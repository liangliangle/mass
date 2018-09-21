package com.mass.dto.create;

import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 上午12:03 2018/5/20 2018
 * @Modify:
 */
public class OptionUser {

    @NotNull(message = "选项ID")
    @ApiModelProperty("选项ID")
    private Long optionId;

    @NotNull(message = "选项值")
    @ApiModelProperty("选项值")
    private String option;

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
