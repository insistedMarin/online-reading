package org.wy.online_reading.controller.front;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wy.online_reading.core.comme.constant.ApiRouterConsts;
import org.wy.online_reading.core.comme.resp.RestResp;
import org.wy.online_reading.dto.resp.HomeBookRespDto;
import org.wy.online_reading.service.HomeService;

import java.util.List;

/**
 * @author 89589
 */

@Tag(name = "HomeController", description = "front-homeModule")
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_HOME_URL_PREFIX)
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;
    @GetMapping("books")
    public RestResp<List<HomeBookRespDto>> listHomeBooks(){
        return homeService.listHomeBooks();
    }
}
