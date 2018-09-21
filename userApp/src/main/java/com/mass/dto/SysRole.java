package com.mass.dto;

import java.util.Date;

public class SysRole {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.role
     *
     * @mbggenerated
     */
    private String role;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.resource_ids_str
     *
     * @mbggenerated
     */
    private String resourceIdsStr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.available
     *
     * @mbggenerated
     */
    private Boolean available;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.role_level
     *
     * @mbggenerated
     */
    private Integer roleLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.createTime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.updateTime
     *
     * @mbggenerated
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.id
     *
     * @return the value of sys_role.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.id
     *
     * @param id the value for sys_role.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.role
     *
     * @return the value of sys_role.role
     *
     * @mbggenerated
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.role
     *
     * @param role the value for sys_role.role
     *
     * @mbggenerated
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.description
     *
     * @return the value of sys_role.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.description
     *
     * @param description the value for sys_role.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.resource_ids_str
     *
     * @return the value of sys_role.resource_ids_str
     *
     * @mbggenerated
     */
    public String getResourceIdsStr() {
        return resourceIdsStr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.resource_ids_str
     *
     * @param resourceIdsStr the value for sys_role.resource_ids_str
     *
     * @mbggenerated
     */
    public void setResourceIdsStr(String resourceIdsStr) {
        this.resourceIdsStr = resourceIdsStr == null ? null : resourceIdsStr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.available
     *
     * @return the value of sys_role.available
     *
     * @mbggenerated
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.available
     *
     * @param available the value for sys_role.available
     *
     * @mbggenerated
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.role_level
     *
     * @return the value of sys_role.role_level
     *
     * @mbggenerated
     */
    public Integer getRoleLevel() {
        return roleLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.role_level
     *
     * @param roleLevel the value for sys_role.role_level
     *
     * @mbggenerated
     */
    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.createTime
     *
     * @return the value of sys_role.createTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.createTime
     *
     * @param createtime the value for sys_role.createTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.updateTime
     *
     * @return the value of sys_role.updateTime
     *
     * @mbggenerated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.updateTime
     *
     * @param updatetime the value for sys_role.updateTime
     *
     * @mbggenerated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}