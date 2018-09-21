package com.mass.dto.result;

import com.mass.common.dto.BaseDto;

import java.util.List;

public class ActivitiesListDto  extends BaseDto{

   private  List<ActivitiesDto> list;
   private long count;

    public List<ActivitiesDto> getList() {
        return list;
    }

    public void setList(List<ActivitiesDto> list) {
        this.list = list;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
