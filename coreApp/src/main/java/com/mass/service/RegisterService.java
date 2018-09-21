package com.mass.service;

import com.github.pagehelper.PageInfo;
import com.mass.dto.create.CreateRegisterDto;
import com.mass.dto.create.CreateRegisterMemDto;
import com.mass.dto.query.ExportRegister;
import com.mass.dto.query.RegisterQuery;
import com.mass.dto.result.RegisterCodeDto;
import com.mass.dto.result.RegisterDto;
import com.mass.entity.RegisterCode;
import com.mass.entity.RegisterMem;

import java.util.List;

public interface RegisterService {


    void create(CreateRegisterDto dto);

    void update(CreateRegisterDto dto);

    RegisterCodeDto getRegisterCode(Long id);


    void register(CreateRegisterMemDto dto);

    PageInfo<RegisterDto> getBySnoAndStatus(String sno, Integer status, Integer page);

    PageInfo<RegisterDto> query(RegisterQuery query, Integer page);


    List<List<String>> export(ExportRegister register);

    PageInfo<RegisterMem> result(Long id, Integer page);


    List<RegisterDto> getByActivity(Long activityId);


    RegisterDto getById(Long id);
}
