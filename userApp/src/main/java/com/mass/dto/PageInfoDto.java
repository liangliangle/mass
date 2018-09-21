package com.mass.dto;

import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 下午10:56 2018/5/18 2018
 * @Modify:
 */
public class PageInfoDto<T> {

    private Long count;
    private List<T> list;


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
