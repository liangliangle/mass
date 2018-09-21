package com.mass.controller;

import com.mass.common.controller.BaseController;
import com.mass.common.service.JwtAuthenticationServiceImpl;
import com.mass.dto.login.LoginDto;
import com.mass.service.RoleService;
import com.mass.service.UserRoleService;
import com.mass.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableAutoConfiguration
@EnableEurekaClient
@RequestMapping("/api/user")
@Api(value = "登录接口", consumes = "application/json")
public class LoginController extends BaseController {
  @Autowired
  private UserService userService;

  @ApiOperation(value = "登录验证", consumes = "application/json")
  @GetMapping(value = "login")
  public LoginDto Login(@RequestHeader("Auth") String auth) {
    String[] userAndPassword=auth.split(" ");
    return userService.login(userAndPassword[0],userAndPassword[1]);
  }
  @ApiOperation(value = "角色切换TOCHEN验证登录", consumes = "application/json")
  @GetMapping(value = "tocken-login")
  public LoginDto Login(@RequestHeader("Authorization") String authorization, @RequestParam  Long  roleId) {

    return userService.tockenLogin(authorization,roleId);
  }
}
