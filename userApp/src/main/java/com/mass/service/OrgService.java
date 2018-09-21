package com.mass.service;

import com.github.pagehelper.PageInfo;
import com.mass.dto.SysOrganization;
import com.mass.dto.SysUserOrg;
import com.mass.dto.login.UserOrgQueryDto;
import com.mass.entity.DataResult;

import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 下午7:13 2018/3/31 2018
 * @Modify:
 */
public interface OrgService {


   public void createOrg(SysOrganization sysOrganization );

   public PageInfo<SysOrganization> getAll(Integer page);

   public  Integer getAllCount();

   public  void  deleteById(Long id);

   public void updateOrg(SysOrganization sysOrganization);

   public   Integer getOrgByOrgNameCount(String orgName);

    Integer getAllByOrgNameCount(String orgName, Integer offset, Integer limit);

    SysOrganization geOrgByID(Long id);

    SysOrganization getOneByOrgName(String orgName);

    DataResult setPersent(Long orgId, String presidentSno);

    void delById(Long id);

    void updateOrgByID(String username,String name,Long orgId);

         SysOrganization getByOrgID(Long orgId);

    void setMassPresent(Long orgId, String username);

    SysOrganization getByID(Long id);

    void delORG(Long id);

    PageInfo<UserOrgQueryDto> seeorgList(Integer page, Long orgId);

    List<SysOrganization> getAllnoPage();
}
