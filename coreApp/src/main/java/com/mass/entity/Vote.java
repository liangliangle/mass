package com.mass.entity;

import com.mass.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Vote   extends BaseEntity implements Serializable {

    /**
     * 创建人
     */
    private String sno;
    /**
     * 创建人姓名
     */
    private String stuName;

    /**
     * 投票
     */
    private String name;

    /**
     * 是否匿名
     */
    private Integer isAnon;

    /**
     * 可投数量
     */
    private Integer voteNum;

    /**
     * 投票范围
     */
    private String range;

    /**
     * 是否包含子组织
     */
    private Integer isCoveSon;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private static final long serialVersionUID = 1L;


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

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
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
        Vote other = (Vote) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIsAnon() == null ? other.getIsAnon() == null : this.getIsAnon().equals(other.getIsAnon()))
            && (this.getVoteNum() == null ? other.getVoteNum() == null : this.getVoteNum().equals(other.getVoteNum()))
            && (this.getRange() == null ? other.getRange() == null : this.getRange().equals(other.getRange()))
            && (this.getIsCoveSon() == null ? other.getIsCoveSon() == null : this.getIsCoveSon().equals(other.getIsCoveSon()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIsAnon() == null) ? 0 : getIsAnon().hashCode());
        result = prime * result + ((getVoteNum() == null) ? 0 : getVoteNum().hashCode());
        result = prime * result + ((getRange() == null) ? 0 : getRange().hashCode());
        result = prime * result + ((getIsCoveSon() == null) ? 0 : getIsCoveSon().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", isAnon=").append(isAnon);
        sb.append(", voteNum=").append(voteNum);
        sb.append(", range=").append(range);
        sb.append(", isCoveSon=").append(isCoveSon);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}