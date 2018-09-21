package com.mass.service;

import com.mass.dto.create.CreateProgramDto;
import com.mass.dto.create.CreateProgramMemDto;
import com.mass.dto.result.ActivitiesProgramDto;

import java.util.List;

public interface ProgramService {


    void create(CreateProgramDto dto);

    void create(CreateProgramMemDto dto, Long id);

    void update(CreateProgramDto dto);

    void update(List<CreateProgramDto> dtos);


    void delmem(Long id);

    void del(Long id);

    ActivitiesProgramDto getById(Long id);

    List<ActivitiesProgramDto> getByActivityId(Long activityId);
}
