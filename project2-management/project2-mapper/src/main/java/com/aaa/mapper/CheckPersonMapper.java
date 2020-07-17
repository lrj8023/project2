package com.aaa.mapper;

import com.aaa.model.CheckPerson;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/16 10:55
 * @Description:
 */
public interface CheckPersonMapper extends Mapper<CheckPerson> {
    int deleteByPrimaryKey(Long id);

    int insert(CheckPerson checkPerson);

    CheckPerson selectByPrimaryKey(Long id);

    List<CheckPerson> selectAll(HashMap hashMap);

    int updateByPrimaryKey(CheckPerson checkPerson);

}
