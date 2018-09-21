package com.mass.dto;

import java.util.Date;

public class SysOrganization {
    private Long id;

    private String name;

    private Long parentId;

    private String parentIds;

    private String petName;

    private String logoUrl;

    private String presidentName;

    private String presidentSno;

    private Boolean available;
    private Date createTime;

    private Date updateTime;

    public SysOrganization(Long id, String name, Long parentId, String parentIds, String petName, String logoUrl, String presidentName, String presidentSno, Boolean available, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.petName = petName;
        this.logoUrl = logoUrl;
        this.presidentName = presidentName;
        this.presidentSno = presidentSno;
        this.available = available;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public SysOrganization() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName == null ? null : petName.trim();
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public String getPresidentName() {
        return presidentName;
    }

    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName == null ? null : presidentName.trim();
    }

    public String getPresidentSno() {
        return presidentSno;
    }

    public void setPresidentSno(String presidentSno) {
        this.presidentSno = presidentSno == null ? null : presidentSno.trim();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}