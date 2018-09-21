package com.mass.dto.login;

import com.mass.common.dto.BaseDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class SetUserRoleDto extends BaseDto {
     private  String username;
    private  List<Long> roleId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Long> getRoleId() {
        return roleId;
    }

    public void setRoleId(List<Long> roleId) {
        this.roleId = roleId;
    }
}
