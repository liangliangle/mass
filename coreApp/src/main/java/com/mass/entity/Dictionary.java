package com.mass.entity;

import com.mass.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class Dictionary extends BaseEntity implements Serializable {


  /**
   * 字典Key值
   */
  private String dKey;

  private Integer status;

  /**
   * value值
   */
  private String value;

  private static final long serialVersionUID = 1L;

  public String getdKey() {
    return dKey;
  }

  public void setdKey(String dKey) {
    this.dKey = dKey;
  }


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}