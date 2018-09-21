package com.mass.dto.login;

import com.mass.dto.SysOrganization;
import com.mass.dto.SysResource;
import com.mass.dto.SysRole;
import com.mass.dto.SysUser;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class LoginDto {
  @ApiModelProperty("当前用户信息")
  private SysUser user;
  @ApiModelProperty("角色列表")
  private List<SysRole> roles;
  @ApiModelProperty("当前角色")
  private SysRole currentRole;
  @ApiModelProperty("tocken")
  private String tocken;
  @ApiModelProperty("加入社团列表")
  private List<SysOrganization> orgs;
  @ApiModelProperty("管理的社团")
  private SysOrganization manageOrg;
  @ApiModelProperty("菜单")
  private List<SysResource> menus;

  public SysUser getUser() {
    return user;
  }

  public void setUser(SysUser user) {
    this.user = user;
  }

  public List<SysRole> getRoles() {
    return roles;
  }

  public void setRoles(List<SysRole> roles) {
    this.roles = roles;
  }

  public SysRole getCurrentRole() {
    return currentRole;
  }

  public void setCurrentRole(SysRole currentRole) {
    this.currentRole = currentRole;
  }

  public String getTocken() {
    return tocken;
  }

  public void setTocken(String tocken) {
    this.tocken = tocken;
  }

  public List<SysOrganization> getOrgs() {
    return orgs;
  }

  public void setOrgs(List<SysOrganization> orgs) {
    this.orgs = orgs;
  }

  public SysOrganization getManageOrg() {
    return manageOrg;
  }

  public void setManageOrg(SysOrganization manageOrg) {
    this.manageOrg = manageOrg;
  }

  public List<SysResource> getMenus() {
    return menus;
  }

  public void setMenus(List<SysResource> menus) {
    this.menus = menus;
  }
}
