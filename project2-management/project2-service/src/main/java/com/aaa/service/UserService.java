package com.aaa.service;

import com.aaa.base.BaseController;
import com.aaa.base.BaseService;
import com.aaa.base.ResultData;
import com.aaa.mapper.UserMapper;
import com.aaa.model.User;
import com.aaa.redis.RedisService;
import com.aaa.utils.DateUtils;
import com.aaa.vo.TokenVo;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.*;

import static com.aaa.status.LoginStatus.*;
import static com.aaa.status.OperationStatus.FAILED;
import static com.aaa.status.OperationStatus.SUCCESS;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author jyz
 * @date 2020/7/15 16:58
 **/
@Service
public class UserService extends BaseService<User> {
    @Autowired
    private  UserMapper userMapper;

    /**
     * @Author jyz
     * @Description //TODO 新增用户
     * @Date 15:40 2020/7/16
     * @Param [user]
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo insert(User user){
        TokenVo tokenVo = new TokenVo();
        //判断user是否为空
        if (null != user){
            //user不为空
            //判断user是否存在
            User user1 = new User();
            user1.setUsername(user.getUsername());
            User user2 = super.selectOne(user1);
            if (null != user2){
                //说明用户名已经存在
                return tokenVo.setIfSuccess(false).setType(2);
            }else {
                //新增用户时添加密码为初始密码:pwd123456
                user.setPassword("pwd123456");
                //新增用户名可用，执行新增操作
                String token = UUID.randomUUID().toString().replaceAll("-","");
                user2.setToken(token);
                Integer result = super.add(user2);
                if (result > 0){
                    //增加成功
                    tokenVo.setIfSuccess(true).setToken(token);
                }else {
                        tokenVo.setIfSuccess(false).setType(5);
                        return tokenVo;
                }
            }
        }else {
            //user为空
            tokenVo.setIfSuccess(false).setType(5);
            return tokenVo;
        }
        return tokenVo;
    }

    /**
     * @Author jyz
     * @Description //TODO 删除用户
     * @Date 15:49 2020/7/16
     * @Param [ids]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> delUser(List<Long> ids){
        Map<String ,Object> resultMap = new HashMap<String, Object>();
        Example example = Example.builder(User.class).where(Sqls.custom().andIn("id",ids)).build();
        int i = userMapper.deleteByExample(example);
        if (i > 0){
            resultMap.put("code",SUCCESS.getCode());
            resultMap.put("msg",SUCCESS.getMsg());
        }else {
            resultMap.put("code",FAILED.getCode());
            resultMap.put("msg",FAILED.getMsg());
        }
        return resultMap;
    }

    /**
     * @Author jyz
     * @Description //TODO 更新用户信息
     * @Date 16:17 2020/7/16
     * @Param [user]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> updateUser(User user){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Date date = new Date();
        user.setModifyTime(date.toString());
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i > 0){
            resultMap.put("code", SUCCESS.getCode());
            resultMap.put("msg", SUCCESS.getMsg());
        }else{
            resultMap.put("code", FAILED.getCode());
            resultMap.put("msg", FAILED.getMsg());
        }
        return resultMap;
    }
    /**
     * @Author jyz
     * @Description //TODO 查询所有用户信息
     * @Date 16:18 2020/7/16
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> selectAll() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<User> users = userMapper.selectAll();
        if (null != users && !users.isEmpty()){
            resultMap.put("code",SUCCESS.getCode());
            resultMap.put("msg",SUCCESS.getMsg());
            resultMap.put("data",users);
            return resultMap;
        }else{
            resultMap.put("code",FAILED.getCode());
            resultMap.put("msg",FAILED.getMsg());
        }
        return resultMap;
    }

    public Map<String,Object> selectUserAll(HashMap map, RedisService redisService){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Object tokenVal = redisService.getOne(map.get("tokenId").toString());
        //检测token
        if (null == tokenVal){
            resultMap.put("code",LOGOUT_WRONG.getCode());
            resultMap.put("msg",LOGOUT_WRONG.getMsg());
            return resultMap;
        }
        if(map.size()>0){
            //调用BaseService分页条件查询
            PageInfo<HashMap> pageInfo = super.selectUserPageInfo(map);
            if (null != pageInfo && pageInfo.getSize() > 0 ){
                resultMap.put("code", SUCCESS.getCode());
                resultMap.put("msg", pageInfo);
                return resultMap;
            }else{
                resultMap.put("code", FAILED.getCode());
                resultMap.put("msg", FAILED.getMsg());
            }
        }
        return resultMap;
    }
}
