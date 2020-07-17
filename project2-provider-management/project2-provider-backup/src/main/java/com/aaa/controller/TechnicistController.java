package com.aaa.controller;

import com.aaa.model.Technicist;
import com.aaa.service.TechnicistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/15 19:36
 * @Description:
 *           测绘管理-单位基本信息-技术人员信息
 */
@RestController
public class TechnicistController {
   @Autowired
    private TechnicistService technicistService;
   /**
    *@author: Cancer:栗仁杰
    *@description:
    *      获取技术人员信息
    *@param: []
    *@date: 19:40 2020/7/15
    *@return:
    *@throws:
    **/

   @PostMapping("/queryTechnicist")
    public List<Technicist> queryTechnicist(@RequestParam("userId") Long userId){
    List<Technicist> technicists = technicistService.queryTechnicist(userId);
       if (technicists != null) {
           return technicists;
       }
       return null;
   }

   @PostMapping("/updateTechnicist")
    public Boolean updateTechnicist(@RequestBody Technicist technicist){
       return technicistService.updateTechnicist(technicist);
   }
}
