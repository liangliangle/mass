package com.mass.entity;

import com.mass.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class ActivitiesProgram extends BaseEntity implements Serializable {

  /**
   * 活动ID
   */
  private Long activitiesId;
  /**
   * 节目名称
   */
  private String name;
  /**
   * 所属社团
   */

  private String mass;

  /**
   * 社团ID
   */
  private Long massId;

  /**
   * 状态
   */
  private Integer status;

  private static final long serialVersionUID = 1L;


  public Long getActivitiesId() {
    return activitiesId;
  }

  public void setActivitiesId(Long activitiesId) {
    this.activitiesId = activitiesId;
  }

  public String getMass() {
    return mass;
  }

  public void setMass(String mass) {
    this.mass = mass;
  }

  public Long getMassId() {
    return massId;
  }

  public void setMassId(Long massId) {
    this.massId = massId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


}