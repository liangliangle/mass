package com.mass.dto.query;

import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

public class FinMassQuery {
  @ApiModelProperty("社团ID")
  private Long massId;
  @ApiModelProperty("type，自费，报销")
  private String type;
  @ApiModelProperty("状态")
  private Integer status;

  @NotNull(message = "页码")
  private Integer page;

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Long getMassId() {
    return massId;
  }

  public void setMassId(Long massId) {
    this.massId = massId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
