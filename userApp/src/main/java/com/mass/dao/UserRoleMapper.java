package com.mass.dao;

import com.mass.dto.SysRole;
import com.mass.dto.SysUser;
import com.mass.dto.UserRole;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface UserRoleMapper {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_role
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_role
   *
   * @mbggenerated
   */
  int insert(UserRole record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_role
   *
   * @mbggenerated
   */
  int insertSelective(UserRole record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_role
   *
   * @mbggenerated
   */
  UserRole selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_role
   *
   * @mbggenerated
   */
  int updateByPrimaryKeySelective(UserRole record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table user_role
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(UserRole record);

  UserRole getRoleByID(SysUser sysUser);

  UserRole getRoleByUsernmae(@Param("username") String user);

  List<SysRole> getSysUserByUserName(String userName);

  UserRole getByDefault(String username);


     public int getrole(@Param("username")String username, @Param("roleId")Long roleId);

  UserRole getRoleByUsernmaeAndRoleId(@Param("username")String username, @Param("roleId")Long roleId);

  void deleteByUsernameAndRoleId(@Param("username")String username, @Param("roleId")Long roleId);

    void updateByPrimaryKeySelectiveur(UserRole us);

  void deleteByUseridAndRoleid(@Param("username")String username, @Param("roleId") Integer roleId);

    int  persentRoleBysno(@Param("username")String presidentSno,@Param("roleId") int i);

  void removeStatus(@Param("username")String username);

    void updateAllDefault(@Param("username")String username);
}