package com.mass.dao;

import com.mass.entity.VoteMem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VoteMemMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByVoteId(Long id);

    int deleteBySno(String sno);

    int insert(VoteMem record);


    VoteMem selectByPrimaryKey(Long id);

    List<VoteMem> selectBySno(@Param("sno") String sno, @Param("status") Integer status);


    int updateByPrimaryKey(VoteMem record);


    int voteMem(@Param("sno") String sno, @Param("voteId") Long voteId);
}