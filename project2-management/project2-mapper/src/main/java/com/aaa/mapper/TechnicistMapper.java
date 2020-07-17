package com.aaa.mapper;

import com.aaa.model.Technicist;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
public interface TechnicistMapper extends Mapper<Technicist> {

    List<Technicist> queryTechnicist(Long id);

    int updateTechnicist(Technicist technicist);

}