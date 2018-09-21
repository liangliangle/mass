package com.mass.controller;

import com.github.pagehelper.PageInfo;
import com.mass.common.controller.BaseController;
import com.mass.common.exception.BaseException;
import com.mass.dao.SysUserMapper;
import com.mass.dto.ApplyOrgTemp;
import com.mass.dto.SysOrganization;
import com.mass.dto.SysUserOrg;
import com.mass.dto.login.ApplyDTO;
import com.mass.dto.login.ApplyUserOrgDto;
import com.mass.dto.login.UserOrgDto;
import com.mass.dto.login.UserOrgQueryDto;
import com.mass.service.OrgService;
import com.mass.service.ApplyOrgService;
import com.mass.service.UserOrgService;
import com.mass.service.UserRoleService;
import com.mass.util.Check;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 上午9:38 2018/3/312018
 * @Modi
 */

@Api(value = "社团管理" )
@RestController
@EnableAutoConfiguration
@EnableEurekaClient
@RequestMapping("/api/mass")
public class OrgController  extends BaseController {


    @Autowired
    public OrgService orgService;
    @Autowired
    ApplyOrgService   applyOrgService;
    @Autowired
    UserOrgService  userOrgService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    SysUserMapper sysUserMapper;
    /**  用户申请社团 *
     *
     *@描述

     *@参数 [sysOrganization]

     *@返回值 com.mass.entity.DataResult

     *@创建人 慧强

     *@创建时间 2018/3/31

     *@修改人和其它信息

     */
    @RequestMapping(value = "" ,method = RequestMethod.POST)
    @ApiOperation(value = "普通用户申请创建社团")
    public void   applyOrg ( @RequestBody ApplyDTO applyDTO){
          Check.checkEmpty(applyDTO);
          List<String> list =new ArrayList<>();
           list.add(applyDTO.getPresidentSno());
           list.add(applyDTO.getUsno1());
           list.add(applyDTO.getUsno2());
           list.add(applyDTO.getUsno3());
        List<String> l= new ArrayList<>();

        for (String x: list ) {
               if (l.size()==0) {
                   l.add(x);
               }else {
                   for (String y : l) {
                         if (x.equals(y)){
                             throw new BaseException("学号存在相同！");
                         }

                   }
               }
        }
        int  flag= sysUserMapper.isExist(list);
          if (flag==0){
              throw new BaseException("请检查你输入的学号是否存在！");
          }



           if(applyOrgService.selectusernameordID(applyDTO)) {
             throw new BaseException("已申请请勿重复提交！");
         }
        applyOrgService.insertApplyTemp(applyDTO);

    }

    @RequestMapping(value = "check" ,method = RequestMethod.GET)
    @ApiOperation(value = "查看申请的社团列表")
      public PageInfo<ApplyOrgTemp> seeApplyList( @RequestParam("page") int page, @RequestParam(value = "status",required = true) Integer status){
         return     applyOrgService.seeApplyList(page,status);
    }

    @RequestMapping(value = "check" ,method = RequestMethod.POST)
    @ApiOperation(value = "审核新创建的社团")
        public  void checkApplyOrgTempList(@RequestParam Long id , @RequestParam  Integer status){
             //2通过，1拒绝
            applyOrgService.checkApplyOrgTempList(id,status);
    }


    @RequestMapping(value = "mycreate" ,method = RequestMethod.GET)
    @ApiOperation(value = "查看我的申请社团列表")
    public  List<ApplyOrgTemp>   seeApplyList(@RequestParam String username,@RequestParam(value = "status",required = false)Integer status ){
        return     applyOrgService.seeApplymyList( username,status);
    }
    @RequestMapping(value = "mycreate/{id}" ,method = RequestMethod.GET)
    @ApiOperation(value = "查看我的申请社团详情")
    public  ApplyOrgTemp   seeApplyList(@PathVariable("id") Long id){
        return     applyOrgService.seeApplymyDetailById(id);
    }



    @RequestMapping(value = "" ,method = RequestMethod.GET)
    @ApiOperation(value = "查看社团列表")
    public PageInfo<SysOrganization> OrderList(@RequestParam Integer page){
       return  orgService.getAll(page);
    }

    @RequestMapping(value = "all" ,method = RequestMethod.GET)
    @ApiOperation(value = "全部社团列表不带分页")
    public      List<SysOrganization> OrderListall()
    {
        return  orgService.getAllnoPage();
    }


    @PutMapping(value = "" )
    @ApiOperation(value = "更新社团信息",consumes = "application/json")
    public void updateOrg( @RequestBody SysOrganization sysOrganization){
        orgService.updateOrg(sysOrganization);
    }
    @RequestMapping(value = "user" ,method = RequestMethod.GET)
    @ApiOperation(value = "查看某社团人员列表")
    public PageInfo<UserOrgQueryDto> seeorgList(@RequestParam Long  orgId,@RequestParam Integer page){
                    return         orgService.seeorgList(page,orgId);
    }
    @RequestMapping(value = "{id}" ,method = RequestMethod.GET)
    @ApiOperation(value = "根据orgid查找社团")
    public SysOrganization seeorgList(  @PathVariable Long  id){
        return orgService.geOrgByID(id);
    }

    @RequestMapping(value = "org-name" ,method = RequestMethod.GET)
    @ApiOperation(value = "根据社团名称查找社团")

    public SysOrganization seeorgList(  @RequestParam  String  orgName){

        return  orgService.getOneByOrgName(orgName);
    }


    @RequestMapping(value = "masspresent", method = RequestMethod.PUT)
    @ApiOperation(value = "指定社长")
    public    void   setMassPresent( @RequestParam  Long orgId, @RequestParam String username){
        orgService.setMassPresent(orgId,username);
    }
    @RequestMapping(value = "org", method = RequestMethod.POST)
    @ApiOperation(value = "申请加某个社团")
    public void checkOrgUser(@RequestBody ApplyUserOrgDto applyUserOrgDto) {
                        Check.checkEmpty(applyUserOrgDto);
                       userOrgService.checkOrgUser(applyUserOrgDto);
    }
    //todo move至社团管理
    @RequestMapping(value = "org/check", method = RequestMethod.GET)
    @ApiOperation(value = "审核申请加入的社团成员列列表")
    public PageInfo<UserOrgQueryDto> checkOrgUserList(@RequestParam Long orgId, @RequestParam Integer page) {
        return      userOrgService.checkOrgUserList(orgId,page);
    }

    //todo move至社团管理
    @RequestMapping(value = "org/check/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "审核申请加入的社团成员")
    public void checkOrgUser(@PathVariable Long id, @RequestParam Integer status) {

           userOrgService.checkOrgUseFor(id,status);

    }
    @RequestMapping(value = "org/myapplys", method = RequestMethod.POST)
    @ApiOperation(value = "我的加入所有社团列表")
    public List<UserOrgDto> myapplyOrgUser(@RequestParam   String username, @RequestParam(value = "status",required = false)   Integer status) {
         return    userOrgService.myapplyOrgUser(username,status);
    }
    @RequestMapping(value = "org/user", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除社团成员")
     public  void  deleteMassUser(@RequestParam  Long orgId, @RequestParam String username){
        userOrgService.deleteMassUser(username,orgId);

    }

}
