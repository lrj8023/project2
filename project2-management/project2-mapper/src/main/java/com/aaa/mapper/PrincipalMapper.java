package com.aaa.mapper;

import com.aaa.model.Principal;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/15 20:15
 * @Description:
 */
public interface PrincipalMapper extends Mapper<Principal> {
    List<Principal> queryOne(Long id);
    int updateList(Principal principal);
}
