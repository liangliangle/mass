package com.mass.dto.query;

import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class NoticeQuery {


  /**
   * 通知名称
   */
  @ApiModelProperty("通知名称")
  private String name;


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

  private Integer status;

  @NotNull(message = "页码")
  private Integer page;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }
}
