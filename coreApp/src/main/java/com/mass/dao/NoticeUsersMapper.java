package com.mass.dao;

import com.mass.entity.NoticeUsers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeUsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoticeUsers record);


    NoticeUsers selectByPrimaryKey(Long id);


    int updateByPrimaryKey(NoticeUsers record);

    int readNotice(@Param("sno") String sno, @Param("noticeId") Long noticeId);


    List<NoticeUsers> selectByNotice(@Param("noticeId") Long noticeId,
                                     @Param("sno") String sno,
                                     @Param("status") Integer status);


    Long countByNoticeId(@Param("noticeId")Long noticeId,@Param("status")Integer status);

}