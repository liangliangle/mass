package com.mass.dto.result;

import com.mass.common.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by liliang on 2018/1/31.
 */
public class DictionaryDto extends BaseDto implements Serializable {

    /**
     * 字典Key值
     */
    @ApiModelProperty("key")
    private String key;

    @ApiModelProperty("状态")
    private Integer status;

    /**
     * value值
     */
    @ApiModelProperty("value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryDto that = (DictionaryDto) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
