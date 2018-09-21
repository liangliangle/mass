package com.mass.dao;

import com.mass.dto.DataResult;
import com.mass.dto.result.VoteResultDto;
import com.mass.entity.VoteResult;

import java.util.List;

public interface VoteResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VoteResult record);


    VoteResult selectByPrimaryKey(Long id);


    int updateByPrimaryKey(VoteResult record);

    List<VoteResultDto> selectByVoteId(Long voteId);


    List<DataResult> groupByVoteId(Long voteId);
}