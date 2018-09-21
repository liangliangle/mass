package com.mass.controller;

import com.mass.dao.DemoDAO;
import com.mass.dto.DemoDto;
import com.mass.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/user")
@Api(value = "demo", consumes = "application/json")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @ApiOperation(value = "demo")
    @GetMapping(value = "")
    public List<DemoDto> getAll() {
        return this.demoService.getAll();
    }
}
