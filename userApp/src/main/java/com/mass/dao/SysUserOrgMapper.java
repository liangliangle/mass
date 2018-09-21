package com.mass.dao;

import com.mass.dto.*;

import java.util.List;

import com.mass.dto.login.ApplyUserOrgDto;
import com.mass.dto.login.UserOrgDto;
import com.mass.dto.login.UserOrgQueryDto;
import org.apache.ibatis.annotations.Param;
public interface SysUserOrgMapper {
    int countByExample(SysUserOrgExample example);

    int deleteByExample(SysUserOrgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserOrg record);

    int insertSelective(SysUserOrg record);

    List<SysUserOrg> selectByExample(SysUserOrgExample example);

    SysUserOrg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserOrg record, @Param("example") SysUserOrgExample example);

    int updateByExample(@Param("record") SysUserOrg record, @Param("example") SysUserOrgExample example);

    int updateByPrimaryKeySelective(SysUserOrg record);

    int updateByPrimaryKey(SysUserOrg record);

    List<SysUserOrg> selectUserAndOrgList(@Param("x") String x, @Param("y") String y, @Param("offset") Integer offset, @Param("limit") Integer limit,@Param("status") Integer status);

    int selectUserAndOrgListCount(@Param("x") String x, @Param("y") String y, @Param("offset") Integer offset, @Param("limit") Integer limit,@Param("status") Integer status);

    public  SysUserOrg selectByUserName(SysUser sysUser);

    public Integer deleteOrgUser(@Param("orgId") Long orgId,@Param("username") Long userId);

   public ApplyOrgTemp getcheckUserByID(@Param("id") Long id);

     public SysUserOrg getUserOrgByID(@Param("id") Long id);

      public  int getcountbyUsernameAndOrgId(ApplyUserOrgDto sysUserOrg);

    public  int getcountbyUsernameAndOrgIdstatus(ApplyUserOrgDto sysUserOrg);

      public  List<UserOrgQueryDto> getCheckList(@Param("orgId")Long orgId);

      public Integer getCheckListCount(@Param("orgId")Long orgId);

      public List<UserOrgQueryDto> getUserOrgByOrgID(@Param("orgId") Long orgId, @Param
          ("offset")Integer offset, @Param("limit")Integer limit);

      public Integer getCountByOrgId(  @Param("orgId")  Long orgId);

      public SysUserOrg getUserOrgByUsername(@Param("username") String username);

      public UserOrgQueryDto getqueryBYusername(SysUser sysUser);

      public  List<UserOrgQueryDto> getAllByOrgName(@Param("orgId")Long orgId);
      public  Integer getAllByOrgNameCount(@Param("orgName")String orgName, @Param("offset")Integer offset, @Param("limit")Integer limit);

       public UserRole getRoleByUsername(@Param("username") String username);

      public List<String> getUsersBymassNames(@Param("orgNames") List<String> orgName);

      public  List<SysUser> getUserSByusernames( @Param("usernames") List<String> list);

    public      void deleteByOrgID(@Param("id") Long id);

    List<UserOrgQueryDto> getUsersByOrgName(@Param("orgName")String orgName, @Param("offset")Integer offset, @Param("limit")Integer limit);

    Integer getUsersByOrgNamecounts(@Param("orgName")String orgName, @Param("offset")Integer offset, @Param("limit")Integer limit);

    int getcountbyUsernameAndOrgIdstatus2(ApplyUserOrgDto sysUserOrg);

    SysUser getuserByUsername(@Param("username") String username);

     List<UserOrgDto> getmyapplyOrgUser(@Param("username") String username, @Param("status") Integer status) ;

    void deleteMassUser(@Param("username") String username, @Param("orgId") Long orgId);
}