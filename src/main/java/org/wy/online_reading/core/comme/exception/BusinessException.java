package org.wy.online_reading.core.comme.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wy.online_reading.core.comme.constant.ErrorCodeEnum;

/**
 * @author 89589
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException{
    private final ErrorCodeEnum errorCodeEnum;
    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage(), null, false, false);
        this.errorCodeEnum = errorCodeEnum;
    }
}
