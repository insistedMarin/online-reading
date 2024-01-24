package org.wy.online_reading.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.wy.online_reading.dao.entity.BookInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wy.online_reading.dto.req.BookSearchReqDto;
import org.wy.online_reading.dto.resp.BookInfoRespDto;


import java.util.List;

/**
 * <p>
 * 小说信息 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @date 2024/01/20
 */
public interface BookInfoMapper extends BaseMapper<BookInfo> {

    /**
     * 增加小说点击量
     *
     * @param bookId 小说ID
     */
    void addVisitCount(@Param("bookId") Long bookId);

    /**
     * 小说搜索
     * @param page
     * @param condition 搜索条件
     * @return 返回结果
     * */
    List<BookInfo> searchBooks(IPage<BookInfoRespDto> page, BookSearchReqDto condition);
}
