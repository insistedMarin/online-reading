package org.wy.online_reading.core.comme.req;

import lombok.Data;

/**
 * parent class of page request
 *
 * @author 89589
 */
@Data
public class PageReqDto {

    private int pageNum = 1;

    private int pageSize = 10;

    private boolean fetchAll = false;
}
