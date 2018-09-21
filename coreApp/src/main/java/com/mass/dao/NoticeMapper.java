package com.mass.dao;

import com.mass.dto.query.NoticeQuery;
import com.mass.dto.result.NoticeDto;
import com.mass.entity.Notice;
import com.mass.entity.NoticeDoc;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Date;

public interface NoticeMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(Notice record);


    Notice selectByPrimaryKey(Long id);


    int updateByPrimaryKey(Notice record);


    List<NoticeDto> selectByQuery(NoticeQuery query);

    List<Notice> selectByStart(Date startTime);

    List<NoticeDto> selectMyNotice(@Param("sno") String sno, @Param("status") Integer status);

    int closeNotice();

    List<NoticeDto> selectBySno(@Param("sno") String sno, @Param("status") Integer status);

}