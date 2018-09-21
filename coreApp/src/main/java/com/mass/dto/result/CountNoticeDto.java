package com.mass.dto.result;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 下午12:48 2018/5/20 2018
 * @Modify:
 */
public class CountNoticeDto {
    @ApiModelProperty("已读人数")
    private Long readCount;
    @ApiModelProperty("总人数")
    private Long total;

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
