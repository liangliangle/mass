package com.mass.controller;

import com.mass.common.controller.BaseController;
import com.mass.dto.DemoDto;
import com.mass.feign.FeignController;
import com.mass.service.DemoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@EnableEurekaClient
@RequestMapping("/api/demo")
@Api(value = "无效接口", consumes = "application/json")
public class DemoController extends BaseController {
    @Autowired
    private FeignController feignDemoController;
    @Autowired
    private DemoService demoService;

    @GetMapping("")
    public List<DemoDto> test() {
        return feignDemoController.getAll();
    }


    @GetMapping("/local")
    public List<DemoDto> local(){
        return this.demoService.getAll();
    }

}
