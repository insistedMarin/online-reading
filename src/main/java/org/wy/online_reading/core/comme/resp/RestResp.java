package org.wy.online_reading.core.comme.resp;

import lombok.Getter;
import org.wy.online_reading.core.comme.constant.ErrorCodeEnum;

import java.util.Objects;

/**
 * Http Rest response tool and data format encapsulation
 *
 * @param <T>
 * @author 89589
 */
@Getter
public class RestResp<T> {
    private String code;
    private String message;
    private T data;
    private RestResp(){
        this.code  = ErrorCodeEnum.OK.getCode();
        this.message = ErrorCodeEnum.OK.getMessage();
    }

    private RestResp(ErrorCodeEnum errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
    private RestResp(T data){
        this.data = data;
    }

    public static RestResp<Void> ok (){
        return new RestResp<>();
    }

    public static <T> RestResp<T> ok (T data){
        return new RestResp<>(data);
    }

    public static RestResp<Void> fail (ErrorCodeEnum errorCode){
        return new RestResp<>(errorCode);
    }

    public static RestResp<Void> error() {
        return new RestResp<>(ErrorCodeEnum.SYSTEM_ERROR);
    }
    public boolean isOk() {
        return Objects.equals(this.code, ErrorCodeEnum.OK.getCode());
    }
}
