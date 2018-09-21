package com.mass.service;

import com.github.pagehelper.PageInfo;
import com.mass.dto.create.CreateFinanceDto;
import com.mass.dto.query.FinMassQuery;
import com.mass.dto.query.FinanceQuery;
import com.mass.dto.result.FinanceDto;
import com.mass.dto.update.AuditFinanceDto;

import java.util.List;

public interface FinanceService {

  void create(CreateFinanceDto dto);

  void update(CreateFinanceDto dto);


  void audit(AuditFinanceDto dto);


  void settle(Long id);

  PageInfo<FinanceDto> query(FinanceQuery query, Integer page);

  PageInfo<FinanceDto> query(FinMassQuery query, Integer page);

  FinanceDto getById(Long id);

  PageInfo<FinanceDto> getByAudit(Integer page);

}
