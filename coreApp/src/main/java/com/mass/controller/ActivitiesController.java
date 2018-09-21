package com.mass.controller;

import com.github.pagehelper.PageInfo;
import com.mass.common.controller.BaseController;
import com.mass.dto.create.CreateProgramDto;
import com.mass.dto.create.CreateProgramMemDto;
import com.mass.dto.query.ActitQuery;
import com.mass.dto.result.ActivitiesProgramDto;
import com.mass.dto.update.AuditActivitiesDto;
import com.mass.dto.create.CreateActivitiesDto;
import com.mass.dto.result.ActivitiesDto;
import com.mass.dto.result.ActivityAndDocDto;
import com.mass.service.ActivitiesService;
import com.mass.service.ProgramService;
import com.mass.utils.Check;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@EnableEurekaClient
@RequestMapping("/api/")
@Api(value = "活动相关接口", consumes = "application/json")
public class ActivitiesController extends BaseController {

    @Autowired
    private ActivitiesService activitiesService;
    @Autowired
    private ProgramService programService;

    @PostMapping("activits/query")
    @ApiOperation("查询活动")
    public PageInfo<ActivitiesDto> getAll(@RequestBody ActitQuery query) {
        Check.checkEmpty(query);
        return activitiesService.getAll(query.getPage(), query);

    }

    @PostMapping("activits/notyet")
    @ApiOperation("查询待审核活动")
    public PageInfo<ActivitiesDto> getAll(@RequestParam("page") int page) {
        ActitQuery query = new ActitQuery();
        query.setStatus(1);
        return activitiesService.getAll(page, query);

    }

    @PostMapping("activits/mymass")
    @ApiOperation("查询社团活动")
    public PageInfo<ActivitiesDto> getMyMass(@RequestParam("page") int page,
                                             @RequestParam("mass") String mass,
                                             @RequestParam(value = "status",
                                                     required = false)
                                                     Integer status) {
        ActitQuery query = new ActitQuery();
        query.setSponsorMass(mass);
        query.setStatus(status);
        return activitiesService.getAll(page, query);

    }

    @GetMapping("activits/{id}")
    @ApiOperation("按照ID查询")
    public ActivityAndDocDto getById(@PathVariable("id") Long id) {
        return this.activitiesService.getById(id);
    }


    @PostMapping("activits")
    @ApiOperation("创建一个活动")
    public void createActivits(@RequestBody CreateActivitiesDto dto) {
        Check.checkEmpty(dto);
        activitiesService.create(dto);
    }

    @PostMapping("activits/audit")
    @ApiOperation("审核活动")
    public void audit(@RequestBody AuditActivitiesDto dto) {
        Check.checkEmpty(dto);
        activitiesService.audit(dto.getId(), dto);

    }

    @PutMapping("activits")
    @ApiOperation("更新活动")
    public void updateActivits(@RequestBody CreateActivitiesDto dto) {
        Check.checkEmpty(dto);
        activitiesService.update(dto);
    }

    @GetMapping("activits/lately")
    @ApiOperation("最近的活动")
    public List<ActivitiesDto> lately() {
        return activitiesService.lately();
    }

    @PostMapping("/program")
    @ApiOperation("为活动添加节目")
    public void createProgram(@RequestBody CreateProgramDto dto) {
        Check.checkEmpty(dto);
        programService.create(dto);

    }

    @PutMapping("program")
    @ApiOperation("为活动更新节目")
    public void updatePrograms(@RequestBody CreateProgramDto dto) {
        Check.checkEmpty(dto);
        programService.update(dto);
    }

    @GetMapping("program/{id}")
    @ApiOperation("按照节目ID查询信息")
    public ActivitiesProgramDto getProgramById(@PathVariable("id") Long id) {
        return programService.getById(id);
    }

    @GetMapping("program")
    @ApiOperation("按照活动ID查询节目信息")
    public List<ActivitiesProgramDto> getProgramByActivityId(@RequestParam("activityId") Long activityId) {
        return programService.getByActivityId(activityId);
    }

    @PostMapping("program/mem/{id}")
    @ApiOperation("添加人员")
    public void addMem(@RequestBody CreateProgramMemDto dto, @PathVariable("id") Long id) {
        Check.checkEmpty(dto);
        programService.create(dto, id);
    }

    @DeleteMapping("program/mem/{id}")
    @ApiOperation("添加人员")
    public void delMem(@PathVariable("id") Long id) {
        programService.delmem(id);
    }

    @DeleteMapping("program/{id}")
    @ApiOperation("按照节目ID查询信息")
    public void del(@PathVariable("id") Long id) {
        programService.del(id);
    }

}
