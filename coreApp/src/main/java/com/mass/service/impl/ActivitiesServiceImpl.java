package com.mass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mass.common.exception.ServiceException;
import com.mass.common.exception.ValidationException;
import com.mass.dao.ActivitiesDocMapper;
import com.mass.dao.ActivitiesMapper;
import com.mass.dao.SemesterMapper;
import com.mass.dto.query.ActitQuery;
import com.mass.dto.update.AuditActivitiesDto;
import com.mass.dto.create.CreateActivitiesDto;
import com.mass.dto.result.ActivitiesDto;
import com.mass.dto.result.ActivityAndDocDto;
import com.mass.entity.Activities;
import com.mass.entity.ActivitiesDoc;
import com.mass.enums.AcitvityType;
import com.mass.service.ActivitiesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by liliang on 2018/2/3.
 */
@Service
@Transactional
public class ActivitiesServiceImpl implements ActivitiesService {
    @Autowired
    private ActivitiesMapper activitiesMapper;
    @Autowired
    private ActivitiesDocMapper activitiesDocMapper;
    @Autowired
    private SemesterMapper semesterMapper;

    private static Logger LOGGER = Logger.getLogger(ActivitiesServiceImpl.class);

    @Override
    public void create(CreateActivitiesDto dto) {
        dto.verification();
        Activities activities = createActivities(dto);
        if (activities.getHoldTime().getTime() < System.currentTimeMillis()) {
            throw new ServiceException("举办时间不能小于当前时间");
        }
        activities.setAudience(0);
        activities.setReview(0);
        LOGGER.info("创建活动[" + dto.getName() + "]");
        Long activitiesId = activitiesMapper.insert(activities);
        ActivitiesDoc activitiesDoc = new ActivitiesDoc();
        activitiesDoc.setActivitiesId(activities.getId());
        activitiesDoc.setType(dto.getDoctype());
        activitiesDoc.setValue(dto.getValue());
        activitiesDoc.setStatus(1);
        activitiesDocMapper.insert(activitiesDoc);
    }

    @Override
    public PageInfo<ActivitiesDto> getAll(int start, ActitQuery query) {
        PageHelper.startPage(start, 10);
        List<ActivitiesDto> list = activitiesMapper.selectByQuery(query);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public ActivityAndDocDto getById(Long id) {
        Activities activities = activitiesMapper.selectByPrimaryKey(id);
        ActivitiesDoc activitiesDoc = activitiesDocMapper.selectByActivitiesId(id);
        if (null == activities) {
            throw new ServiceException("活动不存在");
        }
        if (null == activitiesDoc &&
                !activities.getType().equals(AcitvityType.PRIVATE.getType())) {
            throw new ServiceException("活动申请不存在");
        }
        return toActivityAndDocDto(activities, activitiesDoc);
    }

    @Override
    public void audit(Long id, AuditActivitiesDto dto) {
        Activities activities = activitiesMapper.selectByPrimaryKey(id);
        if (activities.getStatus() != 1) {
            throw new ServiceException("该活动无需审核");
        }
        if (dto.getStatus() == 0) {
            activities.setReview(activities.getReview() + 1);
            if (activities.getReview() == 2) {
                activities.setStatus(0);
            } else {
                activities.setStatus(2);
            }
        } else if (dto.getStatus() == 1) {
            activities.setStatus(3);
        } else if (dto.getStatus() == -1) {
            activities.setStatus(0);
        } else {
            throw new ServiceException("未知审核类型");
        }
        activities.setLastModifyTime(new Date());
        activitiesMapper.updateByPrimaryKey(activities);
        ActivitiesDoc activitiesDoc = activitiesDocMapper.selectByActivitiesId(id);
        activitiesDoc.setProposal(dto.getProposal());
        activitiesDoc.setLastModifyTime(new Date());
        activitiesDocMapper.updateByPrimaryKey(activitiesDoc);
    }

    @Override
    public void update(CreateActivitiesDto dto) {
        Activities activities = activitiesMapper.selectByPrimaryKey(dto.getId());
        if (activities.getStatus() != 2 && activities.getStatus() != 1) {
            throw new ServiceException("该活动不能修改");
        }
        if (dto.getId() == null) {
            throw new ServiceException("未找到这个活动");
        }
        activities = createActivities(dto);
        if (activities.getHoldTime().getTime() < System.currentTimeMillis()) {
            throw new ServiceException("举办时间不能小于当前时间");
        }
        activities.setId(dto.getId());
        activitiesMapper.updateByPrimaryKey(activities);
    }


    private Activities createActivities(CreateActivitiesDto dto) {
        Activities activities = new Activities();
        activities.buildDefaultTimeStamp();
        activities.setType(dto.getType());
        activities.setDescription(dto.getDescription());
        activities.setHoldTime(dto.getHoldTime());
        activities.setName(dto.getName());
        activities.setPlace(dto.getPlace());
        activities.setSponsorMass(dto.getSponsorMass());
        activities.setStatus(1);
        activities.setAudience(0);
        if (!AcitvityType.verification(dto.getType())) {
            throw new ValidationException("未知活动类型");
        }
        if (activities.getType().equals(AcitvityType.PRIVATE.getType())) {
            activities.setStatus(3);
        }
        activities.setSemesterName(semesterMapper.selectByCurrent().getName());
        return activities;
    }

    @Override
    public List<ActivitiesDto> lately() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK, 7);
        date = calendar.getTime();
        return activitiesMapper.lately(date);
    }

    private ActivityAndDocDto toActivityAndDocDto(Activities activities, ActivitiesDoc activitiesDoc) {
        if (activities == null) {
            return null;
        }
        ActivityAndDocDto dto = new ActivityAndDocDto();
        dto.setAudience(activities.getAudience());
        dto.setDescription(activities.getDescription());
        dto.setHoldTime(activities.getHoldTime());
        dto.setJoinPeople(activities.getJoinPeople());
        dto.setName(activities.getName());
        dto.setPlace(activities.getPlace());
        dto.setReview(activities.getReview());
        dto.setSemesterName(activities.getSemesterName());
        dto.setSponsorMass(activities.getSponsorMass());
        dto.setStatus(activities.getStatus());
        dto.setType(activities.getType());
        dto.setId(activities.getId());
        if (activitiesDoc != null) {
            dto.setValue(activitiesDoc.getValue());
            dto.setProposal(activitiesDoc.getProposal());
        }
        return dto;
    }

}
