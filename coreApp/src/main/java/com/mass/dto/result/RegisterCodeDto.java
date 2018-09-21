package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

public class RegisterCodeDto extends BaseDto {

  @ApiModelProperty("二维码信息,需要base64解码")
  private String img;
  @ApiModelProperty("签到ID")
  private Long registerId;
  @ApiModelProperty("签到类型")
  private Long expireTime;

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public Long getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(Long expireTime) {
    this.expireTime = expireTime;
  }

  public Long getRegisterId() {
    return registerId;
  }

  public void setRegisterId(Long registerId) {
    this.registerId = registerId;
  }
}
