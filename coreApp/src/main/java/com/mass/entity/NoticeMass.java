package com.mass.entity;

import com.mass.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * notice_mass
 * @author 
 */
public class NoticeMass extends BaseEntity implements Serializable {

    /**
     * 通知ID
     */
    private Long noticeId;

    /**
     * 社团ID
     */
    private Long massId;

    private static final long serialVersionUID = 1L;


    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Long getMassId() {
        return massId;
    }

    public void setMassId(Long massId) {
        this.massId = massId;
    }
}