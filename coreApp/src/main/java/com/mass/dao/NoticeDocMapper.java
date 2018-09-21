package com.mass.dao;

import com.mass.entity.NoticeDoc;

import java.util.List;

public interface NoticeDocMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoticeDoc record);

    int insertSelective(NoticeDoc record);

    NoticeDoc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NoticeDoc record);

    int updateByPrimaryKeyWithBLOBs(NoticeDoc record);

    int updateByPrimaryKey(NoticeDoc record);

    int updateByNoticeId(NoticeDoc doc);

    NoticeDoc selectByNoticeId(Long noticeId);
}