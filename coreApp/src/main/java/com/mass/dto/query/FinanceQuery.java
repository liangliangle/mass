package com.mass.dto.query;

import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class FinanceQuery {

  @ApiModelProperty("名称")
  private String name;
  /**
   * 申请时间
   */
  @ApiModelProperty("申请时间")
  private Date startTimes;
  @ApiModelProperty("申请时间")
  private Date endTimes;

  /**
   * 类型
   */
  @ApiModelProperty("类型")
  private String type;

  /**
   * 社团ID
   */
  @ApiModelProperty("社团ID")
  private Long massId;

  /**
   * 社团名称
   */
  @ApiModelProperty("社团名称")
  private String massName;
  @ApiModelProperty("状态")
  private Integer status;

  @NotNull(message = "页码")
  private Integer page;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartTimes() {
    return startTimes;
  }

  public void setStartTimes(Date startTimes) {
    this.startTimes = startTimes;
  }

  public Date getEndTimes() {
    return endTimes;
  }

  public void setEndTimes(Date endTimes) {
    this.endTimes = endTimes;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getMassId() {
    return massId;
  }

  public void setMassId(Long massId) {
    this.massId = massId;
  }

  public String getMassName() {
    return massName;
  }

  public void setMassName(String massName) {
    this.massName = massName;
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
