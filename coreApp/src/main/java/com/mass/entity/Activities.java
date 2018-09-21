package com.mass.entity;

import com.mass.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class Activities extends BaseEntity implements Serializable {

    /**
     * 名称
     */
    private String name;

    /**
     * 活动地点
     */
    private String place;

    /**
     * 活动学期
     */
    private String semesterName;

    /**
     * 主办社团
     */
    private String sponsorMass;

    /**
     * 举办时间
     */
    private Date holdTime;

    /**
     * 活动类型
     */
    private String type;

    /**
     * 审核次数
     */
    private Integer review;

    /**
     * 活动摘要
     */
    private String description;

    /**
     * 参加人数
     */
    private Integer joinPeople;

    /**
     * 观众人数
     */
    private Integer audience;

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activities that = (Activities) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (semesterName != null ? !semesterName.equals(that.semesterName) : that.semesterName != null) return false;
        if (sponsorMass != null ? !sponsorMass.equals(that.sponsorMass) : that.sponsorMass != null) return false;
        if (holdTime != null ? !holdTime.equals(that.holdTime) : that.holdTime != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (review != null ? !review.equals(that.review) : that.review != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (joinPeople != null ? !joinPeople.equals(that.joinPeople) : that.joinPeople != null) return false;
        if (audience != null ? !audience.equals(that.audience) : that.audience != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (semesterName != null ? semesterName.hashCode() : 0);
        result = 31 * result + (sponsorMass != null ? sponsorMass.hashCode() : 0);
        result = 31 * result + (holdTime != null ? holdTime.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (joinPeople != null ? joinPeople.hashCode() : 0);
        result = 31 * result + (audience != null ? audience.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Activities{" +
                "name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", semesterName='" + semesterName + '\'' +
                ", sponsorMass='" + sponsorMass + '\'' +
                ", holdTime=" + holdTime +
                ", type='" + type + '\'' +
                ", review=" + review +
                ", description='" + description + '\'' +
                ", joinPeople=" + joinPeople +
                ", audience=" + audience +
                ", status=" + status +
                '}';
    }

    public Activities() {

    }


}