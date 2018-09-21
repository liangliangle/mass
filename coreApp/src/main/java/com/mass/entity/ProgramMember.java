package com.mass.entity;

import com.mass.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class ProgramMember   extends BaseEntity implements Serializable {

    /**
     * 活动ID
     */
    private Long activitiesId;

    /**
     * 节目ID
     */
    private Long programId;

    /**
     * 节目名称
     */
    private String programName;

    /**
     * 学号
     */
    private String sno;

    /**
     * 参与类型
     */
    private String type;

    private String username;

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;


    public Long getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(Long activitiesId) {
        this.activitiesId = activitiesId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
