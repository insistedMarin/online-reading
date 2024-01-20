package org.wy.online_reading.core.comme.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * Error code enumeration class.
 *
 * The error code is a string type with a total of 5 digits and is divided into two parts:
 * error source + four-digit number.
 * The sources of errors are divided into A/B/C. A means that the error comes from the user, such as wrong parameters,
 * the version installed by the user is too low, and the user pays
 * Issues such as timeout; B indicates that the error originates from the current system,
 * often due to errors in business logic or poor program robustness; C indicates the source of the error
 * For third-party services, such as CDN service errors, message delivery timeout and other issues;
 * the four-digit number ranges from 0001 to 9999, between major categories
 * The step size is reserved at 100.
 *
 * Error codes are divided into first-level macro error codes, second-level macro error codes,
 * and third-level macro error codes.
 * In error scenarios that cannot be determined more specifically, the first-level macro error code can be used directly.
 * @author 89589
 */
public enum ErrorCodeEnum {
    /**
     * correct code
     * */
    OK("00000","ok"),

    /**
     * Level 1 macro error code, user-side error
     * */
    USER_ERROR("A0001","user side error"),

    /**
     * Level 2 macro error code, user registration error
     * */
    USER_REGISTER_ERROR("A0100","user registration error"),

    /**
     * level 2 macro error code, the user has not agreed to the privacy agreement
     * */
    USER_NO_AGREE_PRIVATE_ERROR("A0101","the user has not agreed to the privacy agreement"),

    /**
     * level 2 macro error code, user request parameter error
     * */
    USER_REQUEST_PARAM_ERROR("A0400","user request parameter error"),

    /**
     * Level 1 macro error code, system execution error
     * */
    SYSTEM_ERROR("B0001","system execution error"),

    ;

    /**
     * error code
     * */
    private String code;

    /**
     * description
     * */
    private String message;

}
