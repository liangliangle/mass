package com.mass.dto.query;

import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 下午5:39 2018/5/22 2018
 * @Modify:
 */
public class RegisterQuery {
    @ApiModelProperty("名称")
    private String name;
    /**
     * 申请时间
     */
    @ApiModelProperty("申请时间")
    private Date startTimes;
    @ApiModelProperty("申请时间")
    private Date endTimes;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("状态")
    private Integer status;

    @NotNull(message = "页码")
    private Integer page;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Date getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(Date startTimes) {
        this.startTimes = startTimes;
    }

    public Date getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(Date endTimes) {
        this.endTimes = endTimes;
    }
}
