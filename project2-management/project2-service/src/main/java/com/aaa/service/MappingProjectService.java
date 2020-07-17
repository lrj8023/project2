package com.aaa.service;


import com.aaa.mapper.MappingProjectMapper;
import com.aaa.model.MappingProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.status.SelectStatus.*;

/**
 * Package: com.aaa.service
 * Description： TODO
 * Author: yao
 * Date: Created in 2020/7/16 10:35
 * Copyright: Copyright (c) 2017
 * Version: 0.0.1
 */
//项目管理
@Service
public class MappingProjectService {
@Autowired
private MappingProjectMapper mappingProjectMapper;

    //测绘项目管理，项目名称模糊查询，类型 ，日期精确查
    public Map<String,Object> projectSelect(MappingProject mappingProject) {
        HashMap<String, Object> resultMap = new HashMap<>();
        List<HashMap> restdata = new ArrayList<>();
        if (null ==  mappingProject) {
            restdata = mappingProjectMapper.selectAllProject();
        }else {
            restdata = mappingProjectMapper.projectSelect(mappingProject);
        }
        if (restdata.size() > 0) {
            resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
            resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
            resultMap.put("data",restdata);
        }else {
            resultMap.put("code",SELECT_DATA_FAILED.getCode());
            resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
        }
        return  resultMap;
    }

    //通过字段查询所有项目
    public Map<String,Object>selectName(String name){
        HashMap<String,Object> resultMap = new HashMap<>();
        List<HashMap> restdata = new ArrayList<>();
        restdata = mappingProjectMapper.selectName(name);
        if (restdata.size()>0) {
            resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
            resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
            resultMap.put("data",restdata);
        }else {
            resultMap.put("code",SELECT_DATA_FAILED.getCode());
            resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
        }
        return  resultMap;
    }
    //通过ID查询项目
    public  HashMap<String,Object>projectDetail(String id){
        HashMap<String,Object>resultMap = new HashMap<>();
        if (null !=id&& !("").equals(id)) {
        List<HashMap> restdata = mappingProjectMapper.projectDetail(id);
            if (restdata.size() ==1) {
                resultMap.put("code",SELECT_DATA_BY_ID_SUCCESS.getCode());
                resultMap.put("msg",SELECT_DATA_BY_ID_SUCCESS.getMsg());
                resultMap.put("data",restdata);
                return  resultMap;
            }
        }
        resultMap.put("code",SELECT_DATA_BY_ID_FAILED.getCode());
        resultMap.put("msg",SELECT_DATA_BY_ID_FAILED.getMsg());
        return resultMap;
    }

}
