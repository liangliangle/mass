package com.mass.dao;

import com.mass.entity.Dictionary;

public interface DictionaryMapper {


    Dictionary selectByPrimaryKey(String key);

    int updateByPrimaryKey(Dictionary record);

}