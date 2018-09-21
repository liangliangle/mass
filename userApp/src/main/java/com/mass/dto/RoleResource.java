package com.mass.dto;

import java.util.List;

public class RoleResource {
  private Long roleId;
  private List<Long> resourceIds;

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public List<Long> getResourceIds() {
    return resourceIds;
  }

  public void setResourceIds(List<Long> resourceIds) {
    this.resourceIds = resourceIds;
  }
}
