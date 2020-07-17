package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.TechnicistMapper;
import com.aaa.model.Technicist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/15 17:10
 * @Description:
 * 测绘管理-单位基本信息-技术人员信息
 */
@Service
public class TechnicistService extends BaseService<Technicist> {
    @Autowired
    private TechnicistMapper technicistMapper;

    /**
     *@author: Cancer:栗仁杰
     *@description:
     *       获取技术人员信息
     *@param: []
     *@date: 18:48 2020/7/15
     *@return:
     *@throws:
     **/
    public List<Technicist> queryTechnicist(Long userId){
        /**
         * 根据userId去查询技术人员信息
         */
       List<Technicist> technicists = technicistMapper.queryTechnicist(userId);
        if (technicists != null) {
            //不为空返回信息
            return technicists;
        }
        return null;
    }
    /**
     *@author: Cancer:栗仁杰
     *@description:
     *        修改技术人员信息
     *@param: []
     *@date: 18:54 2020/7/15
     *@return:
     *@throws:
     **/
    public Boolean updateTechnicist(Technicist technicist){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);
        Technicist technicist1 = technicist.setId(technicist.getId())
                .setName(technicist.getName())
                .setIdNumber(technicist.getIdNumber())
                .setMajorType(technicist.getMajorType())
                .setSex(technicist.getSex())
                .setAge(technicist.getAge())
                .setMajor(technicist.getMajor())
                .setDuty(technicist.getDuty())
                .setTitleMajor(technicist.getTitleMajor())
                .setModifyTime(format);
        if (null != technicist1){
            int i = technicistMapper.updateTechnicist(technicist1);
            if (i>0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }


}
