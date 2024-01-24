package org.wy.online_reading.controller.front;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wy.online_reading.core.comme.constant.ApiRouterConsts;
import org.wy.online_reading.core.comme.resp.PageRespDto;
import org.wy.online_reading.core.comme.resp.RestResp;
import org.wy.online_reading.dto.req.BookSearchReqDto;
import org.wy.online_reading.dto.resp.BookInfoRespDto;
import org.wy.online_reading.service.SearchService;


/**
 * @author 89589
 */
@Tag(name = "BookController", description = "前台门户-小说模块")
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_BOOK_URL_PREFIX)
@RequiredArgsConstructor
public class BookController {

    private final SearchService searchService;

    /**
     * 小说搜索接口
     */
    @GetMapping("search_list")
    public RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition) {
        return searchService.searchBooks(condition);
    }

}
