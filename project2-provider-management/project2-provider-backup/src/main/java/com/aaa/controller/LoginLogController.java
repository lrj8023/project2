package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.LoginLog;
import com.aaa.model.User;
import com.aaa.service.LoginLogService;
import com.aaa.service.LoginService;
import com.aaa.vo.TokenVo;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.aaa.status.LoginStatus.*;

/**
 * @ClassName LoginLogController
 * @Description TODO
 * @Author jyz
 * @date 2020/7/15 16:22
 **/
@RestController
public class LoginLogController extends CommonController<LoginLog> {
    @Autowired
    private LoginLogService loginLogService;

    @Override
    public BaseService<LoginLog> getBaseService() {
        return loginLogService;
    }

    /**
     * @Author jyz
     * @Description //TODO 保存日志
     * @Date 19:10 2020/7/15
     * @Param [loginLog]
     * @return java.lang.Integer
     **/
    @PostMapping("/addLoginLog")
    public Integer addLoginLog(@RequestBody LoginLog loginLog) {
        return getBaseService().add(loginLog);
    }

}
