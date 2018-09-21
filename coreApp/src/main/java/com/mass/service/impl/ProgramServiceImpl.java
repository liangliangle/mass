package com.mass.service.impl;

import com.mass.common.exception.ServiceException;
import com.mass.dao.ActivitiesProgramMapper;
import com.mass.dao.ProgramMemberMapper;
import com.mass.dto.create.CreateProgramDto;
import com.mass.dto.create.CreateProgramMemDto;
import com.mass.dto.result.ActivitiesProgramDto;
import com.mass.dto.result.ProgramMemberDto;
import com.mass.entity.ActivitiesProgram;
import com.mass.entity.ProgramMember;
import com.mass.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 节目相关的服务
 *
 * @author liangliang
 * @date 2018年3月30日
 */
@Service
public class ProgramServiceImpl implements ProgramService {
    @Autowired
    private ActivitiesProgramMapper activitiesProgramMapper;
    @Autowired
    private ProgramMemberMapper programMemberMapper;


    @Override
    public void create(CreateProgramMemDto dto, Long id) {
        ActivitiesProgram program = activitiesProgramMapper.selectByPrimaryKey(id);
        if(program==null){
            throw new ServiceException("节目不存在");
        }
        ProgramMember member = dtoToEntity(dto, id);
        member.setActivitiesId(program.getActivitiesId());
        member.setProgramId(id);
        member.setProgramName(program.getName());
        programMemberMapper.insert(member);

    }

    @Override
    public void delmem(Long id) {
        programMemberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void del(Long id) {
        activitiesProgramMapper.deleteByPrimaryKey(id);
        programMemberMapper.deleteByByProgram(id);
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public void create(CreateProgramDto dto) {
        if (dto.getProgramMemDtoList().size() < 1) {
            throw new ServiceException("节目[" + dto.getName() + "]必须有一个成员");
        }
        ActivitiesProgram program = dtoToEntity(dto);
        activitiesProgramMapper.insert(program);
        List<ProgramMember> members = dtoToMemEntity(dto, program.getId());
        for (ProgramMember member : members) {
            member.setStatus(1);
            programMemberMapper.insert(member);
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public void update(CreateProgramDto dto) {
        if (dto.getProgramMemDtoList().size() < 1) {
            throw new ServiceException("节目[" + dto.getName() + "]必须有一个成员");
        }
        ActivitiesProgram program = dtoToEntity(dto);
        program.setLastModifyTime(new Date());
        activitiesProgramMapper.updateByPrimaryKey(program);
        List<ProgramMember> members = dtoToMemEntity(dto, dto.getId());
        for (ProgramMember member : members) {
            if (null == member.getId()) {
                programMemberMapper.insert(member);
            } else {
                member.setLastModifyTime(new Date());
                member.setStatus(1);
                programMemberMapper.updateByPrimaryKey(member);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public void update(List<CreateProgramDto> dtos) {
        for (CreateProgramDto dto : dtos) {
            if (dto.getProgramMemDtoList().size() < 1) {
                throw new ServiceException("节目[" + dto.getName() + "]必须有一个成员");
            }
        }
        for (CreateProgramDto dto : dtos) {
            update(dto);
        }
    }

    @Override
    public ActivitiesProgramDto getById(Long id) {
        ActivitiesProgram program = activitiesProgramMapper.selectByPrimaryKey(id);
        List<ProgramMember> members = programMemberMapper.selectByProgramId(id);
        return entityToDto(program, members);
    }

    @Override
    public List<ActivitiesProgramDto> getByActivityId(Long activityId) {
        List<ActivitiesProgramDto> result = new ArrayList<>();
        List<ActivitiesProgram> programs = activitiesProgramMapper.selectByActivity(activityId);
        for (ActivitiesProgram program : programs) {
            List<ProgramMember> members = programMemberMapper.selectByProgramId(program.getId());
            result.add(entityToDto(program, members));
        }
        return result;
    }

    private ActivitiesProgram dtoToEntity(CreateProgramDto dto) {
        ActivitiesProgram program = new ActivitiesProgram();
        program.setActivitiesId(dto.getActivitiesId());
        program.setMass(dto.getMass());
        program.setMassId(dto.getMassId());
        program.setName(dto.getName());
        program.setStatus(1);
        if (null != dto.getId()) {
            program.setId(dto.getId());
        }
        return program;
    }

    private List<ProgramMember> dtoToMemEntity(CreateProgramDto programDto, Long
            programId) {
        List<CreateProgramMemDto> list = programDto.getProgramMemDtoList();
        List<ProgramMember> result = new ArrayList<>();
        for (CreateProgramMemDto dto : list) {
            if (dto.getSno() == null || dto.getSno() == "") {
                continue;
            }
            ProgramMember member = new ProgramMember();
            member.setActivitiesId(programDto.getActivitiesId());
            member.setProgramId(programId);
            member.setSno(dto.getSno());
            member.setUsername(dto.getName());
            member.setProgramName(programDto.getName());
            member.setType(dto.getType());
            member.setStatus(1);
            if (null != dto.getId()) {
                member.setId(dto.getId());
            }
            result.add(member);
        }
        return result;
    }

    private ActivitiesProgramDto entityToDto(ActivitiesProgram program,
                                             List<ProgramMember> members) {
        ActivitiesProgramDto dto = new ActivitiesProgramDto();
        dto.setId(program.getId());
        dto.setActivitiesId(program.getActivitiesId());
        dto.setMass(program.getMass());
        dto.setMassId(program.getMassId());
        dto.setName(program.getName());
        dto.setStatus(program.getStatus());
        List<ProgramMemberDto> memberDtos = new ArrayList<>(members.size());
        for (ProgramMember member : members) {
            ProgramMemberDto memberDto = new ProgramMemberDto();
            memberDto.setActivitiesId(member.getActivitiesId());
            memberDto.setProgramId(member.getProgramId());
            memberDto.setProgramName(member.getProgramName());
            memberDto.setSno(member.getSno());
            memberDto.setName(member.getUsername());
            memberDto.setType(member.getType());
            memberDto.setId(member.getId());
            memberDto.setStatus(member.getStatus());
            memberDtos.add(memberDto);
        }
        dto.setProgramMemberDtoList(memberDtos);
        return dto;
    }

    private ProgramMember dtoToEntity(CreateProgramMemDto dto, Long id) {
        ProgramMember member = new ProgramMember();
        member.setProgramId(id);
        member.setProgramName(dto.getProgramName());
        member.setSno(dto.getSno());
        member.setUsername(dto.getName());
        member.setType(dto.getType());
        member.setId(dto.getId());
        member.setStatus(1);
        return member;
    }
}
