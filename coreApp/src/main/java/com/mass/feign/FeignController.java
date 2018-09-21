package com.mass.feign;

import com.mass.dto.DemoDto;
import com.mass.feign.dto.SysUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
@FeignClient("USER-CONTROLLER")
public interface FeignController {
  @GetMapping(value = "/api/user/")
  List<DemoDto> getAll();

  @RequestMapping(value = "/api/user/mass-name", method = RequestMethod.POST)
  @ApiOperation(value = "根据社团名称查找用户信息")
  List<SysUser> getUsersBymassNames(@RequestBody List<String> orgName);
}
