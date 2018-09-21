package com.mass.dto.login;

import com.mass.common.dto.BaseDto;

import java.util.Date;

public class UserOrgDto extends BaseDto {

    private String orgId;

    private Byte status;

    private Byte position;


    private Date createTime;
    private Date successTime;


    private String name;
    private String petName;
    private String presidentName;

    private String presidentSno;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPresidentName() {
        return presidentName;
    }

    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }

    public String getPresidentSno() {
        return presidentSno;
    }

    public void setPresidentSno(String presidentSno) {
        this.presidentSno = presidentSno;
    }
}
