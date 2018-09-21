package com.mass.service;

import com.github.pagehelper.PageInfo;
import com.mass.dto.ApplyOrgTemp;
import com.mass.dto.login.ApplyDTO;
import com.mass.entity.DataResult;

import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 下午4:25 2018/4/2 2018
 * @Modify:
 */
public interface ApplyOrgService {
    public  Integer  insertApplyTemp(ApplyDTO applyOrgTemp);

    boolean selectusernameordID(ApplyDTO applyOrgTemp);

    PageInfo<ApplyOrgTemp> seeApplyList(Integer offset, Integer status);

    void checkApplyOrgTempList(Long id,Integer status);

    List<ApplyOrgTemp> seeApplymyList(String username,Integer status);

   ApplyOrgTemp  seeApplymyDetailById(Long id);
}
