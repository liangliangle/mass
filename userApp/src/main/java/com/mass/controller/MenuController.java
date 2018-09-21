package com.mass.controller;

import com.mass.common.controller.BaseController;
import com.mass.dao.SysResourceMapper;
import com.mass.dao.SysRoleResourceMapper;
import com.mass.dto.RoleResource;
import com.mass.dto.SysResource;
import com.mass.dto.SysRoleResource;
import com.mass.service.RoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableEurekaClient
@RestController
@RequestMapping("/api/menus")
@Api(value = "菜单接口", consumes = "application/json")
public class MenuController extends BaseController {

  @Autowired
  private SysResourceMapper resourceMapper;
  @Autowired
  private RoleResourceService resourceService;
  @Autowired
  private SysRoleResourceMapper roleResourceMapper;

  @ApiOperation(value = "获取全部菜单")
  @GetMapping("")
  public List<SysResource> getAll() {
    return resourceMapper.findByParent(0L);
  }

  @ApiOperation(value = "按照角色ID获取菜单")
  @GetMapping("role")
  public List<SysResource> getByRole(@RequestParam("roleId") Long roleId) {
    return resourceService.getResource(roleId);
  }

  @PostMapping("role")
  @ApiOperation(value = "添加权限")
  public void addReource(@RequestBody RoleResource resource) {
    roleResourceMapper.rmAllReource(resource.getRoleId());
    for (Long resourceId : resource.getResourceIds()) {
      SysRoleResource roleResource = new SysRoleResource();
      roleResource.setRoleId(resource.getRoleId());
      roleResource.setResourceId(resourceId);
      roleResource = roleResourceMapper.selectByRoleResource(roleResource);
      if (roleResource == null) {
        roleResource = new SysRoleResource();
        roleResource.setRoleId(resource.getRoleId());
        roleResource.setResourceId(resourceId);
        roleResource.setStatus(1);
        roleResourceMapper.insert(roleResource);
      } else {
        roleResource.setStatus(1);
        roleResourceMapper.update(roleResource);
      }
    }
  }


}




