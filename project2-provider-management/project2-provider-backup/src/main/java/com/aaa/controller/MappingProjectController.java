package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingProject;
import com.aaa.model.MappingUnit;
import com.aaa.service.MappingProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.aaa.status.SelectStatus.*;

/**
 * Package: com.aaa.controller
 * Description： TODO
 * Author: yao
 * Date: Created in 2020/7/16 10:43
 * Copyright: Copyright (c) 2017
 * Version: 0.0.1
 */
@RestController
public class MappingProjectController extends CommonController<MappingUnit> {
    @Autowired
    private MappingProjectService mappingProjectService;

    //测绘项目管理，项目名称模糊查询，类型 ，日期精确查
    @PostMapping("/projectSelect")
    public ResultData projectSelect(@RequestBody MappingProject mappingProject) {
        Map<String, Object> resultMap = mappingProjectService.projectSelect(mappingProject);
        if (SELECT_DATA_SUCCESS.getCode().equals(resultMap.get("data"))) {
            return super.selectSuccess(resultMap.get("data"));
        }else {
            return  super.selectFailed();
        }
    }

    //通过字段查询所有的类型和开工日期。分组
    @PostMapping("/SelectName")
    public ResultData SelectName(@RequestParam("name") String name){
        System.out.println(name);
        Map<String,Object> resultMap =  mappingProjectService.selectName(name);
        if (SELECT_DATA_BY_ID_SUCCESS.getCode().equals(resultMap.get("code"))) {
            return  super.selectSuccess(resultMap);
        }else{
            return  super.selectFailed();
        }
    }
    //通过id查询
    public  ResultData projectDetail(@RequestParam("id") String id){
        Map<String,Object> resultMap = mappingProjectService.projectDetail(id);
        if (SELECT_DATA_BY_ID_SUCCESS.getCode().equals(resultMap.get("code"))) {
            return  super.selectSuccess(resultMap);
        }else{
            return  super.selectFailed();
        }
    }

    @Override
    public BaseService<MappingUnit> getBaseService() {
        return null;
    }
}
