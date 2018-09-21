package com.mass.dto.query;

import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liliang on 2018/2/3.
 */
public class ActitQuery {
  /**
   * 名称
   */
  @ApiModelProperty("活动名称")
  private String name;
  /**
   * 主办社团
   */
  @ApiModelProperty("主办社团")
  private String sponsorMass;

  /**
   * 举办时间
   */
  @ApiModelProperty("开始时间")
  private Date holdStartTime;

  @ApiModelProperty("结束时间")
  private Date holdEndTime;

  /**
   * 活动类型
   */
  @ApiModelProperty("活动类型")
  private String type;

  @NotNull(message = "页码")
  private Integer page;

  private Integer status;

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getSponsorMass() {
    return sponsorMass;
  }

  public void setSponsorMass(String sponsorMass) {
    this.sponsorMass = sponsorMass;
  }

  public Date getHoldStartTime() {
    return holdStartTime;
  }

  public void setHoldStartTime(Date holdStartTime) {
    this.holdStartTime = holdStartTime;
  }

  public Date getHoldEndTime() {
    return holdEndTime;
  }

  public void setHoldEndTime(Date holdEndTime) {
    this.holdEndTime = holdEndTime;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }
}
