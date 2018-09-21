package com.mass.entity;

import com.google.common.io.BaseEncoding;
import com.mass.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * vote_result
 *
 * @author
 */
public class VoteResult extends BaseEntity implements Serializable {


    private Long voteId;

    private String userName;

    private String sno;

    private Long optionId;

    private String option;

    private Integer status;

    private static final long serialVersionUID = 1L;


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