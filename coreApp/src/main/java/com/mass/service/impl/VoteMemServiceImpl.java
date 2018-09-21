package com.mass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mass.common.exception.ServiceException;
import com.mass.dao.VoteMapper;
import com.mass.dao.VoteMemMapper;
import com.mass.dao.VoteResultMapper;
import com.mass.dto.create.OptionUser;
import com.mass.dto.create.VoteUser;
import com.mass.dto.result.VoteMemDto;
import com.mass.dto.result.VoteResultDto;
import com.mass.entity.Vote;
import com.mass.entity.VoteMem;
import com.mass.entity.VoteResult;
import com.mass.feign.dto.SysUser;
import com.mass.service.VoteMemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VoteMemServiceImpl implements VoteMemService {
    @Autowired
    private VoteMemMapper voteMemMapper;
    @Autowired
    private VoteResultMapper voteResultMapper;
    @Autowired
    private VoteMapper voteMapper;

    @Override
    @Async
    public void create(List<SysUser> users, Long voteId) {
        for (SysUser user : users) {
            VoteMem voteMem = new VoteMem();
            voteMem.setIsVote(0);
            voteMem.setSno(user.getUsername());
            voteMem.setStatus(1);
            voteMem.setUsername(user.getName());
            voteMem.setVoteId(voteId);
            create(voteMem);
        }
    }

    @Override
    public void create(VoteMem mem) {
        voteMemMapper.insert(mem);
    }

    @Override
    public List<VoteMemDto> getBySno(String sno, Integer status) {
        List<VoteMem> list = voteMemMapper.selectBySno(sno, status);
        return entityToDto(list);
    }

    @Override
    public void vote(VoteUser voteUser) {
        Vote vote = voteMapper.selectByPrimaryKey(voteUser.getVoteId());
        if (vote != null) {
            long now = System.currentTimeMillis();
            if (vote.getEndTime().getTime() < now || vote.getStartTime().getTime() > now) {
                throw new ServiceException("投票未开始或已过期");
            }
        } else {
            throw new ServiceException("未找到该投票");
        }
        int count=voteMemMapper.voteMem(voteUser.getSno(), voteUser.getVoteId());
        if(count==0){
            throw new ServiceException("您已投票");
        }
        for (OptionUser optionUser : voteUser.getOptions()) {
            VoteResult result = new VoteResult();
            result.setOption(optionUser.getOption());
            result.setOptionId(optionUser.getOptionId());
            result.setSno(voteUser.getSno());
            result.setStatus(1);
            result.setUserName(voteUser.getUsername());
            result.setVoteId(voteUser.getVoteId());
            result.buildDefaultTimeStamp();
            voteResultMapper.insert(result);
        }
    }

    @Override
    public PageInfo<VoteResultDto> getByVoteId(Long voteId, Integer page) {
        Vote vote = voteMapper.selectByPrimaryKey(voteId);
        if (vote.getIsAnon().intValue() > 0) {
            throw new ServiceException("匿名投票不允许查看详情");
        }
        PageHelper.startPage(page, 10);
        List<VoteResultDto> results = voteResultMapper.selectByVoteId(voteId);
        return new PageInfo(results);
    }

    private List<VoteMemDto> entityToDto(List<VoteMem> list) {
        List<VoteMemDto> result = new ArrayList<>(list.size());
        for (VoteMem mem : list) {
            result.add(entityToDto(mem));
        }
        return result;
    }

    private VoteMemDto entityToDto(VoteMem mem) {
        VoteMemDto dto = new VoteMemDto();
        dto.setIsVote(mem.getIsVote());
        dto.setSno(mem.getSno());
        dto.setStatus(mem.getStatus());
        dto.setVoteId(mem.getVoteId());
        dto.setUsername(mem.getUsername());
        dto.setVoteTime(mem.getVoteTime());
        dto.setId(mem.getId());
        return dto;
    }

}
