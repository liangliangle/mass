package com.mass.service;

import com.github.pagehelper.PageInfo;
import com.mass.dto.result.CountNoticeDto;
import com.mass.entity.Notice;
import com.mass.entity.NoticeUsers;
import com.mass.feign.dto.SysUser;

import java.util.List;

public interface NoticeMemService {
    void create(List<SysUser> snos, Notice notice);

    PageInfo<NoticeUsers> getNoticeResult(Long voteId, String sno, Integer status, Integer page);


    CountNoticeDto countNoticeResult(Long noticeId);
}
