package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.PrincipalMapper;
import com.aaa.model.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/15 20:59
 * @Description:
 *           测绘管理-单位基本信息-负责人信息
 */
@Service
public class PrincipalService extends BaseService<Principal> {
    @Autowired
    private PrincipalMapper principalMapper;

    public List<Principal> queryOne(Long userId){
        List<Principal> principal = principalMapper.queryOne(userId);
        if (principal != null) {
            return principal;
        }
        return null;
    }
/**
 *@author: Cancer:栗仁杰
 *@description: 修改负责人信息
 *@param: []
 *@date: 11:51 2020/7/16
 *@return:
 *@throws:
 **/
    public Boolean updateList(Principal principal){
        //获取时间
        Date date = new Date();
        //设置时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);
        //获取负责人信息
        Principal principal1 = principal.setId(principal.getId())
                .setType(principal.getType())
                .setName(principal.getName())
                .setIdNumber(principal.getIdNumber())
                .setAge(principal.getAge())
                .setSex(principal.getSex())
                .setMajor(principal.getMajor())
                .setDuty(principal.getDuty())
                .setModifyTime(format);
        if (null != principal1){
            int i = principalMapper.updateList(principal1);
            if (i>0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }
}
