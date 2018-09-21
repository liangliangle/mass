package com.mass.dao;

import com.mass.dto.query.ExportRegister;
import com.mass.dto.query.RegisterQuery;
import com.mass.dto.result.RegisterDto;
import com.mass.entity.Register;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RegisterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Register record);


    Register selectByPrimaryKey(Long id);


    int updateByPrimaryKey(Register record);

    List<Register> selectByName(String name);

    List<Register> selectByStatus(Integer status);

    List<RegisterDto> selectBySnoAndStatus(@Param("sno") String sno,
                                           @Param("status") Integer status);

    List<RegisterDto> selectByQuery(RegisterQuery query);

    // List<RegisterDto> export(ExportRegister register);


    List<RegisterDto> selectByActivity(Long activityId);


    int checkStart(Date date);

    int checkClose(Date date);
}