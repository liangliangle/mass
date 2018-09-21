package com.mass.service.impl;

import com.mass.common.exception.BaseException;
import com.mass.dao.SysOrganizationMapper;
import com.mass.dao.SysRoleMapper;
import com.mass.dao.SysUserMapper;
import com.mass.dao.UserRoleMapper;
import com.mass.dto.*;
import com.mass.dto.vo.UserDetailVo;
import com.mass.entity.DataResult;
import com.mass.entity.PageQueryResponseVo;
import com.mass.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 上午10:08 2018/5/1 2018
 * @Modify:
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysOrganizationMapper sysOrganizationMapper;


    @Override
    public UserRole getUserRole(String sysUser) {
        return userRoleMapper.getRoleByUsernmae(sysUser);
    }

    @Override
    public void insertUserRole(UserRole userRole) {
        userRoleMapper.insert(userRole);
    }

    @Override
    public void updateSelective(UserRole userRole) {
        userRoleMapper.updateByPrimaryKeySelective(userRole);

    }

    @Override
    public boolean getUserRoleCount(String username, Long roleId) {
        return userRoleMapper.getrole(username, roleId) > 0 ? true : false;
    }

    @Override
    public void insertNewUserRole(String username, Long roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserName(username);
        userRole.setRoleId(roleId);
        userRole.setCreateTime(new Date());
        userRole.setUpdateTime(new Date());
        userRole.setIsDefault(0);
        userRoleMapper.insert(userRole);

    }

    @Override
    @Transactional
    public void deleteByUsernameAndRoleId(String username, Long roleId) {
        UserRole userRole = userRoleMapper.getRoleByUsernmaeAndRoleId(username, roleId);
//                 if (userRole.getIsDefault()==1){
//                     dataResult.setMessage("当前默认角色不可删除");
//                     throw new BaseException("当前默认角色不可删除");
//                 }
        if (roleId == 2) {
            throw new BaseException("该社长角色不可删除");
        }
        if (roleId == 8) {
            throw new BaseException("该普通用户角色不可删除");
        }
        userRoleMapper.deleteByUsernameAndRoleId(username, roleId);

    }

    @Override
    public void deleteByUseridAndRoleid(String presidentSno, int i) {
        userRoleMapper.deleteByUseridAndRoleid(presidentSno, i);
    }

    @Override
    public int persentRoleBysno(String presidentSno, int i) {
        return userRoleMapper.persentRoleBysno(presidentSno, i);


    }

    @Override
    public List<SysRole> getAllFilte() {
        return sysRoleMapper.getAllFilte();
    }

    @Override
    public DataResult userListInRole(String username, Integer offset, Integer limit) {
        DataResult dataResult = new DataResult();
        List<SysUser> sysUser = sysUserMapper.getuserListInRole(username, offset, limit);
        PageQueryResponseVo<SysUser> pageQueryResponseVo = new PageQueryResponseVo<>();
        pageQueryResponseVo.setListData(sysUser);
        pageQueryResponseVo.setListCount(sysUserMapper.userListInRoleCount(username, offset, limit));
        dataResult.setData(pageQueryResponseVo);
        return dataResult;
    }

    @Override
    public UserDetailVo userInRoleDetail(String username) {
        UserDetailVo userDetailVo = sysUserMapper.getUserByUserName(username);
        List<SysRole> userRoles = userRoleMapper.getSysUserByUserName(username);
        List<SysOrganization> userOrgQueryList = sysOrganizationMapper.getByuserName(username);
        userDetailVo.setSysRoles(userRoles);
        userDetailVo.setSysOrganizations(userOrgQueryList);

        return userDetailVo;
    }

    @Override
    @Transactional
    public void setUserRoleV2(String username, List<Long> roleId) {
        userRoleMapper.removeStatus(username);
        for (Long role : roleId) {
            UserRole us = userRoleMapper.getRoleByUsernmaeAndRoleId(username, role);
            if (us == null) {
                us = new UserRole();
                Long id = role;
                us.setRoleId(role);
                us.setUserName(username);
                us.setIsDefault(0);
                us.setStatus((byte) 1);
                userRoleMapper.insertSelective(us);
            } else {
                us.setStatus((byte) 1);
                userRoleMapper.updateByPrimaryKeySelective(us);
            }


        }


    }

    @Override
    public void deleteByOrgId(String username) {
        userRoleMapper.deleteByUseridAndRoleid(username, 2);
    }


}
