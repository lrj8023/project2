package com.aaa.mapper;

import com.aaa.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface UserMapper extends Mapper<User> {

    List<User> selectUserAll(HashMap map);
}