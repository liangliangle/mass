package com.mass.controller;

import com.mass.common.controller.BaseController;
import com.mass.dto.SysUser;
import com.mass.entity.DataResult;
import com.mass.service.UserService;
import com.mass.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@EnableAutoConfiguration
@EnableEurekaClient
@RequestMapping("api/file")
@Api(value = "导入导出用户信息")
public class UserExcelController extends BaseController {
  @Autowired
  UserService userService;

  /**
   * 导入用户信息 **
   *
   * @描述
   * @参数 [file, request]
   * @返回值 com.mass.entity.DataResult
   * @创建人 慧强
   * @创建时间 2018/3/31
   * @修改人和其它信息
   */
  @ApiOperation(value = "导入人员excel")
  @PostMapping(value = "user/import")
  public DataResult userImport(@RequestParam("fileName") MultipartFile file, HttpServletRequest request) throws IOException {
    DataResult result = new DataResult();
    if (file.isEmpty()) {
      result.setStatus(500);
    }
    String fileName = file.getOriginalFilename();
    int size = (int) file.getSize();
    System.out.println(fileName + "-->" + size);
    //获取upload文件夹得真实路径
    String realpath = request.getSession().getServletContext().getRealPath("/resources/upload");
    System.out.println(realpath);
    File targetFile = new File(realpath, fileName);
    System.out.println(targetFile.getName());
    if (!targetFile.exists()) {
      targetFile.getParentFile().mkdirs();
      targetFile.createNewFile();
      System.out.println(targetFile.getAbsolutePath());

    }
    try {
      file.transferTo(targetFile); //保存文件
    } catch (IOException e) {
      e.printStackTrace();
    }

    FileInputStream fileInputStream = new FileInputStream(targetFile);
    List<SysUser> list = ExcelUtil.praseXls(fileInputStream, 2000);
    if (targetFile.exists()) {
      if (targetFile.delete()) {
        System.out.println("file {} is deleted!" + targetFile.getPath());
      }
    }
    userService.insertUserList(list);
    return result;
  }

  /**
   * ke 根据社团名称进行分别到处**
   *
   * @描述
   * @参数 [httpServletRequest, httpServletResponse, name]
   * @返回值 void
   * @创建人 慧强
   * @创建时间 2018/3/31
   * @修改人和其它信息
   */
  @ApiOperation(value = "根据社团名称导出人员excel")
  @GetMapping(value = "user/export", produces = "application/force-download")
  public void userExport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(required = false, value = "orgName") String name) throws IOException {

    List<SysUser> list = userService.getuserList(name);
    userService.exportUserByQuery(httpServletRequest, httpServletResponse, list);

  }


}
