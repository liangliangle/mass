package com.mass.dto.create;

import com.mass.common.dto.BaseDto;
import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

public class CreateProgramMemDto extends BaseDto {

    /**
     * 节目名称
     */
    @ApiModelProperty("节目名称")
    private String programName;

    /**
     * 学号
     */
    @NotNull(message = "学号")
    @ApiModelProperty("学号")
    private String sno;
    @NotNull(message = "学生姓名")
    @ApiModelProperty("学生姓名")
    private String name;

    /**
     * 参与类型
     */
    @NotNull(message = "参与类型")
    @ApiModelProperty("参与类型")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
