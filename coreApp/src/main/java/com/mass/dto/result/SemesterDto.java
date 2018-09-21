package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liliang on 2018/2/1.
 * 学期
 */
public class SemesterDto extends BaseDto  implements Serializable {

    /**
     * 学期名称
     */
    @ApiModelProperty("学期名称")
    private String name;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date startTime;

    /**
     * 结束时间
     */ @ApiModelProperty("结束时间")
    private Date endTime;

    /**
     * 是否当前学期
     */ @ApiModelProperty("是否当前学期")
    private Integer isCurrent;

    /**
     * 状态
     */ @ApiModelProperty("状态")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Integer isCurrent) {
        this.isCurrent = isCurrent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
