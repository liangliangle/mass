package com.mass.controller;

import com.github.pagehelper.PageInfo;
import com.mass.common.controller.BaseController;
import com.mass.dto.create.CreateNoticeDto;
import com.mass.dto.query.NoticeQuery;
import com.mass.dto.result.CountNoticeDto;
import com.mass.dto.result.NoticeDto;
import com.mass.entity.NoticeUsers;
import com.mass.service.NoticeService;
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
@RequestMapping("/api/notice")
@Api(value = "通知相关接口", consumes = "application/json")
public class NoticeController extends BaseController {
    @Autowired
    private NoticeService noticeService;

    @PostMapping("")
    @ApiOperation("创建通知")
    public void create(@RequestBody CreateNoticeDto dto) {
        Check.checkEmpty(dto);
        noticeService.create(dto);
    }

    @PutMapping("")
    @ApiOperation("更新通知")
    public void update(@RequestBody CreateNoticeDto dto) {
        Check.checkEmpty(dto);
        noticeService.update(dto);
    }

    @GetMapping("/{id}")
    @ApiOperation("单个通知")
    public NoticeDto getById(@PathVariable("id") Long id,
                             @RequestParam(value = "sno", required = false) String sno) {

        return noticeService.getById(id, sno);

    }

    @PostMapping("/query")
    @ApiOperation("查询通知")
    public PageInfo<NoticeDto> query(@RequestBody NoticeQuery query) {
        Check.checkEmpty(query);
        return noticeService.getAll(query.getPage(), query);
    }

    @GetMapping("/mycreate")
    @ApiOperation("查询我创建的通知")
    public PageInfo<NoticeDto> getBySno(@RequestParam("sno") String sno,
                                        @RequestParam(value = "status", required = false) Integer status,
                                        @RequestParam("page") Integer page) {
        return noticeService.getBySno(sno, status, page);
    }

    @GetMapping("/sno")
    @ApiOperation("查询我的通知")
    public PageInfo<NoticeDto> getMyNotice(@RequestParam("sno") String sno,
                                           @RequestParam(value = "status", required = false) Integer status,
                                           @RequestParam("page") Integer page) {
        return noticeService.getMyNotice(sno, status, page);
    }

    @GetMapping("/result/query")
    @ApiOperation("查询通知列表")
    public PageInfo<NoticeUsers> getNoticeResult(@RequestParam("noticeId") Long noticeId,
                                                 @RequestParam(value = "sno", required = false) String sno,
                                                 @RequestParam(value = "status", required = false) Integer status,
                                                 @RequestParam("page") Integer page) {
        return noticeService.getNoticeResult(noticeId, sno, status, page);
    }

    @GetMapping("/result")
    @ApiOperation("查看通知汇总")
    public CountNoticeDto getNoticeResult(@RequestParam("noticeId") Long noticeId) {
        return noticeService.getNoticeResult(noticeId);
    }


}
