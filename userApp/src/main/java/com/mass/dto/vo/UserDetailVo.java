package com.mass.dto.vo;

import com.mass.dto.*;

import java.util.List;

public class UserDetailVo extends SysUser {
   List<SysRole> sysRoles;
   List<SysOrganization> sysOrganizations;

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public List<SysOrganization> getSysOrganizations() {
        return sysOrganizations;
    }

    public void setSysOrganizations(List<SysOrganization> sysOrganizations) {
        this.sysOrganizations = sysOrganizations;
    }
}
