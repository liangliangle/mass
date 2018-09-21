package com.mass.dao;

import com.mass.entity.Options;

import java.util.List;

/**
 * 投票选项数据库接口
 *
 * @author liangliang
 */
public interface OptionsMapper {
  /**
   * 按照主键逻辑删除选项
   *
   * @param id 需要删除的ID
   * @return 修改行数
   */
  int deleteByPrimaryKey(Long id);

  /**
   * 插入一条记录
   *
   * @param record 需要插入的记录
   * @return 自增ID
   */
  Long insert(Options record);


  /**
   * 按照主键查找选项
   *
   * @param id id
   * @return 数据
   */
  Options selectByPrimaryKey(Long id);

  /**
   * 按照ID更新
   *
   * @param record 更新的对象
   * @return 影响行数
   */
  int updateByPrimaryKey(Options record);

  /**
   * 按照投票ID查询
   *
   * @param voteId 投票ID
   * @return list
   */
  List<Options> selectByVoteId(Long voteId);

  /**
   * 按照投票ID逻辑删除
   *
   * @param voteId 投票ID
   * @return 影响行数
   */
  int deleteByVoteId(Long voteId);
}