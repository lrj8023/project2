package com.aaa.mapper;

import com.aaa.model.SpecialPost;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
public interface SpecialPostMapper extends Mapper<SpecialPost> {
    /**
     * 查询
     * @param userId
     * @return
     */

    List<SpecialPost> selectSpecialPost(Long userId);

}