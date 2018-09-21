package com.mass.service.impl;

import com.mass.dao.SysRoleMapper;
import com.mass.dto.SysRole;
import com.mass.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRoleImpl  implements RoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Override
    public SysRole getRoleById(String id) {
        return  sysRoleMapper.selectByPrimaryKey(Long.valueOf(id)) ;
    }

    @Override
    public List<SysRole> getRoles() {
        return sysRoleMapper.getRoleAll();
    }

    @Override
    public Integer getRolesCount() {
        return sysRoleMapper.getRolesCount();
    }

    @Override
    @Transactional
    public void DeleteById(Integer id) {
        sysRoleMapper.deleteById(id);
    }

    @Override
    @Transactional
    public Integer createRole(SysRole sysRole) {
        return  sysRoleMapper.insertSelective(sysRole);
    }
}
