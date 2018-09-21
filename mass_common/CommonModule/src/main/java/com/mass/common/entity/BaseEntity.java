package com.mass.common.entity;

import com.mass.common.utils.MassUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class BaseEntity implements Serializable {

  private Long id;

    private Date createTime;

    private Date lastModifyTime;
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public void buildDefaultTimeStamp() {
    setCreateTime(MassUtils.getCurrentTimestamp());
    setLastModifyTime(MassUtils.getCurrentTimestamp());
  }
}
