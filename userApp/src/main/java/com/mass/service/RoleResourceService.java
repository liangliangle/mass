package com.mass.service;

import com.mass.dao.SysResourceMapper;
import com.mass.dao.SysRoleMapper;
import com.mass.dto.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleResourceService {
  @Autowired
  private SysRoleMapper roleMapper;
  @Autowired
  private SysResourceMapper resourceMapper;

  public List<SysResource> getResource(Long id) {
    List<SysResource> resources = resourceMapper.findByRole(id);
    List<SysResource> result = new ArrayList<>();
    for (SysResource resource : resources) {
      if (resource.getParentId() == 0) {
        result.add(resource);
      }
    }
    for (SysResource resource : resources) {
      if (!resource.getParentId().equals(0)) {
        for (SysResource parent : result) {
          if (parent.getId().equals(resource.getParentId())) {
            if (parent.getChildren() == null) {
              parent.setChildren(new ArrayList<SysResource>());
            }
            parent.getChildren().add(resource);
          }
        }
      }
    }
    return result;
  }
}
