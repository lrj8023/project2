package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingUnit;
import com.aaa.service.MappingUnitService;
import com.aaa.service.PrincipalService;
import com.aaa.service.TechnicistService;
import com.aaa.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/17 15:19
 * @Description:
 */
@RestController
@RequestMapping("/unit")
public class UnitInfoController extends CommonController<MappingUnit> {
    @Autowired
    private PrincipalService principalService;

    @Autowired
    private TechnicistService technicistService;

    @Autowired
    private MappingUnitService mappingUnitService;

    @Autowired
    private UploadService uploadService;
    /**
     *@author: Cancer:栗仁杰
     *@description:
     *    查询单位基本信息
     *@param: []
     *@date:  2020/7/17
     *@return:
     *@throws:
     **/
    @PostMapping("/selectUnitInfo")
    public ResultData selectUnitInfo(@RequestParam("userId") String userId){
        MappingUnit unit = new MappingUnit();
        unit.setUserId(Long.valueOf(userId));
        MappingUnit mappingUnit = mappingUnitService.
    }






































    @Override
    public BaseService<MappingUnit> getBaseService() {
        return null;
    }
}
