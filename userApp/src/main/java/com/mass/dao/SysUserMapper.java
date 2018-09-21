package com.mass.dao;

import com.mass.dto.SysUser;
import com.mass.dto.SysUserExample;

import java.util.List;

import com.mass.dto.UserRole;
import com.mass.dto.login.UpdateUserDto;
import com.mass.dto.vo.UserDetailVo;

import com.mass.entry.query.UserQuery;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKeySelective2(UpdateUserDto record);

    int updateByPrimaryKey(SysUser record);

    int getCountByUserAndPass(SysUser sysUser);

    SysUser getById(SysUser sysUser);

    void updateUser(SysUser sysUser);

    public SysUser getUserByName(SysUser sysUser);

    int insertUserList(List<SysUser> list);

    List<SysUser> getuserList(@Param("org") String org);

    List<SysUser> getAllByOrg(@Param("org") String orgName, @Param("limit") Integer limit, @Param("offset") Integer offset);

    Integer getAllByOrgCount(@Param("org") String orgName);

    SysUser selectByUsername(SysUser sysUser);

    UserRole getbyid(@Param("id") int i);

    List<SysUser> getAlls(UserQuery userQuery);

    Long countAlls(UserQuery userQuery);

    void setNewPass(@Param("username") String username,@Param("newPassword") String newPassword);

    List<SysUser> getuserListInRole(@Param("username")String username, @Param("offset")Integer offset, @Param("limit")Integer limit);

    Integer userListInRoleCount(@Param("username")String username,@Param("offset") Integer offset, @Param("limit")Integer limit);

    UserDetailVo getUserByUserName(@Param("username")String username);

    int getCountByUsernameAndName(@Param("username")String username,@Param("name") String name);

    int isExist( @Param("list")  List<String> list);

    void setUserImg(@Param("username")String username,@Param("url") String url);
}