package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by liliang on 2018/2/1.
 */
public class OptionsDto extends BaseDto implements Serializable {

  /**
   * 投票ID
   */
  @ApiModelProperty("投票ID")
  private Long voteId;

  /**
   * 顺序
   */
  @ApiModelProperty("顺序")
  private Integer orders;

  /**
   * 选项值
   */
  @ApiModelProperty("选项值")
  private String thetext;

  @ApiModelProperty("状态")
  private Integer status;

  public Long getVoteId() {
    return voteId;
  }

  public void setVoteId(Long voteId) {
    this.voteId = voteId;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    OptionsDto that = (OptionsDto) o;

    if (voteId != null ? !voteId.equals(that.voteId) : that.voteId != null) {
      return false;
    }
    if (orders != null ? !orders.equals(that.orders) : that.orders != null) {
      return false;
    }
    if (thetext != null ? !thetext.equals(that.thetext) : that.thetext != null) {
      return false;
    }
    return status != null ? status.equals(that.status) : that.status == null;
  }

  @Override
  public int hashCode() {
    int result = voteId != null ? voteId.hashCode() : 0;
    result = 31 * result + (orders != null ? orders.hashCode() : 0);
    result = 31 * result + (thetext != null ? thetext.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "OptionsDto{" +
        "voteId=" + voteId +
        ", orders=" + orders +
        ", thetext='" + thetext + '\'' +
        ", status=" + status +
        '}';
  }
}
