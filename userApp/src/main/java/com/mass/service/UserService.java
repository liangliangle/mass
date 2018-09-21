package com.mass.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mass.common.exception.BaseException;
import com.mass.common.exception.ServiceException;
import com.mass.common.model.MassJwtModel;
import com.mass.common.service.JwtAuthenticationServiceImpl;
import com.mass.dao.SysOrganizationMapper;
import com.mass.dao.SysRoleMapper;
import com.mass.dao.SysUserMapper;
import com.mass.dao.UserRoleMapper;
import com.mass.dto.*;
import com.mass.dto.login.LoginDto;
import com.mass.dto.login.UpdateUserDto;
import com.mass.entry.query.UserQuery;
import com.mass.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private SysOrganizationMapper organizationMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private JwtAuthenticationServiceImpl jwtAuthenticationService;
    @Autowired
    private RoleResourceService resourceService;

    public boolean selectByNameAndPass(SysUser sysUser) {
        int s = sysUserMapper.getCountByUserAndPass(sysUser);
        return true;
    }

    public boolean userIsExist(SysUser sysUser) {
        int x = sysUserMapper.getCountByUserAndPass(sysUser);
        if (x > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public LoginDto login(String userName, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userName);
        SysUser user1 = getById(sysUser);
        //TODO MD5
        password = md5(password);
        if (user1 == null || !user1.getPassword().equals(password)) {
            throw new ServiceException("用户名或密码错误");
        }

        LoginDto dto = new LoginDto();
        user1.setPassword(null);
        dto.setUser(user1);
        List<SysRole> sysRoles = userRoleMapper.getSysUserByUserName(userName);
        //角色列表
        if (sysRoles.size() == 0 || sysRoles == null) {
            UserRole userRole = new UserRole();
            userRole.setUserName(userName);
            userRole.setRoleId(8L);
            userRole.setRoleName("初次登录系统默认角色");
            userRole.setIsDefault(1);
            userRole.setCreateTime(new Date());
            userRole.setUpdateTime(new Date());
            userRole.setDescription("初始化进入系统角色");
            userRoleMapper.insert(userRole);
            sysRoles = new ArrayList<>();
            sysRoles.add(roleMapper.selectByPrimaryKey(8L));
            dto.setRoles(sysRoles);
        } else {
            dto.setRoles(sysRoles);
        }

        List<SysOrganization> organizations = organizationMapper.getByuserName(userName);
        dto.setOrgs(organizations);
        //默认角色当前角色
        UserRole role = userRoleMapper.getByDefault(userName);
        if (role == null) {
            dto.setCurrentRole(sysRoles.get(0));
        } else {
            dto.setCurrentRole(roleMapper.selectByPrimaryKey(role.getRoleId()));
        }
        if (dto.getCurrentRole().getRole().equals("社长")) {
            dto.setManageOrg(organizationMapper.getMyManage(userName));
        }
        dto.setTocken(getTocken(dto));
        dto.setMenus(resourceService.getResource(dto.getCurrentRole().getId()));
        return dto;
    }

    public SysUser getById(SysUser sysUser) {
        return sysUserMapper.getById(sysUser);
    }

    @Transactional
    public void updateUser(UpdateUserDto sysUser) {
        sysUserMapper.updateByPrimaryKeySelective2(sysUser);

    }

    public SysUser getUserByName(SysUser sysUser) {
        return sysUserMapper.getUserByName(sysUser);
    }
  @Transactional
    public void insertUserList(List<SysUser> list) {
        for (SysUser s: list) {
            UserRole userRole = new UserRole();
            userRole.setUserName(s.getUsername());
            userRole.setRoleId(8L);
            userRole.setRoleName("初次登录系统默认角色");
            userRole.setIsDefault(1);
            userRole.setCreateTime(new Date());
            userRole.setUpdateTime(new Date());
            userRole.setDescription("初始化进入系统角色");
            userRoleMapper.insert(userRole);
        }
        int count = sysUserMapper.insertUserList(list);
    }

    public List<SysUser> getuserList(String name) {
        return sysUserMapper.getuserList(name);
    }

    public void exportUserByQuery(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, List<SysUser> list) throws IOException {
        ExcelUtil.exportExcel(httpServletRequest, httpServletResponse, list);

    }

    public List<SysUser> getAllByOrg(String orgName, Integer limit, Integer offset) {


        return sysUserMapper.getAllByOrg(orgName, limit, offset);
    }

    public Integer getCountByOrg(String orgName) {

        return sysUserMapper.getAllByOrgCount(orgName);

    }

    public UserRole getRoleById(SysUser sysUser) {
        return userRoleMapper.getRoleByID(sysUser);

    }

    public SysUser getUserByIdQuery(SysUser sysUser) {
        return sysUserMapper.selectByUsername(sysUser);


    }

    public UserRole getbyID(int i) {

        return sysUserMapper.getbyid(i);


    }

    private String getTocken(LoginDto dto) {
        MassJwtModel model = new MassJwtModel();
        model.setUserName(dto.getUser().getName());
        model.setId(dto.getUser().getId());
        model.setSno(dto.getUser().getUsername());
        model.setRoles(String.valueOf(dto.getCurrentRole().getId()));
        model.setCurrentRole(Long.toString(dto.getCurrentRole().getId()));
        return jwtAuthenticationService
                .generateToken(model, 20000000);
    }

    public PageInfo<SysUser> getAlls(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPage(), 10);
        return new PageInfo<SysUser>(sysUserMapper.getAlls(userQuery));
    }

    @Transactional
    public LoginDto tockenLogin(String auth, Long roleId) {

        MassJwtModel massJwtModel = jwtAuthenticationService.getAuthenticatedUser(auth);

        SysUser sysUser = new SysUser();
        sysUser.setUsername(massJwtModel.getSno());
        SysUser user1 = getById(sysUser);
        LoginDto dto = new LoginDto();
        user1.setPassword(null);
        dto.setUser(user1);

        List<SysRole> sysRoles = userRoleMapper.getSysUserByUserName(massJwtModel.getSno());
        dto.setRoles(sysRoles);
        UserRole us = new UserRole();
        us.setRoleId(Long.valueOf(massJwtModel.getCurrentRole()));
        us.setUserName(user1.getUsername());
        us.setIsDefault(0);
        userRoleMapper.updateByPrimaryKeySelectiveur(us);
        userRoleMapper.updateAllDefault(user1.getUsername());
        us.setRoleId(roleId);
        us.setIsDefault(1);
        userRoleMapper.updateByPrimaryKeySelectiveur(us);
        List<SysOrganization> organizations = organizationMapper.getByuserName(massJwtModel.getSno());
        dto.setOrgs(organizations);
        //默认角色当前角色
        UserRole role = userRoleMapper.getByDefault(massJwtModel.getSno());
        if (role == null) {
            dto.setCurrentRole(sysRoles.get(0));
        } else {
            dto.setCurrentRole(roleMapper.selectByPrimaryKey(role.getRoleId()));
        }
        if (dto.getCurrentRole().getRole().equals("社长")) {
            dto.setManageOrg(organizationMapper.getMyManage(massJwtModel.getSno()));
        }
        dto.setTocken(getTocken(dto));
        dto.setMenus(resourceService.getResource(dto.getCurrentRole().getId()));
        return dto;


    }

    @Transactional
    public void setNewPassword(String username, String old, String newPassword) {

        if (ObjectUtils.isEmpty(username)) {
            throw new BaseException("参数不能为空");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(md5(old));
        int x = sysUserMapper.getCountByUserAndPass(sysUser);
        if (x > 0) {
            newPassword = md5(newPassword);
            sysUserMapper.setNewPass(username, newPassword);

        } else {
            throw new BaseException("账号密码错误不能修改密码！");
        }


    }

    private String md5(String s) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            return base64en.encode(md5.digest(s.getBytes("utf-8")));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new ServiceException("修改密码失败");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ServiceException("修改密码失败");
        }
    }

    public void rsetPassword(String username, String name) {
        int x = sysUserMapper.getCountByUsernameAndName(username, name);
        if (x > 0) {
            String newPassword = md5("123456");
            sysUserMapper.setNewPass(username, newPassword);

        } else {


            throw new BaseException("重置密码失败，请重试");
        }


    }

    @Transactional
    public void setUserImg(String username, String url) {


        sysUserMapper.setUserImg(username, url);

    }
}
