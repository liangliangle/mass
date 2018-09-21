package com.mass.dto.create;

import com.mass.common.dto.BaseDto;
import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CreateNoticeDto extends BaseDto implements Serializable {


  /**
   * 通知名称
   */
  @NotNull(message = "通知名称")
  @ApiModelProperty("通知名称")
  private String name;

  @ApiModelProperty("通知范围")
  private String rangeString;
  /**
   * 通知范围
   */

  @NotNull(message = "通知范围")
  @ApiModelProperty("通知范围")
  private List<String> range;
  @NotNull(message = "创建人")
  @ApiModelProperty("创建人")
  private String sno;
  @NotNull(message = "创建人")
  @ApiModelProperty("创建人")
  private String userName;
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
  /**
   * 正文
   */
  @NotNull(message = "通知正文")
  private String thetext;

  public String getRangeString() {
    return rangeString;
  }

  public void setRangeString(String rangeString) {
    this.rangeString = rangeString;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getRange() {
    return range;
  }

  public void setRange(List<String> range) {
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

  public void setStatus(Integer status) {
    this.status = status;
  }


  public String getThetext() {
    return thetext;
  }

  public void setThetext(String thetext) {
    this.thetext = thetext;
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

  public Integer getStatus() {
    return status;
  }
}
