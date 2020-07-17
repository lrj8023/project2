package com.aaa.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName LoginLog
 * @Description TODO
 * @Author jyz
 * @date 2020/7/15 15:08
 **/
@Data
@Table(name = "t_login_log")
@Accessors(chain = true)
public class LoginLog implements Serializable {
    /**
     * 用户名
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * 登录时间
     */
    @Column(name = "LOGIN_TIME")
    private String loginTime;

    /**
     * 登录地点
     */
    @Column(name = "LOCATION")
    private String location;

    /**
     * IP地址
     */
    @Column(name = "IP")
    private String ip;

    @Column(name = "OPERATION_TYPE")
    private String operationType;

    @Column(name = "OPERATION_NAME")
    private String operationName;

}
