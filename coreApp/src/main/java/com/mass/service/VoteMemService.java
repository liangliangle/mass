package com.mass.service;

import com.github.pagehelper.PageInfo;
import com.mass.dto.create.VoteUser;
import com.mass.dto.result.VoteMemDto;
import com.mass.dto.result.VoteResultDto;
import com.mass.entity.VoteMem;
import com.mass.feign.dto.SysUser;

import java.util.List;

public interface VoteMemService {
    void create(VoteMem mem);

    List<VoteMemDto> getBySno(String sno, Integer status);

    void create(List<SysUser> users, Long voteId);


    void vote(VoteUser voteUser);


    PageInfo<VoteResultDto> getByVoteId(Long voteId,Integer page);
}
