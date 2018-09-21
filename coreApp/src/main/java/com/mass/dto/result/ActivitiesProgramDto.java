package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import com.mass.entity.ProgramMember;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liliang on 2018/1/31.
 */
public class ActivitiesProgramDto extends BaseDto implements Serializable {
  /**
   * 节目名称
   */
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

  /**
   * 状态
   */
  @ApiModelProperty("状态")
  private Integer status;

  private List<ProgramMemberDto> programMemberDtoList;

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

  public List<ProgramMemberDto> getProgramMemberDtoList() {
    return programMemberDtoList;
  }

  public void setProgramMemberDtoList(List<ProgramMemberDto> programMemberDtoList) {
    this.programMemberDtoList = programMemberDtoList;
  }
}
