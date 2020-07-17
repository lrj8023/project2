package com.aaa.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.aaa.base.BaseService;
import com.aaa.mapper.AuditMapper;
import com.aaa.mapper.MappingUnitMapper;
import com.aaa.mapper.ScoreMapper;
import com.aaa.model.Audit;
import com.aaa.model.MappingUnit;
import com.aaa.model.Score;
import com.aaa.utils.FileNameUtils;
import com.aaa.vo.UnitVo;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.status.SelectStatus.SELECT_DATA_FAILED;
import static com.aaa.status.SelectStatus.SELECT_DATA_SUCCESS;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/17 10:17
 * @Description:
 *         单位审核
 */
public class UnitService extends BaseService<MappingUnit> {
    @Autowired
    MappingUnitMapper mappingUnitMapper;
    @Autowired
    private AuditMapper auditMapper;
    @Autowired
    private ScoreMapper scoreMapper;
/**
 *@author: Cancer:栗仁杰
 *@description:
 *    单位审核--分页查询单位列表
 *@param: []
 *@date: 10:40 2020/7/17
 *@return:
 *@throws:
 **/
public Map<String, Object> selectUnit(UnitVo unitVo){
    Map<String,Object> resultMap = new HashMap<>();
    try {
        PageInfo<MappingUnit> mappingUnitPageInfo = super.selectListByPage(
                unitVo.getMappingUnit(), unitVo.getPageNo(), unitVo.getPageSize());
        if (null == mappingUnitPageInfo || "".equals(mappingUnitPageInfo)){
            //说明没有查到
            resultMap.put("code",SELECT_DATA_FAILED.getCode());
            resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
        }else {

        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return resultMap;
}
   /**
    *@author: Cancer:栗仁杰
    *@description:
    *          查询单位修改待审核信息
    *@param: []
    *@date: 10:44 2020/7/17
    *@return:
    *@throws:
    **/

   public Map<String,Object> selectUpdateUnit(UnitVo unitVo){
       Map<String,Object> resultMap = new HashMap<String, Object>();
       try {
           PageInfo<MappingUnit> mappingUnitPageInfo = super.selectListByPage(
                   unitVo.getMappingUnit(), unitVo.getPageNo(), unitVo.getPageSize());
           if (mappingUnitPageInfo == null || "".equals(mappingUnitPageInfo)) {
               //说明没有查到
               resultMap.put("code",SELECT_DATA_FAILED.getCode());
               resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
           }else {
               resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
               resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
               resultMap.put("data",mappingUnitPageInfo);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return resultMap;
   }
   /**
    *@author: Cancer:栗仁杰
    *@description:
    *         查询单位注册待审核信息
    *@param: []
    *@date: 10:56 2020/7/17
    *@return:
    *@throws:
    **/
   public Map<String,Object> selectRegistrationUnit(UnitVo unitVo){
       Map<String,Object> resultMap = new HashMap<String, Object>();
       try {
           PageInfo<MappingUnit> mappingUnitPageInfo = super.selectListByPage(
                   unitVo.getMappingUnit(), unitVo.getPageNo(), unitVo.getPageSize());
           if (null == mappingUnitPageInfo || "".equals(mappingUnitPageInfo)){
               //说明没有查到
               resultMap.put("code",SELECT_DATA_FAILED.getCode());
               resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
           }else {
               resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
               resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
               resultMap.put("data",mappingUnitPageInfo);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return resultMap;
   }
   /**
    *@author: Cancer:栗仁杰
    *@description:
    *           调整单位分值
    *@param: []
    *@date: 10:57 2020/7/17
    *@return:
    *@throws:
    **/
   public int updateScore(Score score) {
       MappingUnit unit = new MappingUnit();
       //获取当前分数和单位id
       Integer score1 = score.getScore();
       //生成一个id，用于分值id
       Long id = FileNameUtils.getFileNameLong();
       Long unitId = score.getUnitId();
       unit.setModifyTime(DateUtil.now());
       unit.setScore(score1);
       unit.setId(unitId);
       score.setId(id);
       int insert = mappingUnitMapper.updateByPrimaryKeySelective(unit);
       if (insert > 0) {
           int insert1 = scoreMapper.insert(score);
           if (insert1 > 0) {
               return insert1;
           }

       }
       return 0;
   }
   /**
    *@author: Cancer:栗仁杰
    *@description:
    *        根据单位id查询分值日志
    *@param: []
    *@date: 14:25 2020/7/17
    *@return:
    *@throws:
    **/
   public Map<String,Object> selectScore(Integer unitId){
       Map<String,Object> resultMap = new HashMap<String, Object>();
        try {
            List<Score> scores = scoreMapper.selectScoreByRefId(unitId);
            if (scores == null || scores.size()>0) {
                //说明没有查到
                resultMap.put("code",SELECT_DATA_FAILED.getCode());
                resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
            }else {
                resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
                resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
                resultMap.put("data",scores);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultMap;
   }
   /**
    *@author: Cancer:栗仁杰
    *@description:    根据单位id查询审核日志
    *@param: []
    *@date: 14:31 2020/7/17
    *@return:
    *@throws:
    **/
   public Map<String, Object> selectAudit(Integer refid){
       Map<String, Object> resultMap = new HashMap<String, Object>();
       try {
           List<Audit> audits = auditMapper.selectAuditByRefId(refid);
           if (audits == null || audits.size()>0) {
               //说明没有查到
               resultMap.put("code",SELECT_DATA_FAILED.getCode());
               resultMap.put("msg",SELECT_DATA_FAILED.getMsg());
           }else {
               resultMap.put("code",SELECT_DATA_SUCCESS.getCode());
               resultMap.put("msg",SELECT_DATA_SUCCESS.getMsg());
               resultMap.put("data",audits);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return resultMap;
   }
   /**
    *@author: Cancer:栗仁杰
    *@description:  审核
    *@param: []
    *@date: 14:40 2020/7/17
    *@return:
    *@throws:
    **/
   public int sceneUnit(Audit audit){
       //生成一个id，用于审核id
       Long id = FileNameUtils.getFileNameLong();
       audit.setId(id);
       //获取审核的状态
       Integer status = audit.getStatus();
       //获取审核单位id
       Long refId = audit.getRefId();
       MappingUnit unit = new MappingUnit();
       unit.setId(refId);
       unit.setUnitStatus(status);
       unit.setModifyTime(DateUtil.now());
       //修改单位的审核状态
       int res = mappingUnitMapper.updateByPrimaryKeySelective(unit);
       if (res >0) {
           int insert = auditMapper.insert(audit);
           if (insert>0) {
               return insert;
           }
       }
       return 0;
   }
}
