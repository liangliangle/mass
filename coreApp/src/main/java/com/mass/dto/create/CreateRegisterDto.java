package com.mass.dto.create;

import com.mass.common.dto.BaseDto;
import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CreateRegisterDto extends BaseDto {


    /**
     * 签到类型
     */
    @NotNull(message = "签到类型")
    @ApiModelProperty("签到类型")
    private String type;

    @NotNull(message = "创建人")
    private String sno;

    @NotNull(message = "签到名称")
    private String name;

    /**
     * 活动ID
     */
    @ApiModelProperty("活动ID")
    private Long activitiesId;

    /**
     * 备注
     */

    @NotNull(message = "备注")
    @ApiModelProperty("备注")
    private String comments;

    /**
     * 开始时间
     */

    @NotNull(message = "开始时间")
    @ApiModelProperty("开始时间")
    private Date startTime;

    /**
     * 结束时间
     */

    @NotNull(message = "结束时间")
    @ApiModelProperty("结束时间")
    private Date endTime;

    /**
     * 加分量
     */
    @ApiModelProperty("加分量")
    private Integer mark;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(Long activitiesId) {
        this.activitiesId = activitiesId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
