package com.aaa.mapper;

import com.aaa.model.Equipment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/15 20:02
 * @Description:
 */
public interface EquipmentMapper extends Mapper<Equipment> {
    /**
     * 查询
     * @param userId
     * @return
     */
    List<Equipment> selectEquipment(Long userId);
}
