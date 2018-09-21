package com.mass.controller;

import com.github.pagehelper.PageInfo;
import com.mass.common.controller.BaseController;
import com.mass.dto.DataResult;
import com.mass.dto.create.CreateVoteDto;
import com.mass.dto.create.VoteUser;
import com.mass.dto.query.VoteQuery;
import com.mass.dto.result.VoteDto;
import com.mass.dto.result.VoteResultDto;
import com.mass.service.VoteMemService;
import com.mass.service.VoteService;
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
@RequestMapping("/api/vote")
@Api(value = "投票相关接口", consumes = "application/json")
public class VoteController extends BaseController {
    @Autowired
    private VoteService voteService;
    @Autowired
    private VoteMemService voteMemService;

    @PostMapping("")
    @ApiOperation("创建投票")
    public void create(@RequestBody CreateVoteDto dto) {
        Check.checkEmpty(dto);
        voteService.create(dto);
    }

    @PutMapping("")
    @ApiOperation("修改投票")
    public void update(@RequestBody CreateVoteDto dto) {
        Check.checkEmpty(dto);
        voteService.update(dto);
    }

    @PostMapping("query")
    @ApiOperation("查询")
    public PageInfo<VoteDto> getAll(@RequestBody VoteQuery query) {
        Check.checkEmpty(query);
        return voteService.query(query, query.getPage());
    }


    @GetMapping("my")
    @ApiOperation("查询我的创建")
    public PageInfo<VoteDto> getMy(@RequestParam("sno") String sno,
                                   @RequestParam("page") int page,
                                   @RequestParam(value = "status", required = false) Integer status) {
        VoteQuery query = new VoteQuery();
        query.setSno(sno);
        query.setStatus(status);
        return voteService.query(query, page);
    }

    @ApiOperation("按照ID获取投票信息")
    @GetMapping("{id}")
    public VoteDto getById(@PathVariable("id") Long id) {
        return voteService.getById(id);
    }

    @ApiOperation("按照学号获取投票信息")
    @GetMapping("sno")
    public PageInfo<VoteDto> getBySno(@RequestParam("sno") String sno,
                                      @RequestParam(value = "status", required = false) Integer status,
                                      @RequestParam("page") Integer page) {
        return voteService.getBySno(sno, status, page);
    }

    @ApiOperation("按照学号投票")
    @PostMapping("vote")
    public void getBySno(@RequestBody VoteUser dto) {
        Check.checkEmpty(dto);
        voteMemService.vote(dto);
    }

    @ApiOperation("查询信息")
    @GetMapping("result/{id}")
    public PageInfo<VoteResultDto> getVoteResult(@PathVariable("id") Long id,
                                                 @RequestParam("page") Integer page) {
        return voteMemService.getByVoteId(id, page);

    }

    @ApiOperation("查找汇总结果")
    @GetMapping("group/{id}")
    public List<DataResult> groupByVoteId(@PathVariable("id") Long id) {
        return voteService.groupByVote(id);
    }


}

