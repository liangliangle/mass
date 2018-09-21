package com.mass.dao;

import com.mass.dto.query.FinanceQuery;
import com.mass.dto.result.FinanceDto;
import com.mass.entity.Finance;

import java.util.List;

/**
 * @author liangliang
 * @date 2018年5月2日
 * 财务相关
 */
public interface FinanceMapper {
  /**
   * 删除一个报销
   *
   * @param id 需要删除的ID
   * @return 影响行数
   */
  int deleteByPrimaryKey(Long id);

  /**
   * 新增操作
   *
   * @param record 需要插入的数据
   * @return 影响行数
   */
  int insert(Finance record);

  /**
   * 按照ID查找
   *
   * @param id
   * @return
   */
  Finance selectByPrimaryKey(Long id);

  /**
   * 按照ID更新
   *
   * @param record
   * @return
   */
  int updateByPrimaryKey(Finance record);

  /**
   * 待审核
   *
   * @return
   */
  List<FinanceDto> selectAudit();

  List<FinanceDto> selectByQuery(FinanceQuery query);

}