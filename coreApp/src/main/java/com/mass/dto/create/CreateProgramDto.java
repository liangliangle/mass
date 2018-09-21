package com.mass.dto.create;

import com.mass.common.dto.BaseDto;
import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class CreateProgramDto extends BaseDto {

  /**
   * 节目名称
   */
  @NotNull(message = "节目名称")
  @ApiModelProperty("节目名称")
  private String name;
  /**
   * 活动ID
   */

  @ApiModelProperty("活动ID")
  private Long activitiesId;

  /**
   * 所属社团
   */
  @ApiModelProperty("所属社团")
  private String mass;

  /**
   * 社团ID
   */
  @ApiModelProperty("社团ID")
  private Long massId;
  @NotNull(message = "人员")

  @ApiModelProperty("人员列表")
  private List<CreateProgramMemDto> programMemDtoList;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

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

  public List<CreateProgramMemDto> getProgramMemDtoList() {
    return programMemDtoList;
  }

  public void setProgramMemDtoList(List<CreateProgramMemDto> programMemDtoList) {
    this.programMemDtoList = programMemDtoList;
  }
}
