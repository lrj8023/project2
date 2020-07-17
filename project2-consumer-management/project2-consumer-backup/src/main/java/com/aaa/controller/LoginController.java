package com.aaa.controller;

import com.aaa.annotation.LoginAnnotation;
import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.IProjectService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author jyz
 * @date 2020/7/15 15:54
 **/
@RestController
public class LoginController extends BaseController {
    @Autowired
    private IProjectService projectService;
    /**
     * @Author jyz
     * @Description //TODO 执行登录操作
     * @Date 15:57 2020/7/15
     * @Param [user]
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/doLogin")
    @LoginAnnotation(opeationType = "登录操作" , opeationName = "管理员登录")
    public ResultData doLogin(User user){
        return projectService.doLogin(user);
    }
}
