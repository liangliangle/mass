package com.mass.dto.update;

import com.mass.common.dto.BaseDto;
import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class AuditFinanceDto extends BaseDto implements Serializable {

  /**
   * 申请时间
   */
  @ApiModelProperty("状态")
  @NotNull(message = "签到码")
  private Integer status;

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
