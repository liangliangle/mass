package com.mass.service;

import com.mass.dto.SysRole;

import java.util.List;

public interface RoleService {

    public SysRole getRoleById(String id);

    public List<SysRole> getRoles();

    public Integer getRolesCount();

    public void DeleteById(Integer id);

    Integer createRole(SysRole sysRole);
}
