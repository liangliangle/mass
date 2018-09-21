package com.mass.entity;

import com.mass.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Options  extends BaseEntity implements Serializable {


    /**
     * 投票ID
     */
    private Long voteId;

    /**
     * 顺序
     */
    private Integer orders;

    /**
     * 选项值
     */
    private String thetext;

    private Integer status;

    private static final long serialVersionUID = 1L;



    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getThetext() {
        return thetext;
    }

    public void setThetext(String thetext) {
        this.thetext = thetext;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Options other = (Options) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getVoteId() == null ? other.getVoteId() == null : this.getVoteId().equals(other.getVoteId()))
            && (this.getOrders() == null ? other.getOrders() == null : this.getOrders().equals(other.getOrders()))
            && (this.getThetext() == null ? other.getThetext() == null : this.getThetext().equals(other.getThetext()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getVoteId() == null) ? 0 : getVoteId().hashCode());
        result = prime * result + ((getOrders() == null) ? 0 : getOrders().hashCode());
        result = prime * result + ((getThetext() == null) ? 0 : getThetext().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(getId());
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", lastModifyTime=").append(getLastModifyTime());
        sb.append(", voteId=").append(voteId);
        sb.append(", orders=").append(orders);
        sb.append(", thetext=").append(thetext);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}