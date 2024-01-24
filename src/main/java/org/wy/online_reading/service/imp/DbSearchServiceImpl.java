package org.wy.online_reading.service.imp;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.wy.online_reading.core.comme.resp.PageRespDto;
import org.wy.online_reading.core.comme.resp.RestResp;
import org.wy.online_reading.dao.entity.BookInfo;
import org.wy.online_reading.dao.mapper.BookInfoMapper;
import org.wy.online_reading.dto.req.BookSearchReqDto;
import org.wy.online_reading.dto.resp.BookInfoRespDto;
import org.wy.online_reading.service.SearchService;

import java.util.List;


/**
 * @author 89589
 */
@ConditionalOnProperty(prefix = "spring.elasticsearch", name = "enable", havingValue = "false")
@Service
@RequiredArgsConstructor
@Slf4j
public class DbSearchServiceImpl implements SearchService {

    private final BookInfoMapper bookInfoMapper;

    @Override
    public RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition) {
        Page<BookInfoRespDto> page = new Page<>();
        page.setCurrent(condition.getPageNum());
        page.setSize(condition.getPageSize());
        log.info("********************************************************************");
        log.info(condition.toString());
        List<BookInfo> bookInfos = bookInfoMapper.searchBooks(page,condition);
        return RestResp.ok(PageRespDto.of(condition.getPageNum(), condition.getPageSize(), page.getTotal()
                , bookInfos.stream().map(v -> BookInfoRespDto.builder()
                        .id(v.getId())
                        .bookName(v.getBookName())
                        .categoryId(v.getCategoryId())
                        .categoryName(v.getCategoryName())
                        .authorId(v.getAuthorId())
                        .authorName(v.getAuthorName())
                        .wordCount(v.getWordCount())
                        .lastChapterName(v.getLastChapterName())
                        .build()).toList()));
    }

}
