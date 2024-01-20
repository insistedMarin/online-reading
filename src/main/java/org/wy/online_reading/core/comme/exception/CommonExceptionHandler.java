package org.wy.online_reading.core.comme.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.wy.online_reading.core.comme.constant.ErrorCodeEnum;
import org.wy.online_reading.core.comme.resp.RestResp;

import java.net.BindException;

/**
 * @author 89589
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {
    /**
     * Handle data verification exceptions
     * */
    @ExceptionHandler(BindException.class)
    public RestResp<Void> handlerBindException(BindException e){
        log.error(e.getMessage(),e);
        return RestResp.fail(ErrorCodeEnum.USER_REQUEST_PARAM_ERROR);
    }

    /**
     * Handle business exceptions
     * */
    @ExceptionHandler(BusinessException.class)
    public RestResp<Void> handlerBusinessException(BusinessException e){
        log.error(e.getMessage(),e);
        return RestResp.fail(e.getErrorCodeEnum());
    }

    /**
     * Handling system exceptions
     * */
    @ExceptionHandler(Exception.class)
    public RestResp<Void> handlerException(Exception e){
        log.error(e.getMessage(),e);
        return RestResp.error();
    }
}
