package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by liliang on 2018/2/1.
 */
public class ProgramMemberDto extends BaseDto implements Serializable {


    /**
     * 活动ID
     */
    @ApiModelProperty("活动ID")
    private Long activitiesId;

    /**
     * 节目ID
     */
    @ApiModelProperty("节目ID")
    private Long programId;

    /**
     * 节目名称
     */
    @ApiModelProperty("节目状态")
    private String programName;

    /**
     * 学号
     */
    @ApiModelProperty("学号")
    private String sno;

    /**
     * 参与类型
     */
    @ApiModelProperty("参与类型")
    private String type;

    private String name;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}