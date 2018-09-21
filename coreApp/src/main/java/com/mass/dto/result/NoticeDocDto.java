package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by liliang on 2018/2/1.
 */
public class NoticeDocDto extends BaseDto  implements Serializable {
    /**
     * 通知ID
     */
    @ApiModelProperty("通知ID")
    private Long noticeId;

    private Integer status;

    /**
     * 正文
     */
    @ApiModelProperty("正文")
    private String thetext;



    public Long getNoticeId() {

        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getThetext() {
        return thetext;
    }

    public void setThetext(String thetext) {
        this.thetext = thetext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoticeDocDto that = (NoticeDocDto) o;

        if (noticeId != null ? !noticeId.equals(that.noticeId) : that.noticeId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return thetext != null ? thetext.equals(that.thetext) : that.thetext == null;
    }

    @Override
    public int hashCode() {
        int result = noticeId != null ? noticeId.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (thetext != null ? thetext.hashCode() : 0);
        return result;
    }
}
