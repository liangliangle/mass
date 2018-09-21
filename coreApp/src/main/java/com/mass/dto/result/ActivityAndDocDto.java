package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import com.mass.entity.Activities;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author 李亮亮
 */
public class ActivityAndDocDto extends BaseDto {

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 活动地点
     */
    @ApiModelProperty("地点")
    private String place;

    /**
     * 活动学期
     */
    @ApiModelProperty("地点")
    private String semesterName;

    /**
     * 主办社团
     */
    @ApiModelProperty("主办社团")
    private String sponsorMass;

    /**
     * 举办时间
     */
    @ApiModelProperty("举办时间")
    private Date holdTime;

    /**
     * 活动类型
     */
    @ApiModelProperty("活动类型")
    private String type;

    /**
     * 审核次数
     */
    @ApiModelProperty("审核次数")
    private Integer review;

    /**
     * 活动摘要
     */
    @ApiModelProperty("活动摘要")
    private String description;

    /**
     * 参加人数
     */
    @ApiModelProperty("参加人数")
    private Integer joinPeople;

    /**
     * 观众人数
     */
    @ApiModelProperty("观众人数")
    private Integer audience;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer status;
    /**
     * 文档类型
     */
    @ApiModelProperty("文档类型")
    private Integer docType;


    /**
     * url或md字符串
     */
    @ApiModelProperty("url或md字符串")
    private String value;

    private String proposal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getSponsorMass() {
        return sponsorMass;
    }

    public void setSponsorMass(String sponsorMass) {
        this.sponsorMass = sponsorMass;
    }

    public Date getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(Date holdTime) {
        this.holdTime = holdTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getJoinPeople() {
        return joinPeople;
    }

    public void setJoinPeople(Integer joinPeople) {
        this.joinPeople = joinPeople;
    }

    public Integer getAudience() {
        return audience;
    }

    public void setAudience(Integer audience) {
        this.audience = audience;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDocType() {
        return docType;
    }

    public void setDocType(Integer docType) {
        this.docType = docType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ActivityAndDocDto() {

    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }
}
