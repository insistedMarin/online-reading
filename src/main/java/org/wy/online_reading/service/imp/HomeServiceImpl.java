package org.wy.online_reading.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wy.online_reading.core.comme.resp.RestResp;
import org.wy.online_reading.dto.resp.HomeBookRespDto;
import org.wy.online_reading.manager.HomeBookCacheManager;
import org.wy.online_reading.service.HomeService;

import java.util.List;

/**
 * @author 89589
 */
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeBookCacheManager homeBookCacheManager;

    @Override
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        return RestResp.ok(homeBookCacheManager.listHomeBooks());
    }
}
