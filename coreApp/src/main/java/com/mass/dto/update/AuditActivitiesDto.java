package com.mass.dto.update;

import com.mass.common.dto.BaseDto;
import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liliang on 2018/2/4.
 * 审核
 */
public class AuditActivitiesDto extends BaseDto {

    @ApiModelProperty("评语")
    private String proposal;

    @NotNull(message = "状态")
    private Integer status;

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
