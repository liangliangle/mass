package com.mass.dto.login;

import com.mass.common.dto.BaseDto;
import com.mass.util.NotNull;
import io.swagger.annotations.ApiModelProperty;

public class ApplyUserOrgDto extends BaseDto {
    @ApiModelProperty("账号名")
    @NotNull(message = "账号名")
    private String username;

    @ApiModelProperty("组织id")
    @NotNull(message = "组织id")
      private  Long orgId;
    @ApiModelProperty("社团名称")
    @NotNull(message = "username")
      private  String orgName;
    @ApiModelProperty("班级")
    @NotNull(message = "班级")
      private  String grade;
    @ApiModelProperty("申请理由")
    @NotNull(message = "申请理由")
      private  String  applyReason;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }
}
