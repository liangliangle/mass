package com.mass.entry.query;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserQuery {

    /**
     * 名称
     */
    @ApiModelProperty("人名")
    private String username;

    /**
     * 名称
     */
    @ApiModelProperty("人名")
    private String name;
    /**
     * 主办社团
     */
    @ApiModelProperty("班级")
    private String classes;


    private Integer page;

    private Integer status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
