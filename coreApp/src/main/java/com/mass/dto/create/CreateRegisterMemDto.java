package com.mass.dto.create;

import com.mass.common.dto.BaseDto;
import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

public class CreateRegisterMemDto extends BaseDto {

  @NotNull(message = "签到ID")
  @ApiModelProperty("签到id")
  private Long registerId;

  /**
   * 学生ID
   */

  @NotNull(message = "学号")
  @ApiModelProperty("学生ID")
  private String sno;

  @NotNull(message = "姓名")
  @ApiModelProperty("学生姓名")
  private String name;
  @NotNull(message = "姓名")
  @ApiModelProperty("班级")
  private String classes;

  /**
   * 签到码
   */
  @ApiModelProperty("签到码")
  private String code;

  /**
   * 签到类型
   */
  @ApiModelProperty("签到类型")
  private String type;

  /**
   * 备注
   */
  @ApiModelProperty("备注")
  private String comments;

  public Long getRegisterId() {
    return registerId;
  }

  public void setRegisterId(Long registerId) {
    this.registerId = registerId;
  }

  public String getSno() {
    return sno;
  }

  public void setSno(String sno) {
    this.sno = sno;
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
}
