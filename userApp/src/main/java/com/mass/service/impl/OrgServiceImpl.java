package com.mass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mass.common.exception.BaseException;
import com.mass.dao.SysOrganizationMapper;
import com.mass.dao.SysUserMapper;
import com.mass.dao.SysUserOrgMapper;
import com.mass.dto.SysOrganization;
import com.mass.dto.SysUser;
import com.mass.dto.SysUserOrg;
import com.mass.dto.UserRole;
import com.mass.dto.login.UserOrgQueryDto;
import com.mass.entity.DataResult;
import com.mass.service.OrgService;
import com.mass.service.UserOrgService;
import com.mass.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 下午7:13 2018/3/31 2018
 * @Modify:
 */
@Service
public class OrgServiceImpl  implements OrgService{
    @Autowired
    private SysOrganizationMapper sysOrganizationMapper;
    @Autowired
    SysUserOrgMapper sysUserOrgMapper;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UserOrgService userOrgService;
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public void createOrg(SysOrganization  sysOrganization ) {
          int x= sysOrganizationMapper.insert(sysOrganization);

    }

    @Override
    public PageInfo<SysOrganization> getAll(Integer page) {
        PageHelper.startPage(page,10);


        return new PageInfo<SysOrganization>(sysOrganizationMapper.getAll());
    }

    @Override
    public Integer getAllCount() {
        return sysOrganizationMapper.getAllCount();
    }

    @Override
    public void deleteById(Long id) {
         sysOrganizationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateOrg(SysOrganization sysOrganization) {
        sysOrganizationMapper.updateByPrimaryKeySelective(sysOrganization);
    }

    @Override
    public Integer getOrgByOrgNameCount(String Orgname) {
        return sysOrganizationMapper.getOrgByOrgNameCount( Orgname);
    }



    @Override
    public Integer getAllByOrgNameCount(String orgName, Integer offset, Integer limit) {
        return sysUserOrgMapper.getAllByOrgNameCount(orgName,offset,limit);
    }

    @Override
    public SysOrganization geOrgByID(Long id) {
        return sysOrganizationMapper.getUserOrgByID(id);
    }

    @Override
    public SysOrganization getOneByOrgName(String orgName) {
        return sysOrganizationMapper.getOneByOrgName(orgName);
    }

    @Override
    public DataResult setPersent(Long orgId, String presidentSno) {
             SysOrganization sysOrganization =new SysOrganization();
             sysOrganization.setId(orgId);
             sysOrganization.setPresidentSno(presidentSno);
             int x=sysOrganizationMapper.updateByPrimaryKeySelective(sysOrganization);
              return  new DataResult();
    }

    @Override
    public void delById(Long id) {
           sysOrganizationMapper.delById(id);

    }

    @Override
    public void updateOrgByID(String username,String name, Long orgId) {
        sysOrganizationMapper.updateOrgByID(username,name,orgId);
    }

    @Override
    public SysOrganization getByOrgID(Long orgId) {
        return sysOrganizationMapper.selectByPrimaryKey(orgId);
    }

    @Override
    @Transactional
    public void setMassPresent(Long orgId, String username) {
        SysOrganization sysOrganization =sysOrganizationMapper.selectByPrimaryKey(orgId);
        if (sysOrganization ==null){
            throw  new BaseException("社团不存在");
        }
        userRoleService.deleteByUseridAndRoleid(sysOrganization.getPresidentSno(),2);
        SysUser  sysUser =  sysUserOrgMapper.getuserByUsername(username);
        sysOrganizationMapper.updateOrgByID(sysUser.getUsername(),sysUser.getName(),orgId);
        UserRole userRole =new UserRole();
        userRole.setRoleId(2l);
        userRole.setUserName(username);
        userRole.setCreateTime(new Date());
        userRole.setUpdateTime(new Date());
        userRole.setIsDefault(0);
        userRoleService.insertUserRole(userRole);



    }

    @Override
    public SysOrganization getByID(Long id) {
        return sysOrganizationMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delORG(Long Id) {
        SysOrganization sysOrganization =this.getByID(Id);
        this.delById(Id);
        userRoleService.deleteByOrgId(sysOrganization.getPresidentSno());
        userOrgService.deleteByOrgId(Id);
    }

    public PageInfo<UserOrgQueryDto> seeorgList(Integer page, Long orgId) {
            PageHelper.startPage(page,10);
                List<UserOrgQueryDto>  list=  sysUserOrgMapper.getAllByOrgName(orgId);
                return  new PageInfo<>(list);
    }

    @Override
    public List<SysOrganization> getAllnoPage() {
      return   sysOrganizationMapper.getAll();
    }


}
