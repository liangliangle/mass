package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ActivitiesDocDto extends BaseDto implements Serializable {
    /**
     * 活动ID
     */
    @ApiModelProperty("活动ID")
    private Long activitiesId;

    /**
     * 修改建议
     */ @ApiModelProperty("修改建议")
    private String proposal;

    /**
     * 文档类型
     */
    @ApiModelProperty("文档类型")
    private Integer type;

    /**
     * 状态
     */ @ApiModelProperty("状态")
    private Integer status;

    /**
     * url或md字符串
     */
    @ApiModelProperty("url或md字符串")
    private String value;

    public Long getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(Long activitiesId) {
        this.activitiesId = activitiesId;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivitiesDocDto that = (ActivitiesDocDto) o;

        if (activitiesId != null ? !activitiesId.equals(that.activitiesId) : that.activitiesId != null) return false;
        if (proposal != null ? !proposal.equals(that.proposal) : that.proposal != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = activitiesId != null ? activitiesId.hashCode() : 0;
        result = 31 * result + (proposal != null ? proposal.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActivitiesDocDto{" +
                "activitiesId=" + activitiesId +
                ", proposal='" + proposal + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", value='" + value + '\'' +
                '}';
    }
}
