package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liliang on 2018/1/31.
 */
public class NoticeDto extends BaseDto implements Serializable {

    /**
     * 通知名称
     */
    @ApiModelProperty("通知名称")
    private String name;

    /**
     * 通知范围
     */
    @ApiModelProperty("通知范围")
    private String range;
    private String sno;
    private String userName;
    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endTime;

    /**
     * 是否包含子组织
     */
    @ApiModelProperty("是否包含子组织")
    private Integer isCoveSon;

    /**
     * 摘要
     */
    @ApiModelProperty("摘要")
    private String description;

    /**
     * 活动ID
     */
    @ApiModelProperty("活动ID")
    private Long activitiesId;
    @ApiModelProperty("状态")
    private Integer status;


    private String thetext;

    private Integer redundancy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsCoveSon() {
        return isCoveSon;
    }

    public void setIsCoveSon(Integer isCoveSon) {
        this.isCoveSon = isCoveSon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(Long activitiesId) {
        this.activitiesId = activitiesId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getThetext() {
        return thetext;
    }

    public void setThetext(String thetext) {
        this.thetext = thetext;
    }

    public Integer getRedundancy() {
        return redundancy;
    }

    public void setRedundancy(Integer redundancy) {
        this.redundancy = redundancy;
    }
}
