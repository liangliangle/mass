package com.mass.dto.create;

import com.mass.common.dto.BaseDto;
import com.mass.common.exception.ValidationException;
import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liliang on 2018/2/3.
 */
public class CreateActivitiesDto extends BaseDto {
    /**
     * 名称
     */
    @NotNull(message = "活动名称")
    @ApiModelProperty("活动名称")
    private String name;

    /**
     * 活动地点
     */
    @NotNull(message = "活动地点")
    @ApiModelProperty("活动地点")
    private String place;
    /**
     * 主办社团
     */
    @NotNull(message = "主办社团")
    @ApiModelProperty("主办社团")
    private String sponsorMass;

    /**
     * 举办时间
     */
    @NotNull(message = "举办时间")
    @ApiModelProperty("举办时间")
    private Date holdTime;

    /**
     * 活动类型
     */
    @NotNull(message = "活动类型")
    @ApiModelProperty("活动类型")
    private String type;


    /**
     * 活动摘要
     */
    @ApiModelProperty("摘要")
    private String description;

    /**
     * 文档类型
     */
    @ApiModelProperty("文档类型")
    private Integer doctype;

    /**
     * url或md字符串
     */
    @NotNull(message = "申请书")
    @ApiModelProperty("md字符串")
    private String value;

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

    public Integer getDoctype() {
        return doctype;
    }

    public void setDoctype(Integer doctype) {
        this.doctype = doctype;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void verification() {
        if (this.name == null) {
            throw new ValidationException("活动名称不能为空");
        }
        if (this.place == null) {
            throw new ValidationException("活动地点不能为空");
        }
        if (this.holdTime == null) {
            throw new ValidationException("活动时间不能为空");
        }
        if (this.sponsorMass == null) {
            throw new ValidationException("主办社团不能为空");
        }
        if (this.type == null) {
            throw new ValidationException("活动类型不能为空");
        }
    }
}
