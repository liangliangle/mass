package com.mass.dao;

import com.mass.entity.ActivitiesDoc;

public interface ActivitiesDocMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivitiesDoc record);


    ActivitiesDoc selectById(Long id);


    int updateByPrimaryKeyWithBLOBs(ActivitiesDoc record);

    int updateByPrimaryKey(ActivitiesDoc record);

    ActivitiesDoc selectByActivitiesId(Long activitiesId);

}