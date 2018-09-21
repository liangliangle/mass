package com.mass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mass.common.exception.BaseException;
import com.mass.dao.ApplyOrgTempMapper;
import com.mass.dao.SysOrganizationMapper;
import com.mass.dao.SysUserOrgMapper;
import com.mass.dto.ApplyOrgTemp;
import com.mass.dto.SysOrganization;
import com.mass.dto.SysUserOrg;
import com.mass.dto.UserRole;
import com.mass.dto.login.ApplyDTO;
import com.mass.entity.DataResult;
import com.mass.entity.PageQueryResponseVo;
import com.mass.service.ApplyOrgService;
import com.mass.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 下午4:31 2018/4/2 2018
 * @Modify:
 */
@Service
public class ApplyOrgServiceIimpl implements ApplyOrgService {
    @Autowired
    ApplyOrgTempMapper applyOrgTempMapper;
    @Autowired
    SysOrganizationMapper sysOrganizationMapper;
    @Autowired
    SysUserOrgMapper sysUserOrgMapper;
    @Autowired
    UserRoleService userRoleService;

    @Override
    @Transactional
    public Integer insertApplyTemp(ApplyDTO applyOrgTemp) {
        return applyOrgTempMapper.insertApplyDto(applyOrgTemp);

    }

    @Override
    public boolean selectusernameordID(ApplyDTO applyOrgTemp) {
        int x= applyOrgTempMapper.getusernameandorgid(applyOrgTemp);


        return x>0?true:false;
    }

    @Override
    public PageInfo<ApplyOrgTemp> seeApplyList(Integer offset, Integer status) {
        PageHelper.startPage(offset, 10);

             List<ApplyOrgTemp> ls=applyOrgTempMapper.getByStatus(status);
            PageInfo<ApplyOrgTemp>  pageInfo= new PageInfo(ls);
        return pageInfo;
    }




    @Override
    @Transactional
    public void checkApplyOrgTempList(Long  id, Integer status) {

             if (status==1){
             //拒绝
                 applyOrgTempMapper.upStatusById(id,1);

             }else if(status==2){
                  ApplyOrgTemp applyOrgTemp = applyOrgTempMapper.selectByPrimaryKey(id);
                 applyOrgTempMapper.upStatusById(id,2);
                 SysOrganization sysOrganization =new SysOrganization();
                 sysOrganization.setPresidentSno(applyOrgTemp.getPresidentSno());
                 sysOrganization.setPresidentName(applyOrgTemp.getPresidentName());
                 sysOrganization.setName(applyOrgTemp.getName());
                 sysOrganization.setPetName(applyOrgTemp.getPetName());
                 sysOrganization.setAvailable(true);
                 sysOrganization.setCreateTime(new Date());
                 sysOrganization.setUpdateTime(new Date());
                 sysOrganizationMapper.insert(sysOrganization);

                  SysUserOrg u1= new SysUserOrg();
                  u1.setLocked((byte)0);
                  u1.setStatus((byte)1);
                  u1.setOrgId(String.valueOf(sysOrganization.getId()));
                  u1.setCreateTime(new Date());
                  u1.setUpdateTime(new Date());
                  u1.setUsername(applyOrgTemp.getPresidentSno());
                  u1.setOrgName(applyOrgTemp.getName());
                 int flag = userRoleService.persentRoleBysno(applyOrgTemp.getPresidentSno(),2);
                 if (flag>0){
                     throw  new BaseException("该主要申请人以为其他角色社长，不可添加");
                 }
                 UserRole userRole =new UserRole();
                      userRole.setRoleId(2l);
                      userRole.setUserName(applyOrgTemp.getPresidentSno());
                      userRole.setCreateTime(new Date());
                      userRole.setUpdateTime(new Date());
                      userRole.setIsDefault(0);
                   userRoleService.insertUserRole(userRole);

                  SysUserOrg u2= new SysUserOrg();
                 u2.setLocked((byte)0);
                 u2.setStatus((byte)1);
                 u2.setOrgId(String.valueOf(sysOrganization.getId()));
                 u2.setCreateTime(new Date());
                 u2.setUpdateTime(new Date());
                 u2.setUsername(applyOrgTemp.getUsno1());
                 u2.setOrgName(applyOrgTemp.getName());

                 SysUserOrg u3= new SysUserOrg();
                 u3.setLocked((byte)0);
                 u3.setStatus((byte)1);
                 u3.setOrgId(String.valueOf(sysOrganization.getId()));
                 u3.setCreateTime(new Date());
                 u3.setUpdateTime(new Date());
                 u3.setUsername(applyOrgTemp.getUsno2());
                 u3.setOrgName(applyOrgTemp.getName());
                 SysUserOrg u4= new SysUserOrg();
                 u4.setLocked((byte)0);
                 u4.setStatus((byte)1);
                 u4.setOrgId(String.valueOf(sysOrganization.getId()));
                 u4.setCreateTime(new Date());
                 u4.setUpdateTime(new Date());
                 u4.setUsername(applyOrgTemp.getUsno3());
                 u4.setOrgName(applyOrgTemp.getName());
                 sysUserOrgMapper.insert(u1);
                 sysUserOrgMapper.insert(u2);
                 sysUserOrgMapper.insert(u3);
                 sysUserOrgMapper.insert(u4);
             }else{
                 throw  new BaseException("审核出现错误");
             }


    }

    @Override
    public List<ApplyOrgTemp> seeApplymyList(String username,Integer status) {
        return    applyOrgTempMapper.seeApplymyList(username,status);
    }

    @Override
    public ApplyOrgTemp seeApplymyDetailById(Long id) {
        return applyOrgTempMapper.selectByPrimaryKey(id);
    }
}
