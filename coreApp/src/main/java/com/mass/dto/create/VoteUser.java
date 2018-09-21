package com.mass.dto.create;

import com.mass.utils.NotNull;
import io.swagger.annotations.ApiModelProperty;

import javax.swing.text.html.Option;
import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 上午12:01 2018/5/20 2018
 * @Modify:
 */
public class VoteUser {

    @NotNull(message = "投票ID")
    @ApiModelProperty("投票ID")
    private Long voteId;

    @NotNull(message = "用户名")
    @ApiModelProperty("用户名")
    private String username;

    @NotNull(message = "学号")
    @ApiModelProperty("学号")
    private String sno;

    @NotNull(message = "选项")
    @ApiModelProperty("选项")
    private List<OptionUser> options;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OptionUser> getOptions() {
        return options;
    }

    public void setOptions(List<OptionUser> options) {
        this.options = options;
    }
}
