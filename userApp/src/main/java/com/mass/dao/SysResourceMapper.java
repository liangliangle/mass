package com.mass.dao;

import com.mass.dto.SysResource;
import com.mass.dto.SysResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysResourceMapper {
   List<SysResource> findByParent(Long parentId);

   List<SysResource> findByRole(Long roleId);
}