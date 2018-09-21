package com.mass.dao;

import com.mass.entity.DemoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DemoDAO {
    List<DemoEntity> getAll();
}
