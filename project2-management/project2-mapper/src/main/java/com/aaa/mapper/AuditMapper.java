package com.aaa.mapper;

import com.aaa.model.Audit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/17 10:25
 * @Description:
 */
public interface AuditMapper extends Mapper<Audit> {
    List<Audit> selectAuditByRefId(Integer refid);
}
