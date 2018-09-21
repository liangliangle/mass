package com.mass.dao;

import com.mass.entity.VoteMass;

import java.util.List;

public interface VoteMassMapper {
  int deleteByPrimaryKey(Long id);

  int insert(VoteMass record);


  VoteMass selectByPrimaryKey(Long id);


  int updateByPrimaryKey(VoteMass record);


  List<VoteMass> selectByVoteId(Long voteId);


  int deleteByVoteId(Long voteId);
}