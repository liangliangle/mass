package com.mass.dto;

import java.util.Date;

public class SysUserOrg {
    private Integer id;

    private String username;

    private String orgId;

    private Byte status;

    private Byte position;

    private Byte locked;

    private Date createTime;

    private Date updateTime;

    private Date exitTime;

    private String exitReason;

    private String gradeClass;

    private String orgName;
    private String applyReason;
    private  Date successTime;
    public SysUserOrg(Integer id, String username, String orgId, Byte status, Byte position, Byte locked, Date createTime, Date updateTime, Date exitTime, String exitReason, String gradeClass, String orgName, String applyReason,Date successTime) {
        this.id = id;
        this.username = username;
        this.orgId = orgId;
        this.status = status;
        this.position = position;
        this.locked = locked;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.exitTime = exitTime;
        this.exitReason = exitReason;
        this.gradeClass = gradeClass;
        this.orgName = orgName;
        this.applyReason = applyReason;
        this.successTime=successTime;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public SysUserOrg() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getPosition() {
        return position;
    }

    public void setPosition(Byte position) {
        this.position = position;
    }

    public Byte getLocked() {
        return locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public String getExitReason() {
        return exitReason;
    }

    public void setExitReason(String exitReason) {
        this.exitReason = exitReason == null ? null : exitReason.trim();
    }

    public String getGradeClass() {
        return gradeClass;
    }

    public void setGradeClass(String gradeClass) {
        this.gradeClass = gradeClass == null ? null : gradeClass.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }
}