package com.aaa.controller;

import com.aaa.model.Equipment;
import com.aaa.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 *
 * @Date: Created in 2020/7/16 8:45
 * @Description:
 *        测绘管理-单位基本信息-仪器设备信息
 */
@RestController
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;
/**
 *@author: Cancer:栗仁杰
 *@description: 获取仪器信息
 *@param: []
 *@date: 9:02 2020/7/16
 *@return:
 *@throws:
 **/
    @PostMapping("/queryEquipment")
    public List<Equipment> selectEquipment(@RequestParam("userId") Long userId){
        try {
            //根据userID查询仪器设备
            List<Equipment> equipment = equipmentService.selectEquipment(userId);
            return equipment;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
