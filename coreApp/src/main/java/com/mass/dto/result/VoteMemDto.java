package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liliang on 2018/2/3.
 */
public class VoteMemDto extends BaseDto implements Serializable {
    /**
     * 投票ID
     */
    @ApiModelProperty("投票ID")
    private Long voteId;

    /**
     * 学号
     */
    @ApiModelProperty("学号")
    private String sno;

    /**
     * 选项
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 投票时间
     */
    @ApiModelProperty("投票时间")
    private Date voteTime;

    /**
     * 是否已投
     */
    @ApiModelProperty("是否已投")
    private Integer isVote;
    @ApiModelProperty("状态")
    private Integer status;

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(Date voteTime) {
        this.voteTime = voteTime;
    }

    public Integer getIsVote() {
        return isVote;
    }

    public void setIsVote(Integer isVote) {
        this.isVote = isVote;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
