package com.mass.dao;

import com.mass.dto.SysRoleResource;

public interface SysRoleResourceMapper {


  int insert(SysRoleResource roleResource);

  int update(SysRoleResource roleResource);

  SysRoleResource selectByRoleResource(SysRoleResource roleResource);

  int rmAllReource(Long roleId);
}
