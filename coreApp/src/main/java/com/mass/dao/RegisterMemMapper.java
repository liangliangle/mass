package com.mass.dao;

import com.mass.entity.RegisterMem;

import java.util.List;

public interface RegisterMemMapper {
  int deleteByPrimaryKey(Long id);

  int insert(RegisterMem record);


  RegisterMem selectByPrimaryKey(Long id);


  int updateByPrimaryKey(RegisterMem record);

  List<RegisterMem> selectByRegister(Long register);
}