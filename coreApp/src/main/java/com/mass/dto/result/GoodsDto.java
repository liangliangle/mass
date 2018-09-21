package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * goods
 *
 * @author
 */
public class GoodsDto extends BaseDto implements Serializable {
  @ApiModelProperty("状态")
  private Long massId;

  /**
   * s社团名称
   */
  @ApiModelProperty("状态")
  private String massName;

  /**
   * 物资名称
   */
  @ApiModelProperty("状态")
  private String name;

  /**
   * 申请批次ID
   */
  @ApiModelProperty("状态")
  private Long financeId;

  /**
   * 是否自费
   */
  @ApiModelProperty("状态")
  private Integer isExpense;

  /**
   * 是否一次性用品
   */
  @ApiModelProperty("状态")
  private Integer isExpendable;

  /**
   * 数量
   */
  @ApiModelProperty("状态")
  private BigDecimal num;

  /**
   * 单价
   */
  @ApiModelProperty("单价")
  private BigDecimal price;

  /**
   * 状态
   */
  @ApiModelProperty("状态")
  private Integer status;


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

  public Long getFinanceId() {
    return financeId;
  }

  public void setFinanceId(Long financeId) {
    this.financeId = financeId;
  }

  public Integer getIsExpense() {
    return isExpense;
  }

  public void setIsExpense(Integer isExpense) {
    this.isExpense = isExpense;
  }

  public Integer getIsExpendable() {
    return isExpendable;
  }

  public void setIsExpendable(Integer isExpendable) {
    this.isExpendable = isExpendable;
  }

  public BigDecimal getNum() {
    return num;
  }

  public void setNum(BigDecimal num) {
    this.num = num;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}