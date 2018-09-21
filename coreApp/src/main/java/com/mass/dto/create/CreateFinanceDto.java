package com.mass.dto.create;

import com.mass.common.dto.BaseDto;
import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * finance
 *
 * @author
 */
public class CreateFinanceDto extends BaseDto implements Serializable {

  @ApiModelProperty("活动ID")
  private Long activitiesId;

  /**
   * 活动名称
   */
  @ApiModelProperty("活动名称")
  private String activitiesName;
  @NotNull(message = "申请名称")
  @ApiModelProperty("名称")
  private String name;
  /**
   * 申请时间
   */
  @ApiModelProperty("申请时间")
  private Date times;

  /**
   * 总额
   */
  @ApiModelProperty("总额")
  private BigDecimal totalFree;

  /**
   * 类型
   */
  @NotNull(message = "类型")
  @ApiModelProperty("类型")
  private String type;

  /**
   * 社团ID
   */
  @NotNull(message = "社团参数不正确")
  @ApiModelProperty("社团ID")
  private Long massId;

  /**
   * 社团名称
   */
  @NotNull(message = "社团名称")
  @ApiModelProperty("社团名称")
  private String massName;

  private List<CreateGoodsDto> goodsDtoList;


  private static final long serialVersionUID = 1L;


  public Long getActivitiesId() {
    return activitiesId;
  }

  public void setActivitiesId(Long activitiesId) {
    this.activitiesId = activitiesId;
  }

  public String getActivitiesName() {
    return activitiesName;
  }

  public void setActivitiesName(String activitiesName) {
    this.activitiesName = activitiesName;
  }

  public Date getTimes() {
    return times;
  }

  public void setTimes(Date times) {
    this.times = times;
  }

  public BigDecimal getTotalFree() {
    return totalFree;
  }

  public void setTotalFree(BigDecimal totalFree) {
    this.totalFree = totalFree;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getMassId() {
    return massId;
  }

  public void setMassId(Long massId) {
    this.massId = massId;
  }

  public String getMassName() {
    return massName;
  }

  public void setMassName(String massName) {
    this.massName = massName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<CreateGoodsDto> getGoodsDtoList() {
    return goodsDtoList;
  }

  public void setGoodsDtoList(List<CreateGoodsDto> goodsDtoList) {
    this.goodsDtoList = goodsDtoList;
  }
}