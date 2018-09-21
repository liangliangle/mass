package com.mass.dao;

import com.mass.dto.query.ActitQuery;
import com.mass.dto.result.ActivitiesDto;
import com.mass.entity.Activities;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ActivitiesMapper {
    int deleteByPrimaryKey(Long id);

    long insert(Activities record);


    Activities selectByPrimaryKey(Long id);


    int updateByPrimaryKey(Activities record);

    List<ActivitiesDto> selectByQuery(@Param("query") ActitQuery query);

    List<ActivitiesDto> lately(Date lastDate);


    int checkActivity(String date);
}