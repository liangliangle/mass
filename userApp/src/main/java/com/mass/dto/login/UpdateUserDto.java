package com.mass.dto.login;

import com.mass.common.dto.BaseDto;
import com.mass.util.NotNull;
import io.swagger.annotations.ApiModelProperty;

public class UpdateUserDto extends BaseDto {

    @ApiModelProperty("账号")
    @NotNull(message = "账号")
     private String username;
    @ApiModelProperty("姓名")
    @NotNull(message = "姓名")
    private  String  name;
    @ApiModelProperty("性别")
    @NotNull(message = "性别")
    private String sex;
    @ApiModelProperty("班级")
    @NotNull(message = "班级")
    private String grade;
    @ApiModelProperty("邮箱")
    @NotNull(message = "邮箱")
    private String email;
    @ApiModelProperty("手机号")
    @NotNull(message = "手机号")
    private String phone;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
