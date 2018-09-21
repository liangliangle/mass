package com.mass.dto.create;

import com.mass.common.dto.BaseDto;
import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

public class CreateOptionDto extends BaseDto {
  /**
   * 顺序
   */
  @ApiModelProperty("顺序")
  private Integer orders;

  /**
   * 选项值
   */
  @NotNull(message = "选项")
  @ApiModelProperty("选项值")
  private String thetext;

  @ApiModelProperty("状态")
  private Integer status;

  public Integer getOrders() {
    return orders;
  }

  public void setOrders(Integer orders) {
    this.orders = orders;
  }

  public String getThetext() {
    return thetext;
  }

  public void setThetext(String thetext) {
    this.thetext = thetext;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
