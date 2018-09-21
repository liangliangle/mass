package com.mass.service;

import com.github.pagehelper.PageInfo;
import com.mass.dto.DataResult;
import com.mass.dto.create.CreateVoteDto;
import com.mass.dto.query.VoteQuery;
import com.mass.dto.result.VoteDto;

import java.util.List;

public interface VoteService {

    void create(CreateVoteDto dto);

    void update(CreateVoteDto dto);

    VoteDto getById(Long id);

    PageInfo<VoteDto> query(VoteQuery query, int page);


    PageInfo<VoteDto> getBySno(String sno, Integer status,Integer page);



     List<DataResult> groupByVote(Long id);

}
