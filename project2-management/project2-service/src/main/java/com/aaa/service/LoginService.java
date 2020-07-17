package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.model.User;
import com.aaa.vo.TokenVo;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author jyz
 * @date 2020/7/15 16:02
 **/
@Service
public class LoginService extends BaseService<User> {
    /**
     * @Author jyz
     * @Description //TODO 执行登录操作
     * @Date 16:03 2020/7/15
     * @Param [user]
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo doLogin(User user){
        TokenVo tokenVo = new TokenVo();
        User user1 = new User();
        //判断user是否为null
        if (null != user){
            user1.setUsername(user.getUsername());
            User user2 = super.selectOne(user1);
            //判断user2是否为null
            if (null == user2){
                tokenVo.setIfSuccess(false).setType(1);
                return tokenVo;
            }else {
                //用户名通过，查询密码
                user1.setPassword(user.getPassword());
                User user3 = super.selectOne(user1);
                //判断user3 是否为空
                if (null == user3){
                    tokenVo.setIfSuccess(false).setType(2);
                    return tokenVo;
                }else {
                    //登录成功
                    String token = UUID.randomUUID().toString().replaceAll("-","");
                    user3.setToken(token);
                    Integer updateResult = super.update(user3);
                    if (updateResult > 0){
                        tokenVo.setIfSuccess(true).setToken(token);
                    }else {
                        tokenVo.setIfSuccess(false).setType(4);
                        return tokenVo;
                    }
                }
            }
        }else {
            tokenVo.setIfSuccess(false).setType(4);
            return tokenVo;
        }
        return tokenVo;
    }
}
