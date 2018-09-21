package com.mass.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mass.common.controller.BaseController;
import com.mass.common.exception.ServiceException;
import com.mass.dao.DictionaryMapper;
import com.mass.dao.SemesterMapper;
import com.mass.dto.dictionary.Classes;
import com.mass.entity.Dictionary;
import com.mass.entity.Semester;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
@EnableEurekaClient
@RequestMapping("/api/dictionary")
@Api(value = "字典相关接口", consumes = "application/json")
public class DictionaryController extends BaseController {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SemesterMapper semesterMapper;

    @PostMapping("classes")
    @ApiOperation("添加班级")
    public void addClasses(@RequestBody Classes classes) throws IOException {

        Dictionary dictionary = dictionaryMapper.selectByPrimaryKey("classes");
        String vaule = dictionary.getValue();
        Classes[] clazzs=objectMapper.readValue(vaule, Classes[].class);

        List<Classes> classesList =new ArrayList<Classes>();
        if(clazzs.length>0){
            classesList=Arrays.asList(clazzs);
        }
        for (Classes c : classesList) {
            if (c.getClasses().equals(classes.getClasses())) {
                throw new ServiceException("存在相同班级");
            }
        }
        classesList=new ArrayList<>(classesList);
        classesList.add(classes);
        dictionary.setValue(objectMapper.writeValueAsString(classesList));
        dictionary.setLastModifyTime(new Date());
        dictionaryMapper.updateByPrimaryKey(dictionary);

    }

    @DeleteMapping("classes")
    @ApiOperation("删除班级")
    public void rmClasses(@RequestParam("classes") String classes) throws IOException {
        Dictionary dictionary = dictionaryMapper.selectByPrimaryKey("classes");
        String vaule = dictionary.getValue();
        List<Classes> classesList = Arrays.asList(objectMapper.readValue(vaule, Classes[].class));
        classesList=new ArrayList<>(classesList);
        int i = 0;
        for (i = 0; i < classesList.size(); i++) {
            if (classesList.get(i).getClasses().equals(classes)) {
                break;
            }
        }
        classesList.remove(i);
        dictionary.setValue(objectMapper.writeValueAsString(classesList));
        dictionary.setLastModifyTime(new Date());
        dictionaryMapper.updateByPrimaryKey(dictionary);

    }
    @PutMapping("classes")
    @ApiOperation("修改班级")
    public void editClasses(@RequestBody() Classes classes) throws IOException {
        Dictionary dictionary = dictionaryMapper.selectByPrimaryKey("classes");
        String vaule = dictionary.getValue();
        List<Classes> classesList = Arrays.asList(objectMapper.readValue(vaule, Classes[].class));
        int i = 0;
        for (i = 0; i < classesList.size(); i++) {
            if (classesList.get(i).getClasses().equals(classes.getClasses())) {
                break;
            }
        }
        classesList=new ArrayList<>(classesList);
        classesList.remove(i);
        classesList.add(classes);
        dictionary.setValue(objectMapper.writeValueAsString(classesList));
        dictionary.setLastModifyTime(new Date());
        dictionaryMapper.updateByPrimaryKey(dictionary);

    }


    @GetMapping("classes")
    @ApiOperation("获取全部班级")
    public Classes[] getAllClasses() throws IOException {
        Dictionary dictionary = dictionaryMapper.selectByPrimaryKey("classes");
        String vaule = dictionary.getValue();
        return objectMapper.readValue(vaule, Classes[].class);
    }

    @GetMapping("semester")
    @ApiOperation("获取当前学期")
    public Semester getCurrent() {
        return semesterMapper.selectByCurrent();
    }

    @PostMapping("semester")
    @ApiOperation("添加学期")
    public void addSemster(@RequestBody Semester semester){
        semester.buildDefaultTimeStamp();
        if(semester.getStartTime().getTime()>System.currentTimeMillis()){
            throw new ServiceException("学期开始日期不可大于当前日期");
        }
        Semester current=semesterMapper.selectByCurrent();
        if(current.getEndTime().getTime()>semester.getStartTime().getTime()){
            throw new ServiceException("与当前学期时间冲突");
        }
        semesterMapper.insert(semester);
    }

}
