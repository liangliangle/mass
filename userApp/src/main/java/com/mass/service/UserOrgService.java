package com.mass.service;

import com.github.pagehelper.PageInfo;
import com.mass.dto.*;
import com.mass.dto.login.ApplyUserOrgDto;
import com.mass.dto.login.UserOrgDto;
import com.mass.dto.login.UserOrgQueryDto;
import com.mass.entity.DataResult;

import java.util.List;

public interface UserOrgService {
  public  List<SysUserOrg> userAndOrgList(String x, String y, Integer offset, Integer limit,Integer status);

    public  int userAndOrgListCount(String x, String y, Integer offset, Integer limit,Integer status);

    public   SysUserOrg getUserById(SysUser sysUser);

    public Integer deleteOrgUser(Long orgId, Long userId);

    public ApplyOrgTemp getCheckUserById(Long id);

  SysUserOrg getUserOrgByID(Long id);

  void updateselective(SysUserOrg sysUserOrg);

  void insertUserOrg(SysUserOrg sysUserOrg);

    public  boolean getusernameorgid(ApplyUserOrgDto sysUserOrg);

  List<UserOrgQueryDto> getcheckList(Long orgId);

  Integer getcheckListCount(Long orgId);

  List<UserOrgQueryDto> getUserOrgByOrgID(Long orgId, Integer offset, Integer limit);

  Integer getCountByOrgID(Long orgId);

  SysUserOrg getOrgByusername(String username);


  UserOrgQueryDto getOrgquerytByusername(SysUser sysUser);

   public UserRole getRoleByUsername(String username);

  boolean getusernameorgidstat(ApplyUserOrgDto sysUserOrg);

  List<SysUser> getUsersBymassNames(List<String> orgName);

    void deleteByOrgId(Long id);

  DataResult getUsersByOrgName(String orgName,Integer offset,Integer limit);

  boolean getusernameorgidstat2(ApplyUserOrgDto sysUserOrg);

  void checkOrgUser(ApplyUserOrgDto sysUserOrg);

  PageInfo<UserOrgQueryDto> checkOrgUserList(Long orgId, Integer page);

  void checkOrgUseFor(Long id, Integer status);

  List<UserOrgDto> myapplyOrgUser(String username, Integer status);

  void deleteMassUser(String username, Long orgId);
}
