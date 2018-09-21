package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class VoteResultDto extends BaseDto {
    @ApiModelProperty("投票时间")
    private Date createTime;
    @ApiModelProperty("投票ID")
    private Long voteId;
    @ApiModelProperty("姓名")
    private String userName;
    @ApiModelProperty("学号")
    private String sno;
    @ApiModelProperty("选项ID")
    private Long optionId;
    @ApiModelProperty("选项")
    private String option;
    @ApiModelProperty("状态")
    private Integer status;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getVoteId() {

        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
