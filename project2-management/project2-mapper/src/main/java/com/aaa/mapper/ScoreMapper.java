package com.aaa.mapper;

import com.aaa.model.Score;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/16 15:11
 * @Description:
 */
public interface ScoreMapper extends Mapper<Score> {
    List<Score> selectScoreByRefId(Integer userId);
}
