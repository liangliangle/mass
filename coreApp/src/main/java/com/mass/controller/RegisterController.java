package com.mass.controller;

import com.github.pagehelper.PageInfo;
import com.mass.common.controller.BaseController;
import com.mass.dto.create.CreateRegisterDto;
import com.mass.dto.create.CreateRegisterMemDto;
import com.mass.dto.query.ExportRegister;
import com.mass.dto.query.RegisterQuery;
import com.mass.dto.result.RegisterCodeDto;
import com.mass.dto.result.RegisterDto;
import com.mass.entity.RegisterMem;
import com.mass.service.RegisterService;
import com.mass.utils.Check;
import com.mass.utils.ExcelUtil;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@EnableAutoConfiguration
@EnableEurekaClient
@RequestMapping("/api/register")
@Api(value = "签到相关接口", consumes = "application/json")
public class RegisterController extends BaseController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("")
    @ApiOperation("创建签到")
    public void create(@RequestBody CreateRegisterDto dto) {
        Check.checkEmpty(dto);
        registerService.create(dto);
    }

    @PutMapping("")
    @ApiOperation("修改签到")
    public void update(@RequestBody CreateRegisterDto dto) {
        Check.checkEmpty(dto);
        registerService.update(dto);
    }

    @GetMapping("code/{id}")
    @ApiOperation("获取签到码")
    public RegisterCodeDto getCode(@PathVariable("id") Long id) {
        return registerService.getRegisterCode(id);
    }

    @GetMapping("sno/")
    @ApiOperation("获取我创建的签到")
    public PageInfo<RegisterDto> getBySno(@RequestParam("sno") String sno,
                                          @RequestParam("page") Integer page,
                                          @RequestParam(value = "status", required = false)
                                                  Integer status) {
        return registerService.getBySnoAndStatus(sno, status, page);
    }

    @PostMapping("/register")
    @ApiOperation("签到")
    public void register(@RequestBody CreateRegisterMemDto dto) {
        Check.checkEmpty(dto);
        registerService.register(dto);
    }

    @PostMapping("query")
    @ApiOperation("查询签到")
    public PageInfo<RegisterDto> query(@RequestBody RegisterQuery query) {
        Check.checkEmpty(query);
        return registerService.query(query, query.getPage());
    }


    @GetMapping("result/{id}")
    @ApiOperation("签到结果")
    public PageInfo<RegisterMem> result(@PathVariable("id") Long id,
                                        @RequestParam("page") Integer page
    ) {
        return registerService.result(id, page);
    }

    @GetMapping("activity/{activityId}")
    @ApiOperation("签到结果")
    public List<RegisterDto> getByActivity(@PathVariable("activityId") Long activityId) {
        return registerService.getByActivity(activityId);
    }

    @GetMapping("{id}")
    @ApiOperation("按照ID查找签到信息")
    public RegisterDto getById(@PathVariable("id") Long id) {
        return registerService.getById(id);
    }


}
