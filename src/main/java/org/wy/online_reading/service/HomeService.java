package org.wy.online_reading.service;

import org.wy.online_reading.core.comme.resp.RestResp;
import org.wy.online_reading.dto.resp.HomeBookRespDto;

import java.util.List;

/**
 * @author 89589
 */
public interface HomeService {
    /**
     * 查询首页小说推荐列表
     *
     * @return 首页小说推荐列表的 rest 响应结果
     */
    RestResp<List<HomeBookRespDto>> listHomeBooks();

}
