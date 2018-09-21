package com.mass.service.impl;

import com.mass.dao.DemoDAO;
import com.mass.dto.DemoDto;
import com.mass.entity.DemoEntity;
import com.mass.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoDAO demoDAO;

    public List<DemoDto> getAll() {
        List<DemoEntity> entityList = this.demoDAO.getAll();
        return convert(entityList, new ArrayList<DemoDto>(entityList.size()));

    }

    private List<DemoDto> convert(List<DemoEntity> entities, List<DemoDto> dtos) {
        for (DemoEntity entity : entities) {
            DemoDto dto = new DemoDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtos.add(dto);
        }
        return dtos;
    }
}
