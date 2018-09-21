package com.mass.entity;

import com.mass.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * register
 *
 * @author
 */
public class Register extends BaseEntity implements Serializable {

  /**
   * 签到类型
   */
  private String type;

  private String name;

  /**
   * 活动ID
   */
  private Long activitiesId;
  private String sno;

  /**
   * 备注
   */
  private String comments;

  /**
   * 开始时间
   */
  private Date startTime;

  /**
   * 结束时间
   */
  private Date endTime;

  /**
   * 加分量
   */
  private Integer mark;

  /**
   * 状态
   */
  private Integer status;

  public String getSno() {
    return sno;
  }

  public void setSno(String sno) {
    this.sno = sno;
  }

  private static final long serialVersionUID = 1L;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
}