package com.mass.dto.login;

import com.mass.dto.SysUserOrg;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 下午8:42 2018/5/6 2018
 * @Modify:
 */
public class UserOrgQueryDto extends SysUserOrg {


     private  String   uname;
     private  String  usex;
     private  String  email;
     private   String  phone;
    private  String   name;
    private  String  sex;

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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
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
