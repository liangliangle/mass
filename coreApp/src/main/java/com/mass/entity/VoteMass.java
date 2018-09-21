package com.mass.entity;

import com.mass.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * vote_mass
 * @author 
 */
public class VoteMass extends BaseEntity implements Serializable {

    /**
     * 投票ID
     */
    private Long voteId;

    /**
     * 社团ID
     */
    private Long massId;


    private static final long serialVersionUID = 1L;


    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Long getMassId() {
        return massId;
    }

    public void setMassId(Long massId) {
        this.massId = massId;
    }
}