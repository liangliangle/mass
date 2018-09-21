package com.mass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mass.common.controller.BaseController;
import com.mass.common.exception.BaseException;
import com.mass.dto.SysRole;
import com.mass.dto.login.SetUserRoleDto;
import com.mass.entity.DataResult;
import com.mass.entity.PageQueryResponseVo;
import com.mass.service.OrgService;
import com.mass.service.RoleService;
import com.mass.service.UserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/role")
@EnableEurekaClient
public class RoleController extends BaseController {

  @Autowired
  RoleService roleService;
  @Autowired
  UserRoleService userRoleService;
  @Autowired
  OrgService orgService;


  /**
   * 获取所有橘色*
   *
   * @描述
   * @参数 []
   * @返回值 com.mass.entity.DataResult
   * @创建人 慧强
   * @创建时间 2018/4/26
   * @修改人和其它信息
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ApiOperation(value = "获取角色", consumes = "application/json")
  public PageInfo<SysRole> getRole(@RequestParam Integer page) {
    PageHelper.startPage(page,10);

    List<SysRole> list = roleService.getRoles();

    return new PageInfo<>(list);
  }

  /**
   * 根据id删除指定角色*
   *
   * @描述
   * @参数 [id]
   * @返回值 com.mass.entity.DataResult
   * @创建人 慧强
   * @创建时间 2018/4/26
   * @修改人和其它信息
   */
  @RequestMapping(value = "", method = RequestMethod.DELETE)
  @ApiOperation(value = "删除角色")
  public void DeLeteById(@RequestParam Integer id) {

    roleService.DeleteById(id);

  }

  /**
   * 创建角色*
   *
   * @描述
   * @参数 [sysRole]
   * @返回值 com.mass.entity.DataResult
   * @创建人 慧强
   * @创建时间 2018/4/28
   * @修改人和其它信息
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ApiOperation(value = "新建角色", consumes = "application/json")
  public void createRole(@RequestBody SysRole sysRole) {
    Integer integer = roleService.createRole(sysRole);

  }

  @RequestMapping(value = "username", method = RequestMethod.DELETE)
  @ApiOperation(value = "删除指定用户的角色")
  public  void      deleteUserRole(@RequestParam String  username,@RequestParam Long roleId){

    boolean flag = userRoleService.getUserRoleCount(username,roleId);
    if (!flag){
      throw new BaseException("指定用户角色不存在！");

    }
    userRoleService.deleteByUsernameAndRoleId(username,roleId);


  }

  @RequestMapping(value = "filte", method = RequestMethod.GET)
  @ApiOperation(value = "过滤社长和普通用户")
   public List<SysRole>  getAllFilte(){
   return     userRoleService.getAllFilte();

  }

  @RequestMapping(value = "username", method = RequestMethod.POST)
  @ApiOperation(value = "设置用户角色")
  public void setUserRoleV2(@RequestBody SetUserRoleDto setUserRoleDto){
                   if (setUserRoleDto.getRoleId()==null || setUserRoleDto.getUsername()==null){
                      throw  new BaseException("角色参数不能为空！");
                   }
                   userRoleService.setUserRoleV2(setUserRoleDto.getUsername(),setUserRoleDto.getRoleId());

  }


}
