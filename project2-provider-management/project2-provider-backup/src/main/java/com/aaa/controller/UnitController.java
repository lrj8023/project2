package com.aaa.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Audit;
import com.aaa.model.Score;
import com.aaa.service.UnitService;
import com.aaa.vo.UnitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.aaa.status.SelectStatus.SELECT_DATA_SUCCESS;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/17 15:20
 * @Description:
 */
@RestController
@RequestMapping("/mappingUnit")
public class UnitController extends BaseController {
    @Autowired
    private UnitService unitService;
    /**
     *@author: Cancer:栗仁杰
     *@description:
     *            单位审核--分页查询单位列表
     *@param: []
     *@date: 15:30 2020/7/17
     *@return:
     *@throws:
     **/
    @PostMapping("/selectUnit")
    ResultData selectUnit(@RequestBody UnitVo unitVo){
        Map<String,Object> stringObjectMap = unitService.selectUnit(unitVo);
        if (SELECT_DATA_SUCCESS.getCode().equals(stringObjectMap.get("code"))){
            return super.selectSuccess(stringObjectMap.get("data"));
        }else {
            return super.selectFailed();
        }
    }
    /**
     *@author: Cancer:栗仁杰
     *@description:
     *          查询单位注册待审核信息
     *@param: []
     *@date: 15:35 2020/7/17
     *@return:
     *@throws:
     **/
    @PostMapping("/selectUpdateUnit")
    ResultData selectUpdateUnit(@RequestBody UnitVo unitVo){
        Map<String, Object> stringObjectMap = unitService.selectUpdateUnit(unitVo);
        if (SELECT_DATA_SUCCESS.getCode().equals(stringObjectMap.get("code"))){
            return super.selectSuccess(stringObjectMap.get("date"));
        }else{
            return super.selectFailed();
        }
    }
    /**
     *@author: Cancer:栗仁杰
     *@description: 修改单位分值
     *@param: [score]
     *@date: 15:36 2020/7/17
     *@return:
     *@throws:
     **/
   @PostMapping("/updateScore")
    ResultData updateScore(@RequestBody Score score){
       score.setCreateTime(DateUtil.now());
       int res = unitService.updateScore(score);
       if (res>0) {
           return super.updateSuccess();
       }else {
           return super.updateFailed();
       }
   }
   /**
    *@author: Cancer:栗仁杰
    *@description:
    *             根据单位id查询分值日志
    *@param: []
    *@date: 16:18 2020/7/17
    *@return:
    *@throws:
    **/
   @PostMapping("/selectScore")
    ResultData selectScore(@RequestParam("unitId") Integer unitId){
       Map<String,Object> stringObjectMap = unitService.selectScore(unitId);
       if (SELECT_DATA_SUCCESS.getCode().equals(stringObjectMap.get("code"))){
           return super.selectSuccess(stringObjectMap.get("date"));
       }else{
           return super.selectFailed();
       }
   }
   /**
    *@author: Cancer:栗仁杰
    *@description:
    *       根据单位id查询审核日志
    *@param: []
    *@date: 16:18 2020/7/17
    *@return:
    *@throws:
    **/

   @PostMapping("/selectAudit")
    ResultData selectAudit(@RequestParam("refid") Integer refid){
       Map<String,Object> stringObjectMap = unitService.selectAudit(refid);
       if (SELECT_DATA_SUCCESS.getCode().equals(stringObjectMap.get("code"))){
           return super.selectSuccess(stringObjectMap.get("data"));
       }else {
           return super.selectFailed();
       }
   }

   /**
    *@author: Cancer:栗仁杰
    *@description:
    *        审核
    *@param: []
    *@date: 16:22 2020/7/17
    *@return:
    *@throws:
    **/
   @PostMapping("/sceneUnit")
    ResultData sceneUnit(@RequestBody Audit audit){
       audit.setCreateTime(DateUtil.now());
       int res = unitService.sceneUnit(audit);
       if (res >0) {
           return super.updateSuccess();
       }else{
           return super.updateFailed();
       }
   }
}
