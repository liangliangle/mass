package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by liliang on 2018/2/3.
 */
public class VoteDto extends BaseDto implements Serializable {

    

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String sno;
    /**
     * 创建人姓名
     */
    @ApiModelProperty("创建人姓名")
    private String stuName;

    /**
     * 投票
     */
    @ApiModelProperty("投票")
    private String name;

    /**
     * 是否匿名
     */
    @ApiModelProperty("是否匿名")
    private Integer isAnon;

    /**
     * 可投数量
     */
    @ApiModelProperty("可投数量")
    private Integer voteNum;

    /**
     * 投票范围
     */
    @ApiModelProperty("投票范围")
    private String range;

    /**
     * 是否包含子组织
     */
    @ApiModelProperty("是否包含子组织")
    private Integer isCoveSon;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("选项列表")
    private List<OptionsDto> optionsDto;


    private Integer redundancy;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsAnon() {
        return isAnon;
    }

    public void setIsAnon(Integer isAnon) {
        this.isAnon = isAnon;
    }

    public Integer getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(Integer voteNum) {
        this.voteNum = voteNum;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Integer getIsCoveSon() {
        return isCoveSon;
    }

    public void setIsCoveSon(Integer isCoveSon) {
        this.isCoveSon = isCoveSon;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OptionsDto> getOptionsDto() {
        return optionsDto;
    }

    public void setOptionsDto(List<OptionsDto> optionsDto) {
        this.optionsDto = optionsDto;
    }


    public Integer getRedundancy() {
        return redundancy;
    }

    public void setRedundancy(Integer redundancy) {
        this.redundancy = redundancy;
    }
}
