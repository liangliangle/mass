package com.mass.dao;

import com.mass.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
  int deleteByPrimaryKey(Long id);

  int insert(Goods record);


  Goods selectByPrimaryKey(Long id);

  List<Goods> selectByFinanceId(@Param("id") Long id);


  int updateByPrimaryKey(Goods record);

  int delectByFinanceId(Long id);
}