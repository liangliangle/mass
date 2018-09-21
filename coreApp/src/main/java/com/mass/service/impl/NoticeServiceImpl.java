package com.mass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mass.common.exception.ServiceException;
import com.mass.dao.NoticeDocMapper;
import com.mass.dao.NoticeMapper;
import com.mass.dao.NoticeMassMapper;
import com.mass.dao.NoticeUsersMapper;
import com.mass.dto.create.CreateNoticeDto;
import com.mass.dto.query.NoticeQuery;
import com.mass.dto.result.CountNoticeDto;
import com.mass.dto.result.NoticeDto;
import com.mass.entity.Notice;
import com.mass.entity.NoticeDoc;
import com.mass.entity.NoticeMass;
import com.mass.entity.NoticeUsers;
import com.mass.feign.FeignService;
import com.mass.service.NoticeMemService;
import com.mass.service.NoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 通知  service实现
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private NoticeDocMapper noticeDocMapper;
    @Autowired
    private NoticeMassMapper noticeMassMapper;
    @Autowired
    private NoticeUsersMapper usersMapper;
    @Autowired
    private NoticeMemService memService;

    @Override
    public void create(CreateNoticeDto dto) {
        Notice notice = dtoToNotice(dto);
        if(notice.getEndTime().getTime()<System.currentTimeMillis()){
            throw new ServiceException("结束时间不得小于当前时间");
        }
        notice.setStatus(1);
        noticeMapper.insert(notice);
        if (StringUtils.isNoneEmpty(dto.getThetext())) {
            NoticeDoc doc = new NoticeDoc();
            doc.setNoticeId(notice.getId());
            doc.setThetext(dto.getThetext());
            doc.setStatus(1);
            doc.buildDefaultTimeStamp();
            noticeDocMapper.insert(doc);
        }
        List<NoticeMass> voteMasses = dtoToNoticeMassEntity(dto, notice.getId());
        for (NoticeMass mass : voteMasses) {
            noticeMassMapper.insert(mass);
        }
    }

    @Override
    public void update(CreateNoticeDto dto) {
        Notice notice = this.noticeMapper.selectByPrimaryKey(dto.getId());
        if (null == notice) {
            throw new ServiceException("未找到该通知");
        }
        if (notice.getStatus() != 1) {
            throw new ServiceException("该通知已经开始，不能修改");
        }
        notice = dtoToNotice(dto);
        notice.setLastModifyTime(new Date());
        if(notice.getEndTime().getTime()<System.currentTimeMillis()){
            throw new ServiceException("结束时间不得小于当前时间");
        }
        noticeMapper.updateByPrimaryKey(notice);
        NoticeDoc doc = new NoticeDoc();
        doc.setNoticeId(notice.getId());
        doc.setThetext(dto.getThetext());
        doc.setStatus(1);
        doc.setLastModifyTime(new Date());
        noticeDocMapper.updateByNoticeId(doc);
        noticeMassMapper.deleteByNoticeId(dto.getId());
        List<NoticeMass> voteMasses = dtoToNoticeMassEntity(dto, notice.getId());
        for (NoticeMass mass : voteMasses) {
            noticeMassMapper.insert(mass);
        }
    }

    @Override
    public PageInfo<NoticeDto> getAll(int start, NoticeQuery query) {
        PageHelper.startPage(start, 10);
        List<NoticeDto> list = noticeMapper.selectByQuery(query);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public NoticeDto getById(Long id, String sno) {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        NoticeDto dto = entityToDto(notice);
        NoticeDoc doc = noticeDocMapper.selectByNoticeId(id);
        if (doc != null) {
            dto.setThetext(doc.getThetext());
        }
        if (sno != null && notice.getStartTime().getTime() < System.currentTimeMillis() && notice.getEndTime().getTime() > System.currentTimeMillis()) {
            usersMapper.readNotice(sno, id);
        }
        return dto;

    }

    @Override
    public PageInfo<NoticeDto> getBySno(String sno, Integer status, Integer page) {
        PageHelper.startPage(page, 10);
        List<NoticeDto> list = noticeMapper.selectBySno(sno, status);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo<NoticeDto> getMyNotice(String sno, Integer status, Integer page) {
        PageHelper.startPage(page, 10);
        List<NoticeDto> list = noticeMapper.selectMyNotice(sno, status);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo<NoticeUsers> getNoticeResult(Long voteId, String sno, Integer status, Integer page) {
        return memService.getNoticeResult(voteId, sno, status, page);
    }

    @Override
    public CountNoticeDto getNoticeResult(Long noticeId) {
        return memService.countNoticeResult(noticeId);
    }

    /**
     * dto转实体
     *
     * @param dto dto
     * @return 实体
     */
    private Notice dtoToNotice(CreateNoticeDto dto) {
        Notice notice = new Notice();
        if (null != dto.getId()) {
            notice.setId(dto.getId());
        }
        notice.setActivitiesId(dto.getActivitiesId());
        notice.setDescription(dto.getDescription());
        notice.setEndTime(dto.getEndTime());
        notice.setIsCoveSon(dto.getIsCoveSon());
        notice.setName(dto.getName());
        String range = String.join(",", dto.getRange());
        notice.setRange(range);
        notice.setStartTime(dto.getStartTime());
        notice.setStatus(1);
        notice.setSno(dto.getSno());
        notice.setUserName(dto.getUserName());
        notice.buildDefaultTimeStamp();
        if (null != dto.getId()) {
            notice.setId(dto.getId());
        }
        return notice;
    }

    /**
     * 实体转DTO
     *
     * @param notice 实体
     * @return dto
     */
    private NoticeDto entityToDto(Notice notice) {
        NoticeDto dto = new NoticeDto();
        dto.setActivitiesId(notice.getActivitiesId());
        dto.setDescription(notice.getDescription());
        dto.setEndTime(notice.getEndTime());
        dto.setIsCoveSon(notice.getIsCoveSon());
        dto.setName(notice.getName());
        dto.setSno(notice.getSno());
        dto.setUserName(notice.getUserName());
        dto.setRange(notice.getRange());
        dto.setStartTime(notice.getStartTime());
        dto.setStatus(notice.getStatus());
        dto.setId(notice.getId());
        return dto;

    }

    private List<NoticeMass> dtoToNoticeMassEntity(CreateNoticeDto dto, Long noticeId) {
        List<String> massNames = dto.getRange();
        List<NoticeMass> result = new ArrayList<NoticeMass>(massNames.size());
        for (String massName : massNames) {
            NoticeMass mass = new NoticeMass();
            mass.setNoticeId(noticeId);
            mass.setMassId(1L);
            //TODO 远程调用查询ID mass.setMassId();
            mass.buildDefaultTimeStamp();
            result.add(mass);
        }
        return result;
    }


}
