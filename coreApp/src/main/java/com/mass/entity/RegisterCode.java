package com.mass.entity;

import com.mass.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * register_code
 *
 * @author
 */
public class RegisterCode extends BaseEntity implements Serializable {

  /**
   * 签到id
   */
  private Long registerId;

  /**
   * 签到码
   */
  private String code;

  /**
   * 过期时间
   */
  private Date endTime;

  /**
   * 状态
   */
  private Integer status;

  private static final long serialVersionUID = 1L;


  public Long getRegisterId() {
    return registerId;
  }

  public void setRegisterId(Long registerId) {
    this.registerId = registerId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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
}