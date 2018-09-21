package com.mass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.mass.common.exception.ServiceException;
import com.mass.dao.*;
import com.mass.dto.DataResult;
import com.mass.dto.create.CreateOptionDto;
import com.mass.dto.create.CreateVoteDto;
import com.mass.dto.query.VoteQuery;
import com.mass.dto.result.OptionsDto;
import com.mass.dto.result.VoteDto;
import com.mass.entity.Options;
import com.mass.entity.Vote;
import com.mass.entity.VoteMass;
import com.mass.service.VoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteMapper voteMapper;
    @Autowired
    private OptionsMapper optionsMapper;
    @Autowired
    private VoteResultMapper voteResultMapper;

    @Autowired
    private VoteMassMapper voteMassMapper;

    @Override
    public void create(CreateVoteDto dto) {
        Vote vote = dtoToEntity(dto);
        if (vote.getEndTime().getTime() < System.currentTimeMillis()) {
            throw new ServiceException("结束时间不得小于当前时间");
        }

        List<Options> list = dtoToOptions(dto, vote.getId());
        if (list.size() < 2) {
            throw new ServiceException("必须有两个以上有效选项");
        }
        voteMapper.insert(vote);
        for (Options options : list) {
            options.setVoteId(vote.getId());
            optionsMapper.insert(options);
        }
        List<VoteMass> voteMasses = dtoToVoteMassEntity(dto, vote.getId());
        for (VoteMass mass : voteMasses) {
            voteMassMapper.insert(mass);
        }
    }

    @Override
    public void update(CreateVoteDto dto) {
        Vote oldVote = voteMapper.selectByPrimaryKey(dto.getId());
        if (oldVote.getStatus() != 1) {
            throw new ServiceException("该投票已经开始，不能修改");
        }
        if (dto.getEndTime().getTime() < System.currentTimeMillis()) {
            throw new ServiceException("结束时间不得小于当前时间");
        }
        List<Options> list = dtoToOptions(dto, dto.getId());
        if (list.size() < 2) {
            throw new ServiceException("必须有两个以上选项");
        }
        voteMapper.updateByPrimaryKey(dtoToEntity(dto));
        optionsMapper.deleteByVoteId(dto.getId());
        voteMassMapper.deleteByVoteId(dto.getId());
        for (Options options : list) {
            optionsMapper.insert(options);
        }
        List<VoteMass> voteMasses = dtoToVoteMassEntity(dto, dto.getId());
        for (VoteMass mass : voteMasses) {
            voteMassMapper.insert(mass);
        }
    }

    @Override
    public VoteDto getById(Long id) {
        Vote vote = voteMapper.selectByPrimaryKey(id);
        List<Options> options = optionsMapper.selectByVoteId(id);
        return entityToDto(vote, options);
    }

    @Override
    public PageInfo<VoteDto> query(VoteQuery query, int page) {
        PageHelper.startPage(page, 10);
        List<Vote> result = voteMapper.selectByQuery(query);
        return new PageInfo(result);
    }

    @Override
    public PageInfo<VoteDto> getBySno(String sno, Integer status, Integer page) {
        PageHelper.startPage(page, 10);
        List<VoteDto> result = voteMapper.selectMyVote(sno, status);
        return new PageInfo(result);
    }


    @Override
    public List<DataResult> groupByVote(Long id) {
        return voteResultMapper.groupByVoteId(id);
    }

    private Vote dtoToEntity(CreateVoteDto dto) {
        Vote vote = new Vote();
        vote.setId(dto.getId());
        vote.setName(dto.getName());
        vote.setEndTime(dto.getEndTime());
        vote.setStartTime(dto.getStartTime());
        vote.setIsAnon(dto.getIsAnon());
        vote.setSno(dto.getSno());
        vote.setStuName(dto.getStuName());
        vote.setIsCoveSon(dto.getIsCoveSon());
        String range = StringUtils.join(dto.getRange(), ",");
        vote.setRange(range);
        vote.setStatus(1);
        vote.setVoteNum(dto.getVoteNum());
        vote.buildDefaultTimeStamp();
        return vote;
    }

    private List<VoteMass> dtoToVoteMassEntity(CreateVoteDto dto, Long voteId) {
        List<String> massNames = dto.getRange();
        List<VoteMass> result = new ArrayList<VoteMass>(massNames.size());
        for (String massName : massNames) {
            VoteMass mass = new VoteMass();
            mass.setVoteId(voteId);
            mass.setMassId(1L);
            //TODO 远程调用查询ID mass.setMassId();
            mass.buildDefaultTimeStamp();
            result.add(mass);
        }
        return result;
    }

    private List<Options> dtoToOptions(CreateVoteDto dto, Long voteId) {
        List<CreateOptionDto> optionsDtos = dto.getOptionsDtos();
        List<Options> list = new ArrayList<>(optionsDtos.size());
        for (CreateOptionDto optionsDto : optionsDtos) {
            if (StringUtils.isEmpty(optionsDto.getThetext())) {
                continue;
            }
            Options options = new Options();
            options.setOrders(optionsDto.getOrders());
            options.setThetext(optionsDto.getThetext());
            options.setVoteId(voteId);
            options.buildDefaultTimeStamp();
            options.setStatus(1);
            list.add(options);
        }
        return list;
    }

    private List<VoteDto> entityToDto(List<Vote> votes) {
        List<VoteDto> list = new ArrayList<>(votes.size());
        for (Vote vote : votes) {
            VoteDto dto = new VoteDto();
            dto.setId(vote.getId());
            dto.setIsAnon(vote.getIsAnon());
            dto.setIsCoveSon(vote.getIsCoveSon());
            dto.setName(vote.getName());
            dto.setEndTime(vote.getEndTime());
            dto.setRange(vote.getRange());
            dto.setStartTime(vote.getStartTime());
            dto.setSno(vote.getSno());
            dto.setStuName(vote.getStuName());
            dto.setStatus(vote.getStatus());
            dto.setVoteNum(vote.getVoteNum());
            list.add(dto);
        }
        return list;
    }

    private VoteDto entityToDto(Vote vote, List<Options> options) {
        VoteDto dto = new VoteDto();
        dto.setId(vote.getId());
        dto.setIsAnon(vote.getIsAnon());
        dto.setIsCoveSon(vote.getIsCoveSon());
        dto.setName(vote.getName());
        dto.setEndTime(vote.getEndTime());
        dto.setRange(vote.getRange());
        dto.setStartTime(vote.getStartTime());
        dto.setSno(vote.getSno());
        dto.setStuName(vote.getStuName());
        dto.setStatus(vote.getStatus());
        dto.setVoteNum(vote.getVoteNum());
        List<OptionsDto> optionsDtos = new ArrayList<>(options.size());
        for (Options o : options) {
            optionsDtos.add(optionsToDto(o, vote.getId()));
        }
        dto.setOptionsDto(optionsDtos);
        return dto;
    }

    private OptionsDto optionsToDto(Options options, Long voteId) {
        OptionsDto dto = new OptionsDto();
        dto.setId(options.getId());
        dto.setVoteId(voteId);
        dto.setStatus(options.getStatus());
        dto.setOrders(options.getOrders());
        dto.setThetext(options.getThetext());
        return dto;
    }
}
