package com.mass.controller;

import com.kcss.oss.service.OssService;
import com.mass.common.exception.ServiceException;
import com.mass.dto.query.ExportRegister;
import com.mass.service.RegisterService;
import com.mass.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 下午3:02 2018/5/25 2018
 * @Modify:
 */

@EnableAutoConfiguration
@EnableEurekaClient
@RestController
@RequestMapping(value = "/api/export")
@Api(value = "导出相关接口")
public class ExportController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private OssService ossService;
    private static Logger LOGGER = Logger.getLogger(ExportController.class.getName());


    @PostMapping(value = "/register")
    @ApiOperation("查询签到")
    public void export(HttpServletResponse response, @RequestBody ExportRegister register) throws Exception {
        String fileName = register.getName();
        response.setContentType("application/force-download");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        List<List<String>> result = registerService.export(register);
        ExcelUtil.createExcel(response, result, fileName);
    }


    @PostMapping("img")
    @ApiOperation("上传图片，返回地址")
    public String uploadImg(@RequestParam("file") MultipartFile file,
                            HttpServletRequest request) throws IOException {
        File realFile = saveFile(file, request);
        String url = ossService.uploadFile(realFile);
        LOGGER.info(url);
        return url;
    }


    private File saveFile(MultipartFile multipartFile, HttpServletRequest request) {
        // 判断文件是否为空
        if (!multipartFile.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath("/")
                        + multipartFile.getOriginalFilename();
                // 转存文件
                LOGGER.info(filePath);
                File realFile = new File(filePath);
                if (!realFile.exists()) {
                    realFile.createNewFile();
                }
                multipartFile.transferTo(realFile);
                return realFile;
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException("保存文件失败");
            }
        }
        return null;
    }
}
