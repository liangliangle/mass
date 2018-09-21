package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liliang on 2018/2/1.
 */
public class NoticeUsersDto extends BaseDto implements Serializable {

    /**
     * 学号
     */
    @ApiModelProperty("学号")
    private String sno;
    @ApiModelProperty("开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endTime;

    /**
     * 阅读时间
     */
    @ApiModelProperty("阅读时间")
    private Date readTime;

    /**
     * 是否阅读
     */
    @ApiModelProperty("是否阅读")
    private Integer readStatus;
    @ApiModelProperty("状态")
    private Integer status;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
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

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
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

        NoticeUsersDto that = (NoticeUsersDto) o;

        if (sno != null ? !sno.equals(that.sno) : that.sno != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (readTime != null ? !readTime.equals(that.readTime) : that.readTime != null) return false;
        if (readStatus != null ? !readStatus.equals(that.readStatus) : that.readStatus != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = sno != null ? sno.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (readTime != null ? readTime.hashCode() : 0);
        result = 31 * result + (readStatus != null ? readStatus.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NoticeUsersDto{" +
                "sno='" + sno + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", readTime=" + readTime +
                ", readStatus=" + readStatus +
                ", status=" + status +
                '}';
    }
}
