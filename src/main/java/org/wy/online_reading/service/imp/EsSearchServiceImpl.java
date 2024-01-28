package org.wy.online_reading.service.imp;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.json.JsonData;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.wy.online_reading.core.comme.resp.PageRespDto;
import org.wy.online_reading.core.comme.resp.RestResp;
import org.wy.online_reading.dto.es.EsBookDto;
import org.wy.online_reading.dto.req.BookSearchReqDto;
import org.wy.online_reading.dto.resp.BookInfoRespDto;
import org.wy.online_reading.service.SearchService;
import org.wy.online_reading.core.constant.EsConsts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ConditionalOnProperty(prefix = "spring.elasticsearch", name = "enable", havingValue = "true")
@Service
@RequiredArgsConstructor
@Slf4j
public class EsSearchServiceImpl implements SearchService {

    private final ElasticsearchClient esClient;

    @SneakyThrows
    @Override
    public RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition) {

        SearchResponse<EsBookDto> response = esClient.search(s -> {

                    SearchRequest.Builder searchBuilder = s.index(EsConsts.BookIndex.INDEX_NAME);
                    buildSearchCondition(condition, searchBuilder);
                    // 排序
                    if (!StringUtils.isBlank(condition.getSort())) {
                        searchBuilder.sort(o ->
                                o.field(f -> f.field(StringUtils
                                                .underlineToCamel(condition.getSort().split(" ")[0]))
                                        .order(SortOrder.Desc))
                        );
                    }
                    searchBuilder.from((condition.getPageNum() - 1) * condition.getPageSize())
                            .size(condition.getPageSize());


                    return searchBuilder;
                },
                EsBookDto.class
        );

        TotalHits total = response.hits().total();

        List<BookInfoRespDto> list = new ArrayList<>();
        List<Hit<EsBookDto>> hits = response.hits().hits();
        for (Hit<EsBookDto> hit : hits) {
            EsBookDto book = hit.source();
            assert book != null;
            list.add(BookInfoRespDto.builder()
                    .id(book.getId())
                    .bookName(book.getBookName())
                    .categoryId(book.getCategoryId())
                    .categoryName(book.getCategoryName())
                    .authorId(book.getAuthorId())
                    .authorName(book.getAuthorName())
                    .wordCount(book.getWordCount())
                    .lastChapterName(book.getLastChapterName())
                    .build());
        }
        assert total != null;
        return RestResp.ok(PageRespDto.of(condition.getPageNum(), condition.getPageSize(), total.value(), list));

    }

    /**
     * 构建查询条件
     */
    private void buildSearchCondition(BookSearchReqDto condition, SearchRequest.Builder searchBuilder) {

        BoolQuery boolQuery = BoolQuery.of(b -> {

            if (!StringUtils.isBlank(condition.getKeyword())) {
                // 关键词匹配
                log.info(condition.getKeyword());
                b.must((q -> q.multiMatch(t -> t
                        .fields("book_name^2","author_name^1.8","book_desc^0.1")
                        .query(condition.getKeyword())
                )
                ));
            }

            // 精确查询
            if (Objects.nonNull(condition.getWorkDirection())) {
                b.must(TermQuery.of(m -> m
                        .field("work_direction")
                        .value(condition.getWorkDirection())
                )._toQuery());
            }

            if (Objects.nonNull(condition.getCategoryId())) {
                b.must(TermQuery.of(m -> m
                        .field("category_id")
                        .value(condition.getCategoryId())
                )._toQuery());
            }

            // 范围查询
            if (Objects.nonNull(condition.getWordCountMin())) {
                b.must(RangeQuery.of(m -> m
                        .field("word_count")
                        .gte(JsonData.of(condition.getWordCountMin()))
                )._toQuery());
            }

            if (Objects.nonNull(condition.getWordCountMax())) {
                b.must(RangeQuery.of(m -> m
                        .field("word_count")
                        .lt(JsonData.of(condition.getWordCountMax()))
                )._toQuery());
            }

            if (Objects.nonNull(condition.getUpdateTimeMin())) {
                b.must(RangeQuery.of(m -> m
                        .field("last_chapter_update_time")
                        .gte(JsonData.of(condition.getUpdateTimeMin().getTime()))
                )._toQuery());
            }

            return b;

        });

        searchBuilder.query(q -> q.bool(boolQuery));

    }
}
