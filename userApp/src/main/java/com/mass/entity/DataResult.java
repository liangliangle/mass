package com.mass.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataResult   implements Serializable {

    private static final long serialVersionUID = -356912317056399140L;

    public static final int SUCCESS_STATUS = 200;

    protected Object data;
    protected  String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected Map<String, Object> dataMap = new HashMap<>();

    protected int status = SUCCESS_STATUS; // 响应状态码 默认200代表成功

    protected String message; // 状态描述信息



}
