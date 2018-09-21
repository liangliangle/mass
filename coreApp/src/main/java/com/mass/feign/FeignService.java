package com.mass.feign;

import com.mass.feign.dto.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeignService {
  @Autowired
  private FeignController feignController;

  public List<SysUser> getUserByMassName(List<String> names) {
    List<SysUser> result = null;
    while (result == null) {
      try {
        result = feignController.getUsersBymassNames(names);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return result;
  }

}
