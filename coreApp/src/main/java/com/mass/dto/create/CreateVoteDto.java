package com.mass.dto.create;

import com.mass.common.dto.BaseDto;
import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class CreateVoteDto extends BaseDto {
  /**
   * 创建人
   */
  @NotNull(message = "创建人")
  @ApiModelProperty("创建人")
  private String sno;
  /**
   * 创建人姓名
   */
  @NotNull(message = "创建人")
  @ApiModelProperty("创建人")
  private String stuName;
  /**
   * 投票
   */
  @NotNull(message = "名称")
  @ApiModelProperty("投票")
  private String name;

  /**
   * 是否匿名
   */
  @NotNull(message = "是否匿名")
  @ApiModelProperty("是否匿名")
  private Integer isAnon;

  /**
   * 可投数量
   */
  @NotNull(message = "可投数量")
  @ApiModelProperty("可投数量")
  private Integer voteNum;

  /**
   * 投票范围
   */
  @NotNull(message = "投票范围")
  @ApiModelProperty("投票范围")
  private List<String> range;

  /**
   * 是否包含子组织
   */
  @ApiModelProperty("是否包含子组织")
  private Integer isCoveSon;
  @NotNull(message = "开始时间")
  @ApiModelProperty("开始时间")
  private Date startTime;

  @NotNull(message = "结束时间")
  @ApiModelProperty("结束时间")
  private Date endTime;

  @NotNull(message = "选项")
  @ApiModelProperty("选项")
  List<CreateOptionDto> optionsDtos;

  public String getSno() {
    return sno;
  }

  public void setSno(String sno) {
    this.sno = sno;
  }

  public String getStuName() {
    return stuName;
  }

  public void setStuName(String stuName) {
    this.stuName = stuName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getIsAnon() {
    return isAnon;
  }

  public void setIsAnon(Integer isAnon) {
    this.isAnon = isAnon;
  }

  public Integer getVoteNum() {
    return voteNum;
  }

  public void setVoteNum(Integer voteNum) {
    this.voteNum = voteNum;
  }

  public List<String> getRange() {
    return range;
  }

  public void setRange(List<String> range) {
    this.range = range;
  }

  public Integer getIsCoveSon() {
    return isCoveSon;
  }

  public void setIsCoveSon(Integer isCoveSon) {
    this.isCoveSon = isCoveSon;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public List<CreateOptionDto> getOptionsDtos() {
    return optionsDtos;
  }

  public void setOptionsDtos(List<CreateOptionDto> optionsDtos) {
    this.optionsDtos = optionsDtos;
  }
}
