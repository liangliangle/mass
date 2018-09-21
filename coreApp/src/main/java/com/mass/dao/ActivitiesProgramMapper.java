package com.mass.dao;

import com.mass.entity.ActivitiesProgram;

import java.util.List;

public interface ActivitiesProgramMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivitiesProgram record);


    ActivitiesProgram selectByPrimaryKey(Long id);


    int updateByPrimaryKey(ActivitiesProgram record);

    List<ActivitiesProgram> selectByActivity(Long id);
}