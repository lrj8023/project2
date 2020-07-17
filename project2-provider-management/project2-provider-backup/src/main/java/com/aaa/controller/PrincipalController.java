package com.aaa.controller;

import com.aaa.model.Principal;
import com.aaa.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/16 8:54
 * @Description:
 */
@RestController
public class PrincipalController {

    @Autowired
    private PrincipalService principalService;
    /**
     *@author: Cancer:栗仁杰
     *@description: 获取负责人信息
     *@param: []
     *@date: 8:59 2020/7/16
     *@return:
     *@throws:
     **/
    @PostMapping("/queryPrincipal")
    public List<Principal> queryOne(@RequestParam("userId") Long userId){
        List<Principal> principals = principalService.queryOne(userId);
        if (principals != null) {
            return principals;

        }
        return null;
    }
    /**
     *@author: Cancer:栗仁杰
     *@description: 修改负责人信息
     *@param: []
     *@date: 9:02 2020/7/16
     *@return:
     *@throws:
     **/
    @PostMapping("/updateList")
    public Boolean updateList(@RequestBody Principal principal){

        return principalService.updateList(principal);
    }
}
