package com.aaa.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.mapper.CheckPersonMapper;
import com.aaa.model.CheckPerson;
import com.aaa.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.status.AddStatus.ADD_DATA_FAILED;
import static com.aaa.status.AddStatus.ADD_DATA_SUCCESS;
import static com.aaa.status.OperationStatus.*;
import static com.aaa.status.SelectStatus.SELECT_DATA_FAILED;
import static com.aaa.status.SelectStatus.SELECT_DATA_SUCCESS;
import static com.aaa.status.UpdateStatus.UPDATE_DATA_FAILED;
import static com.aaa.status.UpdateStatus.UPDATE_DATA_SUCCESS;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/16 11:38
 * @Description:
 *            用于用户的增删改
 */
public class CheckPersonService {



    @Autowired
    private CheckPersonMapper checkPersonMapper;
    /**
     *@author: Cancer:栗仁杰
     *@description:
     *         查询
     *@param: []
     *@date: 8:45 2020/7/17
     *@return:
     *@throws:
     **/
    public Map<String,Object> selectAllCheckPerson(HashMap hashMap){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<CheckPerson> checkPeople= checkPersonMapper.selectAll(hashMap);
        if (checkPeople.size()>0 && checkPeople != null) {
            resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
            resultMap.put("code",SELECT_DATA_SUCCESS.getMsg());

        }else {
            resultMap.put("code", SELECT_DATA_FAILED.getCode());
            resultMap.put("code", SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;
    }
    /**
     *@author: Cancer:栗仁杰
     *@description:
     *        新增抽查人员
     *@param: []
     *@date: 14:43 2020/7/16
     *@return:
     *@throws:
     **/
    public HashMap<String,Object> addCheckPerson(CheckPerson checkPerson) {
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        int addCheckPerson = checkPersonMapper.insertSelective(checkPerson);
        if (addCheckPerson >0) {
            resultMap.put("code", ADD_DATA_SUCCESS.getCode());
            resultMap.put("msg", ADD_DATA_SUCCESS.getCode());
        }else{
            resultMap.put("code", ADD_DATA_FAILED.getCode());
            resultMap.put("msg", ADD_DATA_FAILED.getCode());


        }
        return resultMap;
    }
/**
 *@author: Cancer:栗仁杰
 *@description:
 *        修改抽查人员信息
 *@param: []
 *@date: 10:09 2020/7/17
 *@return:
 *@throws:
 **/
    public Map<String,Object> updateCheckPerson(CheckPerson checkPerson){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        checkPerson.setModifyTime(DateUtil.now());
        CheckPerson checkPerson1 = checkPersonMapper.selectByPrimaryKey(checkPerson);
        if (checkPerson1 != null) {
            String creatTime = checkPerson1.getCreateTime();
            checkPerson.setCreateTime(creatTime);
        }
        int updateResult = checkPersonMapper.updateByPrimaryKey(checkPerson);
        if (updateResult>0) {
            resultMap.put("code", UPDATE_DATA_SUCCESS.getCode());
            resultMap.put("msg", UPDATE_DATA_SUCCESS.getMsg());
        }else {
            resultMap.put("code", UPDATE_DATA_FAILED.getCode());
            resultMap.put("msg", UPDATE_DATA_FAILED.getMsg());
        }
        return resultMap;
    }


}
