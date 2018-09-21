package com.mass.service;

import com.mass.dto.SysRole;
import com.mass.dto.UserRole;
import com.mass.dto.vo.UserDetailVo;
import com.mass.entity.DataResult;

import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 上午10:08 2018/5/1 2018
 * @Modify:
 */
public interface UserRoleService {


        public  UserRole getUserRole(String sysUser);


    void insertUserRole(UserRole userRole);

    void updateSelective(UserRole userRole);

    boolean getUserRoleCount(String username, Long roleId);

    void insertNewUserRole(String username, Long roleId);

    void deleteByUsernameAndRoleId(String username, Long roleId);

    void deleteByUseridAndRoleid(String presidentSno, int i);

    int persentRoleBysno(String presidentSno, int i);

    List<SysRole> getAllFilte();

    DataResult userListInRole(String username, Integer offset, Integer limit);

    UserDetailVo userInRoleDetail(String username);

    void setUserRoleV2(String username, List<Long> roleId);

    void deleteByOrgId(String username);
}
