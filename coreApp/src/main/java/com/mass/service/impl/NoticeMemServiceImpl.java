package com.mass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mass.dao.NoticeUsersMapper;
import com.mass.dto.result.CountNoticeDto;
import com.mass.entity.Notice;
import com.mass.entity.NoticeUsers;
import com.mass.feign.dto.SysUser;
import com.mass.service.NoticeMemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeMemServiceImpl implements NoticeMemService {
    @Autowired
    private NoticeUsersMapper noticeUsersMapper;

    @Override
    public void create(List<SysUser> users, Notice notice) {
        for (SysUser user : users) {
            NoticeUsers noticeUser = new NoticeUsers();
            noticeUser.setEndTime(notice.getEndTime());
            noticeUser.setReadStatus(0);
            noticeUser.setNoticeId(notice.getId());
            noticeUser.setSno(user.getUsername());
            noticeUser.setUsername(user.getName());
            noticeUser.setStatus(1);
            noticeUser.buildDefaultTimeStamp();
            noticeUser.setStartTime(notice.getStartTime());
            noticeUsersMapper.insert(noticeUser);
        }

    }

    @Override
    public PageInfo<NoticeUsers> getNoticeResult(Long voteId, String sno, Integer status, Integer page) {
        PageHelper.startPage(page, 10);
        List<NoticeUsers> list = noticeUsersMapper.selectByNotice(voteId, sno, status);
        return new PageInfo<>(list);
    }


    @Override
    public CountNoticeDto countNoticeResult(Long noticeId) {
        CountNoticeDto dto = new CountNoticeDto();
        Long total = noticeUsersMapper.countByNoticeId(noticeId, null);
        Long readCount = noticeUsersMapper.countByNoticeId(noticeId, 1);
        dto.setReadCount(readCount);
        dto.setTotal(total);
        return dto;
    }
}
