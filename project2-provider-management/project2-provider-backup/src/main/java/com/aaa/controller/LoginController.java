package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.LoginService;
import com.aaa.vo.TokenVo;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.aaa.status.LoginStatus.*;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author jyz
 * @date 2020/7/15 16:22
 **/
@RestController
public class LoginController extends CommonController<User> {

    @Autowired
    private LoginService loginService;

    @Override
    public BaseService<User> getBaseService() {
        return loginService;
    }

    /**
     * @Author jyz
     * @Description //TODO 执行登录操作
     * @Date 19:09 2020/7/15
     * @Param [user]
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/doLogin")
    public ResultData doLogin(@RequestBody User user) {
        TokenVo tokenVo = loginService.doLogin(user);
        if(tokenVo.getIfSuccess()) {
            return super.loginSuccess(tokenVo.getToken());
        } else if(tokenVo.getType() == 1) {
            return super.loginFailed(USER_NOT_EXIST.getMsg());
        } else if(tokenVo.getType() == 2) {
            return super.loginFailed(PASSWORD_WRONG.getMsg());
        } else {
            return super.loginFailed(SYSTEM_EXCEPTION.getMsg());
        }
    }

}
