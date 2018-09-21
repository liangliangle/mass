package com.mass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mass.common.exception.BaseException;
import com.mass.dao.SysOrganizationMapper;
import com.mass.dao.SysUserOrgMapper;
import com.mass.dto.*;
import com.mass.dto.login.ApplyUserOrgDto;
import com.mass.dto.login.UserOrgDto;
import com.mass.dto.login.UserOrgQueryDto;
import com.mass.entity.DataResult;
import com.mass.entity.PageQueryResponseVo;
import com.mass.service.UserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserOrgServiceImpl implements UserOrgService {
  @Autowired
  SysUserOrgMapper sysUserOrgMapper;
  @Autowired
  SysOrganizationMapper sysOrganizationMapper;

  @Override
  public List<SysUserOrg> userAndOrgList(String x, String y, Integer offset, Integer limit, Integer status) {

    return sysUserOrgMapper.selectUserAndOrgList(x, y, offset, limit, status);
  }

  @Override
  public int userAndOrgListCount(String x, String y, Integer offset, Integer limit, Integer status) {
    return sysUserOrgMapper.selectUserAndOrgListCount(x, y, offset, limit, status);
  }

  @Override
  public SysUserOrg getUserById(SysUser sysUser) {
    return sysUserOrgMapper.selectByUserName(sysUser);

  }

  @Override
  public Integer deleteOrgUser(Long orgId, Long userId) {
    return sysUserOrgMapper.deleteOrgUser(orgId, userId);
  }

  @Override
  public ApplyOrgTemp getCheckUserById(Long id) {
    return sysUserOrgMapper.getcheckUserByID(id);
  }

  @Override
  public SysUserOrg getUserOrgByID(Long id) {
    return sysUserOrgMapper.getUserOrgByID(id);
  }

  @Override
  public void updateselective(SysUserOrg sysUserOrg) {
    sysUserOrgMapper.updateByPrimaryKeySelective(sysUserOrg);

  }

  @Override
  public void insertUserOrg(SysUserOrg sysUserOrg) {
    sysUserOrgMapper.insert(sysUserOrg);
  }

  @Override
  public boolean getusernameorgid(ApplyUserOrgDto sysUserOrg) {
    int x = sysUserOrgMapper.getcountbyUsernameAndOrgId(sysUserOrg);
    return x > 0 ? true : false;
  }

  @Override
  public List<UserOrgQueryDto> getcheckList(Long orgId) {
    List<UserOrgQueryDto> list = sysUserOrgMapper.getCheckList(orgId);
    return list;


  }

  @Override
  public Integer getcheckListCount(Long orgId) {
    return sysUserOrgMapper.getCheckListCount(orgId);
  }

  @Override
  public List<UserOrgQueryDto> getUserOrgByOrgID(Long orgId, Integer offset, Integer limit) {
    return sysUserOrgMapper.getUserOrgByOrgID(orgId, offset, limit);
  }

  @Override
  public Integer getCountByOrgID(Long orgId) {
    return sysUserOrgMapper.getCountByOrgId(orgId);
  }

  @Override
  public SysUserOrg getOrgByusername(String username) {
    return sysUserOrgMapper.getUserOrgByUsername(username);
  }

  @Override
  public UserOrgQueryDto getOrgquerytByusername(SysUser sysUser) {
    return sysUserOrgMapper.getqueryBYusername(sysUser);
  }

  @Override
  public UserRole getRoleByUsername(String username) {
    return sysUserOrgMapper.getRoleByUsername(username);
  }

  @Override
  public boolean getusernameorgidstat(ApplyUserOrgDto sysUserOrg) {
    int x = sysUserOrgMapper.getcountbyUsernameAndOrgIdstatus(sysUserOrg);
    return x > 0 ? true : false;
  }

  @Override
  public List<SysUser> getUsersBymassNames(@RequestBody List<String> orgName) {
    List<String> list = sysUserOrgMapper.getUsersBymassNames(orgName);
    if (list.size() == 0 || list == null) {
      return new ArrayList<SysUser>();
    }
    return sysUserOrgMapper.getUserSByusernames(list);
  }

  @Override
  public void deleteByOrgId(Long id) {

    sysUserOrgMapper.deleteByOrgID(id);


  }

  @Override
  public DataResult getUsersByOrgName(String orgName,Integer offset,Integer limit) {
       DataResult dataResult =new DataResult();
    PageQueryResponseVo<UserOrgQueryDto> pageQueryResponseVo =new PageQueryResponseVo();
          List<UserOrgQueryDto> list=  sysUserOrgMapper.getUsersByOrgName(orgName,offset,limit);

           pageQueryResponseVo.setListData(list);
           pageQueryResponseVo.setListCount(sysUserOrgMapper.getUsersByOrgNamecounts(orgName,offset,limit));
             dataResult.setData(pageQueryResponseVo);

    return dataResult;
  }

  @Override
  public boolean getusernameorgidstat2(ApplyUserOrgDto sysUserOrg) {
    int x = sysUserOrgMapper.getcountbyUsernameAndOrgIdstatus2(sysUserOrg);
    return x > 0 ? true : false;
  }

  @Override
  @Transactional
  public void checkOrgUser(ApplyUserOrgDto sysUserOrg) {
    if (this.getusernameorgid(sysUserOrg)) {

      throw  new BaseException("请勿重复提交");

    }
    if (this.getusernameorgidstat2(sysUserOrg)) {
      throw  new BaseException("已经发出加入信息请勿提交");

    }

    if (this.getusernameorgidstat(sysUserOrg)) {

      throw  new BaseException("已经是该社团成员");

    }
     SysUserOrg s=  new SysUserOrg();
    s.setUsername(sysUserOrg.getUsername());
    s.setOrgName(sysUserOrg.getOrgName());
    s.setOrgId( String.valueOf(sysUserOrg.getOrgId()));
    s.setApplyReason(sysUserOrg.getApplyReason());

    s.setStatus((byte) -1);
    s.setCreateTime(new Date());
    s.setUpdateTime(new Date());
    s.setLocked((byte) 0);
    this.insertUserOrg(s);
  }

  @Override
  public PageInfo<UserOrgQueryDto> checkOrgUserList(Long orgId, Integer page) {
    PageHelper.startPage(page,10);
    List<UserOrgQueryDto> list = this.getcheckList(orgId);

    return new PageInfo<>(list);
  }

  @Override
  @Transactional
  public void checkOrgUseFor(Long id, Integer status) {
    if (id == null || status==null) {

      throw   new  BaseException("指定参数不能为空");
    }
    SysUserOrg sysUserOrg = this.getUserOrgByID(id);
    //获取需要审核的某个转态为-1
    if (status == 0) {
      //拒绝
      sysUserOrg.setStatus((byte) 0);
      this.updateselective(sysUserOrg);
    } else if (status == 1) {
      sysUserOrg.setStatus((byte) 1);
      sysUserOrg.setSuccessTime(new Date());
      //表示通过
      this.updateselective(sysUserOrg);
    }
  }

  @Override
  public List<UserOrgDto> myapplyOrgUser(String username, Integer status) {
    return sysUserOrgMapper.getmyapplyOrgUser(username,status);
  }

  @Override
  @Transactional
  public void deleteMassUser(String username, Long orgId) {
           int c= sysOrganizationMapper.userIsPresent(username,orgId);
           if(c>0){
             throw  new BaseException("该用户为该社团社长，不可删除");
           }
    sysUserOrgMapper.deleteMassUser(username,orgId);
  }


}
