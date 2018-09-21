package com.mass.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mass.common.exception.ServiceException;
import com.mass.dao.RegisterCodeMapper;
import com.mass.dao.RegisterMapper;
import com.mass.dao.RegisterMemMapper;
import com.mass.dto.create.CreateRegisterDto;
import com.mass.dto.create.CreateRegisterMemDto;
import com.mass.dto.query.ExportRegister;
import com.mass.dto.query.RegisterQuery;
import com.mass.dto.result.RegisterCodeDto;
import com.mass.dto.result.RegisterDto;
import com.mass.entity.Register;
import com.mass.entity.RegisterCode;
import com.mass.entity.RegisterMem;
import com.mass.service.RegisterService;
import com.mass.utils.DateUtils;
import com.mass.utils.Qrcode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private RegisterMemMapper registerMemMapper;
    @Autowired
    private RegisterCodeMapper codeMapper;
    @Autowired
    private ObjectMapper objectMapper;

    private static Logger logger = Logger.getLogger(RegisterServiceImpl.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void create(CreateRegisterDto dto) {
        Register register = dtoToEntity(dto);
        register.setStatus(1);
        if (register.getEndTime().getTime() < System.currentTimeMillis()) {
            throw new ServiceException("结束时间不得小于当前时间");
        }
        register.setType(dto.getType());
        register.setMark(0);
        registerMapper.insert(register);
    }

    @Override
    public void update(CreateRegisterDto dto) {
        Register register = registerMapper.selectByPrimaryKey(dto.getId());
        if (register.getStartTime().getTime() < System.currentTimeMillis()) {
            throw new ServiceException("签到已经开始，不可修改");
        }
        register = dtoToEntity(dto);
        register.setStatus(1);
        register.setType(dto.getType());
        register.setMark(0);
        if (register.getEndTime().getTime() < System.currentTimeMillis()) {
            throw new ServiceException("结束时间不得小于当前时间");
        }
        register.setLastModifyTime(new Date());
        registerMapper.updateByPrimaryKey(register);
    }

    @Override
    public RegisterCodeDto getRegisterCode(Long id) {
        Register register = registerMapper.selectByPrimaryKey(id);
        if (register.getStatus() != 2) {
            throw new ServiceException("签到已结束，或签到未到时间");
        }
        String code = UUID.randomUUID().toString().replace("-", "");
        RegisterCode registerCode = new RegisterCode();
        registerCode.setCode(code);
        registerCode.setEndTime(DateUtils.getByDateAndSecond(10));
        registerCode.setRegisterId(id);
        registerCode.setStatus(1);
        String imgString = Qrcode.getCodeString("app:mass//regist:" + id + "//code:" + code);
        RegisterCodeDto registerCodeDto = new RegisterCodeDto();
        registerCodeDto.setExpireTime(10L);
        registerCodeDto.setImg(imgString);
        registerCodeDto.setRegisterId(id);
        codeMapper.insert(registerCode);
        return registerCodeDto;
    }

    @Override
    public void register(CreateRegisterMemDto dto) {
        try {
            logger.info("签到信息：【" + objectMapper.writeValueAsString(dto) + "】");
        } catch (Exception e) {
        }
        if (null != dto.getCode() && null == dto.getComments()) {
            Date date = DateUtils.getByDateAndSecond(-5);
            logger.info(date);
            List<RegisterCode> codes = codeMapper.selectByCodeAndTime(dto.getCode(), date,
                    dto.getRegisterId());
            if (codes.size() <= 0) {
                throw new ServiceException("二维码已过期");
            }
        }
        if (null == dto.getCode() && null == dto.getComments()) {
            throw new ServiceException("签到失败");
        }
        RegisterMem mem = dtoToEntity(dto);
        mem.setType("正常");
        if (null == mem.getCode()) {
            mem.setCode("0");
            mem.setType("补签");
        }
        int count = registerMemMapper.insert(mem);
        if (count < 1) {
            throw new ServiceException("您已签到");
        }
    }

    @Override
    public PageInfo<RegisterDto> getBySnoAndStatus(String sno, Integer status, Integer page) {
        PageHelper.startPage(page, 10);
        List<RegisterDto> list = registerMapper.selectBySnoAndStatus(sno, status);
        PageInfo<RegisterDto> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo<RegisterDto> query(RegisterQuery query, Integer page) {
        PageHelper.startPage(page, 10);
        List<RegisterDto> list = registerMapper.selectByQuery(query);
        PageInfo<RegisterDto> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<List<String>> export(ExportRegister register) {
        List<List<String>> result = new ArrayList<>();
        result.add(getTitle(register));
        List<RegisterMem> fastList = getMemById(register.getId());
        for (RegisterMem mem : fastList) {
            List<String> row = new ArrayList<>();
            row.add(mem.getSno());
            row.add(mem.getName());
            row.add(dateFormat.format(mem.getCreateTime()));
            row.add(mem.getType());
            result.add(row);
        }
        if (null != register.getChindle()) {
            List<RegisterMem> secList = getMemById(register.getChindle().getId());
            for (List<String> row : result) {
                for (RegisterMem mem : secList) {
                    if (row.get(0).equals(mem.getSno())) {
                        row.add(dateFormat.format(mem.getCreateTime()));
                        row.add(mem.getType());
                    }
                }
            }
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).size() <= 4) {
                    result.remove(i);
                    i--;
                }
            }
        }
        return result;
    }

    @Override
    public PageInfo<RegisterMem> result(Long id, Integer page) {
        PageHelper.startPage(page, 10);
        List<RegisterMem> mems = registerMemMapper.selectByRegister(id);
        return new PageInfo<RegisterMem>(mems);

    }

    @Override
    public List<RegisterDto> getByActivity(Long activityId) {
        return registerMapper.selectByActivity(activityId);
    }

    @Override
    public RegisterDto getById(Long id) {
        Register register = registerMapper.selectByPrimaryKey(id);
        return entityToDto(register);
    }

    private List<RegisterMem> getMemById(Long id) {
        return this.registerMemMapper.selectByRegister(id);
    }

    private List<String> getTitle(ExportRegister register) {
        List<String> title = new ArrayList<>();
        title.add("学号");
        title.add("姓名");
        title.add(register.getName() + "-时间");
        title.add(register.getName() + "-类型");
        if (null != register.getChindle()) {
            title.add(register.getChindle().getName() + "-时间");
            title.add(register.getChindle().getName() + "-类型");

        }
        return title;

    }

    private Register dtoToEntity(CreateRegisterDto dto)  {
        Register register = new Register();

        register.setActivitiesId(dto.getActivitiesId());
        register.setComments(dto.getComments());
        register.setEndTime(dto.getEndTime());
        register.setMark(dto.getMark());
        register.setStartTime(dto.getStartTime());
        register.setStatus(dto.getMark());
        register.setType(dto.getType());
        register.setStatus(1);
        register.setName(dto.getName());
        register.setSno(dto.getSno());
        if (null != dto.getId()) {
            register.setId(dto.getId());
        }
        return register;
    }

    private RegisterMem dtoToEntity(CreateRegisterMemDto dto) {
        RegisterMem mem = new RegisterMem();
        mem.setClasses(dto.getClasses());
        mem.setCode(dto.getCode());
        mem.setComments(dto.getComments());
        mem.setName(dto.getName());
        mem.setRegisterId(dto.getRegisterId());
        mem.setSno(dto.getSno());
        mem.setStatus(1);
        mem.setType(dto.getType());
        return mem;
    }

    private RegisterDto entityToDto(Register register) {
        RegisterDto dto = new RegisterDto();
        dto.setActivitiesId(register.getActivitiesId());
        dto.setComments(register.getComments());
        dto.setEndTime(register.getEndTime());
        dto.setMark(register.getMark());
        dto.setName(register.getName());
        dto.setSno(register.getSno());
        dto.setStartTime(register.getStartTime());
        dto.setStatus(register.getStatus());
        dto.setType(register.getType());
        dto.setId(register.getId());
        return dto;
    }


}
