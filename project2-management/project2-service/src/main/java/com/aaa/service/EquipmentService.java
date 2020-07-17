package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.EquipmentMapper;
import com.aaa.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/15 20:59
 * @Description:
 */
@Service
public class EquipmentService extends BaseService<Equipment> {

    @Autowired
    private EquipmentMapper equipmentMapper;


    public List<Equipment> selectEquipment(Long userId){
        try {
            if (!"".equals(userId)) {
                List<Equipment> equipmentList = equipmentMapper.selectEquipment(userId);
                if (equipmentList != null && !"".equals(equipmentList)) {
                    return equipmentList;
                } else {
                    return null;
                }
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
