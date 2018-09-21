package com.mass.dao;

import com.mass.entity.RegisterCode;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RegisterCodeMapper {
  int deleteByPrimaryKey(Long id);

  int insert(RegisterCode record);


  RegisterCode selectByPrimaryKey(Long id);


  int updateByPrimaryKey(RegisterCode record);


  List<RegisterCode> selectByCodeAndTime(@Param("code") String code,
                                         @Param("time") Date time,
                                         @Param("id") Long id);
}