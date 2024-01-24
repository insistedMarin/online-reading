package org.wy.online_reading.service;

import org.wy.online_reading.core.comme.resp.PageRespDto;
import org.wy.online_reading.core.comme.resp.RestResp;
import org.wy.online_reading.dto.req.BookSearchReqDto;
import org.wy.online_reading.dto.resp.BookInfoRespDto;

/**
 * @author 89589
 */
public interface SearchService {

    RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition);
}
