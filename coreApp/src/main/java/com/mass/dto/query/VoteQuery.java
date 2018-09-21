package com.mass.dto.query;

import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class VoteQuery {


  /**
   * 创建人
   */
  @ApiModelProperty("创建人")
  private String sno;

  /**
   * 投票
   */
  @ApiModelProperty("投票")
  private String name;

  @ApiModelProperty("开始时间")
  private Date startTime;
  @ApiModelProperty("结束时间")
  private Date endTime;
  @ApiModelProperty("状态")
  private Integer status;

  @NotNull(message = "页码")
  private Integer page;

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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }
}
