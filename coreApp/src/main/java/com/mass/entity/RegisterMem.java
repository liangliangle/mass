package com.mass.entity;

import com.mass.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * register_mem
 *
 * @author
 */
public class RegisterMem extends BaseEntity implements Serializable {

  /**
   * 签到id
   */
  private Long registerId;

  /**
   * 学生ID
   */
  private String sno;

  private String name;

  private String classes;
  /**
   * 签到码
   */
  private String code;

  /**
   * 签到类型
   */
  private String type;

  /**
   * 备注
   */
  private String comments;

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

  public String getSno() {
    return sno;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getClasses() {
    return classes;
  }

  public void setClasses(String classes) {
    this.classes = classes;
  }

  public void setSno(String sno) {
    this.sno = sno;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}