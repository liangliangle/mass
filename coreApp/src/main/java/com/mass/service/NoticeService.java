package com.mass.service;

import com.github.pagehelper.PageInfo;
import com.mass.dto.create.CreateNoticeDto;
import com.mass.dto.query.NoticeQuery;
import com.mass.dto.result.CountNoticeDto;
import com.mass.dto.result.NoticeDto;
import com.mass.entity.NoticeUsers;

public interface NoticeService {

    void create(CreateNoticeDto dto);

    void update(CreateNoticeDto dto);

    PageInfo<NoticeDto> getAll(int start, NoticeQuery query);

    NoticeDto getById(Long id, String sno);

    PageInfo<NoticeDto> getBySno(String sno, Integer status, Integer page);

    PageInfo<NoticeDto> getMyNotice(String sno, Integer status, Integer page);

    PageInfo<NoticeUsers> getNoticeResult(Long voteId, String sno, Integer status, Integer page);


    CountNoticeDto getNoticeResult(Long noticeId);

}
