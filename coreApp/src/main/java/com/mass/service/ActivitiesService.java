package com.mass.service;

import com.github.pagehelper.PageInfo;
import com.mass.dto.query.ActitQuery;
import com.mass.dto.update.AuditActivitiesDto;
import com.mass.dto.create.CreateActivitiesDto;
import com.mass.dto.result.ActivitiesDto;
import com.mass.dto.result.ActivityAndDocDto;

import java.util.List;

/**
 * Created by liliang on 2018/2/3.
 */
public interface ActivitiesService {

    void create(CreateActivitiesDto dto);

    PageInfo<ActivitiesDto> getAll(int start, ActitQuery query);

    ActivityAndDocDto getById(Long id);

    void audit(Long id, AuditActivitiesDto dto);

    void update(CreateActivitiesDto dto);

    List<ActivitiesDto> lately();
}
