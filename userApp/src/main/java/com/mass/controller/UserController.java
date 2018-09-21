package com.mass.controller;

import com.github.pagehelper.PageInfo;
import com.mass.common.controller.BaseController;
import com.mass.common.exception.BaseException;
import com.mass.dto.PageInfoDto;
import com.mass.dto.SysUser;
import com.mass.dto.login.UpdateUserDto;
import com.mass.dto.vo.UserDetailVo;
import com.mass.entry.query.UserQuery;
import com.mass.service.UserOrgService;
import com.mass.service.UserRoleService;
import com.mass.service.UserService;
import com.mass.util.Check;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@EnableAutoConfiguration
@EnableEurekaClient
@RequestMapping("/api/user")
@Api(value = "用户模块接口")
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    UserOrgService userOrgService;
    @Autowired
    UserRoleService userRoleService;

    /**
     * 查看当前管理员管理用户列表 *
     *
     * @描述
     * @参数 [orgId, offset, limit]
     * @返回值 com.mass.entity.DataResult
     * @创建人 慧强
     * @创建时间 2018/5/6
     * @修改人和其它信息
     */
    // todo  合并到mass/user 合并参数改为orgId
//    @RequestMapping(value = "mass", method = RequestMethod.GET)
//    @ApiOperation(value = "查看社团用户列表")
//    public DataResult getCurrentRolemanagementUserList(@RequestParam String username, @RequestParam Long orgId, @RequestParam Integer offset, @RequestParam Integer limit) {
//        DataResult dataResult = new DataResult();
//        PageQueryResponseVo<UserOrgQueryDto> pageQueryResponseVo = new PageQueryResponseVo();
//        List<UserOrgQueryDto> list = userOrgService.getUserOrgByOrgID(orgId, offset, limit);
//        pageQueryResponseVo.setListData(list);
//        pageQueryResponseVo.setListCount(userOrgService.getCountByOrgID(orgId));
//        dataResult.setData(pageQueryResponseVo);
//        return dataResult;
//    }


    /**
     * 重置密码**
     *
     * @描述
     * @参数 [sysUser]
     * @返回值 com.mass.entity.DataResult
     * @创建人 慧强
     * @创建时间 2018/3/31
     * @修改人和其它信息
     */
    //TODO 增加老密码
    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "pwd", method = RequestMethod.PUT)

    public void pwd(@RequestParam String username, @RequestParam String oldPwd,@RequestParam String newPassword  ) {

           userService.setNewPassword(username,oldPwd,newPassword);
    }
    //重置密码 学号和name
    @ApiOperation(value = "重置密码")
    @RequestMapping(value = "rsetpwd", method = RequestMethod.PUT)

    public void resetpwd(@RequestParam String username, @RequestParam String  name  ) {

        userService.rsetPassword(username,name);
    }

    /**
     * 查看用户社团详情**
     *
     * @描述
     * @参数 [username]
     * @返回值 com.mass.entity.DataResult
     * @创建人 慧强
     * @创建时间 2018/3/31
     * @修改人和其它信息
     */
    // todo 删除
//    @ApiOperation(value = "查看用户社团详情")
//    @GetMapping(value = "mass-detail")
//    public DataResult userOrgDetail(@RequestParam("username") String username, @RequestParam("orgId") Long orgId) {
//        DataResult dataResult = new DataResult();
//        SysUser sysUser = new SysUser();
//        sysUser.setUsername(username);
//        sysUser.setOrganizationId(orgId);
//
//        SysUser sysUser1 = userService.getUserByIdQuery(sysUser);
//        UserOrgQueryDto userOrgQuery = userOrgService.getOrgquerytByusername(sysUser);
//        userOrgQuery.setUname(sysUser1.getName());
//        userOrgQuery.setUsex(sysUser1.getSex());
//        userOrgQuery.setEmail(sysUser1.getEmail());
//        userOrgQuery.setPhone(sysUser1.getPhone());
//
//        dataResult.setData(userOrgQuery);
//        return dataResult;
//    }

    /**
     * 查看用户详情**
     *
     * @描述
     * @参数 [username]
     * @返回值 com.mass.entity.DataResult
     * @创建人 慧强
     * @创建时间 2018/3/31
     * @修改人和其它信息
     */

    @ApiOperation(value = "指定username查看用户详情")
    @GetMapping(value ="username")
    public SysUser userDetail(@RequestParam("username") String username) {
        if (username==null){
            throw new BaseException("账号不能为空");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        SysUser sysUser1 = userService.getUserByName(sysUser);
        return sysUser1;
    }

    /**
     * 用户信息修改*
     *
     * @描述
     * @参数 [sysUser]
     * @返回值 com.mass.entity.DataResult
     * @创建人 慧强
     * @创建时间 2018/3/31
     * @修改人和其它信息
     */
    @ApiOperation(value = "更改用户信息")
    @PutMapping(value = "")
    public void updateUser(@RequestBody UpdateUserDto sysUser) {
        Check.checkEmpty(sysUser);
        userService.updateUser(sysUser);

    }



    @RequestMapping(value = "mass-name", method = RequestMethod.POST)
    @ApiOperation(value = "根据社团名称查找用户信息")
    public List<SysUser> getUsersBymassNames(@RequestBody List<String> orgName) {
        return userOrgService.getUsersBymassNames(orgName);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "查看所有用户")
    public PageInfo<SysUser> getAlls(@RequestBody UserQuery userQuery) {
        return userService.getAlls(userQuery);
    }
//    //todo 待删除
//    @RequestMapping(value = "org/name", method = RequestMethod.GET)
//    @ApiOperation(value = "根据社团名称查找用户信息")
//    public DataResult getUsersBymassname(@RequestParam String orgName, @RequestParam Integer offset, @RequestParam Integer limit) {
//        return userOrgService.getUsersByOrgName(orgName, offset, limit);
//    }
//     //待删除
//    @RequestMapping(value = "role/list", method = RequestMethod.GET)
//    @ApiOperation(value = "查看全部成员，角色查询")
//    public DataResult userListInRole(@RequestParam(value = "username", required = false) String username, @RequestParam Integer offset, @RequestParam Integer limit) {
//        return userRoleService.userListInRole(username, offset, limit);
//
//    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ApiOperation(value = "查看用户的详细信息查询")
    public UserDetailVo userListInRole(@RequestParam(value = "username", required = true) String username) {
        UserDetailVo userDetailVo= userRoleService.userInRoleDetail(username);
        userDetailVo.setPassword(null);
        return userDetailVo;
    }
    @RequestMapping(value = "head-img", method = RequestMethod.POST)
    @ApiOperation(value = "设置用户头像")
    public  void  setUserImg( @RequestParam String username,@RequestParam String url){
        userService.setUserImg(username,url);




    }


}
