package com.mass.dao;

import com.mass.entity.NoticeMass;

public interface NoticeMassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoticeMass record);


    NoticeMass selectByPrimaryKey(Long id);


    int updateByPrimaryKey(NoticeMass record);


    int deleteByNoticeId(Long noticeId);
}