package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class RegisterDto extends BaseDto {

  /**
   * 签到类型
   */
  @ApiModelProperty("签到类型")
  private String type;

  /**
   * 活动ID
   */
  @ApiModelProperty("")
  private Long activitiesId;
  @ApiModelProperty("sno")
  private String sno;
  @ApiModelProperty("name")
  private String name;

  /**
   * 备注
   */
  @ApiModelProperty("签到类型")
  private String comments;

  /**
   * 开始时间
   */
  @ApiModelProperty("签到类型")
  private Date startTime;

  /**
   * 结束时间
   */
  @ApiModelProperty("签到类型")
  private Date endTime;

  /**
   * 加分量
   */
  @ApiModelProperty("签到类型")
  private Integer mark;

  /**
   * 状态
   */
  @ApiModelProperty("状态")
  private Integer status;

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
