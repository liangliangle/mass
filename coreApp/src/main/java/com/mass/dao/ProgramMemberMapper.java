package com.mass.dao;

import com.mass.entity.ProgramMember;

import java.util.List;

public interface ProgramMemberMapper {
    int deleteByPrimaryKey(Long id);


    int deleteByByProgram(Long id);

    int insert(ProgramMember record);


    ProgramMember selectByPrimaryKey(Long id);


    int updateByPrimaryKey(ProgramMember record);

    List<ProgramMember> selectByProgramId(Long id);

    List<ProgramMember> selectByMemId(String sno);
}