package com.mass.controller;

import com.github.pagehelper.PageInfo;
import com.mass.common.controller.BaseController;
import com.mass.dto.create.CreateFinanceDto;
import com.mass.dto.query.FinMassQuery;
import com.mass.dto.query.FinanceQuery;
import com.mass.dto.result.FinanceDto;
import com.mass.dto.update.AuditFinanceDto;
import com.mass.service.FinanceService;
import com.mass.utils.Check;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@EnableEurekaClient
@RequestMapping("/api/finance")
@Api(value = "财务相关接口", consumes = "application/json")
public class FinanceController extends BaseController {

  @Autowired
  private FinanceService financeService;

  @PostMapping("")
  @ApiOperation("新增")
  public void create(@RequestBody CreateFinanceDto dto) {
    Check.checkEmpty(dto);
    financeService.create(dto);
  }

  @PutMapping("")
  @ApiOperation("修改")
  public void update(@RequestBody CreateFinanceDto dto) {
    Check.checkEmpty(dto);
    financeService.update(dto);
  }

  @GetMapping("{id}")
  @ApiOperation("按照ID查询")
  public FinanceDto getById(@PathVariable("id") Long id) {
    return financeService.getById(id);
  }

  @PostMapping("query")
  @ApiOperation("查询")
  public PageInfo<FinanceDto> getByQuery(@RequestBody FinanceQuery query) {
    Check.checkEmpty(query);
    return financeService.query(query, query.getPage());
  }

  @PostMapping("mymass")
  @ApiOperation("查询本社团")
  public PageInfo<FinanceDto> getByMass(@RequestBody FinMassQuery query) {
    Check.checkEmpty(query);
    return financeService.query(query, query.getPage());
  }
  //

  @PutMapping("audit")
  @ApiOperation("审核")
  public void audit(@RequestBody AuditFinanceDto dto) {
    Check.checkEmpty(dto);
    financeService.audit(dto);
  }

  @PutMapping("settle/{id}")
  @ApiOperation("结算")
  public void settle(@PathVariable("id") Long id) {
    financeService.settle(id);
  }


  @GetMapping("notyet")
  @ApiOperation("获取待审核")
  public PageInfo<FinanceDto> getByAudit(@RequestParam("page") Integer page) {
    return financeService.getByAudit(page);
  }


}
