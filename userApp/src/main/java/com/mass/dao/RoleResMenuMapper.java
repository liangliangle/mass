package com.mass.dao;

import com.mass.dto.RoleResMenu;

public interface RoleResMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_res_menu
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_res_menu
     *
     * @mbggenerated
     */
    int insert(RoleResMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_res_menu
     *
     * @mbggenerated
     */
    int insertSelective(RoleResMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_res_menu
     *
     * @mbggenerated
     */
    RoleResMenu selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_res_menu
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RoleResMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_res_menu
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RoleResMenu record);
}