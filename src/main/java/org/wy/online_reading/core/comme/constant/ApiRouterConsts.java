package org.wy.online_reading.core.comme.constant;

/**
 * @author 89589
 */
public class ApiRouterConsts {
    /**
     * API request path prefix
     */
    String API_URL_PREFIX = "/api";

    /**
     * Front portal system request path prefix
     */
    String API_FRONT_URL_PREFIX = API_URL_PREFIX + "/front";

    /**
     * Writer management system request path prefix
     */
    String API_AUTHOR_URL_PREFIX = API_URL_PREFIX + "/author";

    /**
     * Platform background management system request path prefix
     */
    String API_ADMIN_URL_PREFIX = API_URL_PREFIX + "/admin";

    /**
     * Home module request path prefix
     * */
    String HOME_URL_PREFIX = "/home";

    /**
     * Novel module request path prefix
     * */
    String BOOK_URL_PREFIX = "/book";

    /**
     * Novel module request path prefix
     * */
    String USER_URL_PREFIX = "/user";

    /**
     * Front portal homepage API request path prefix
     */
    String API_FRONT_HOME_URL_PREFIX = API_FRONT_URL_PREFIX + HOME_URL_PREFIX;

    /**
     * Front portal novel related API request path prefix
     */
    String API_FRONT_BOOK_URL_PREFIX = API_FRONT_URL_PREFIX + BOOK_URL_PREFIX;

    /**
     * Front-end portal member-related API request path prefix
     */
    String API_FRONT_USER_URL_PREFIX = API_FRONT_URL_PREFIX + USER_URL_PREFIX;
}
