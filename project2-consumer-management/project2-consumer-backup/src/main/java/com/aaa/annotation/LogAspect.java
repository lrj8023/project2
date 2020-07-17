package com.aaa.annotation;

import com.aaa.model.LoginLog;
import com.aaa.model.User;
import com.aaa.service.IProjectService;
import com.aaa.utils.AddressUtils;
import com.aaa.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.util.DateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.logging.StreamHandler;

import static com.aaa.staticproperties.TimeForatProperties.TIME_FORMAT;

/**
 * @ClassName LogAspect
 * @Description TODO
 * @Author jyz
 * @date 2020/7/15 15:26
 **/
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private IProjectService projectService;
    /**
     * @Author jyz
     * @Description //TODO 定义切点信息
     * @Date 15:30 2020/7/15
     * @Param []
     * @return void
     **/
    @Pointcut("@annotation(com.aaa.annotation.LoginAnnotation)")
    public void pointcut(){
        //TODO noting to do
    }
    /**
     * @Author jyz
     * @Description //TODO 定义环形切面
     * @Date 15:30 2020/7/15
     * @Param [proceedingJoinPoint]
     * @return java.lang.Object
     **/
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws ClassNotFoundException {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //获取Request对象
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        //获取IP地址
        String ipAddr = IPUtils.getIpAddr(request);
        //获取地理位置
        Map<String,Object> addressMap = AddressUtils.getAddresses(ipAddr,"UTF-8");

        LoginLog loginLog = new LoginLog();
        loginLog.setIp(ipAddr);
        loginLog.setLocation(addressMap.get("province")+"|"+addressMap.get("city"));
        loginLog.setLoginTime(DateUtil.formatDate(new Date(),TIME_FORMAT));

        //获取Username
        Object[] args = proceedingJoinPoint.getArgs();
        User user = (User) args[0];
        loginLog.setUsername(user.getUsername());
        //获取操作的类型及具体操作的内容
        String traClassName = proceedingJoinPoint.getTarget().getClass().getName();
        String tarMehtodName = proceedingJoinPoint.getSignature().getName();
        Class tarClass = Class.forName(traClassName);
        Method[] methods = tarClass.getMethods();
        String operationType = "";
        String operationName = "";
        for (Method method : methods){
            String methodName = method.getName();
            if (tarMehtodName.equals(methodName)){
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == args.length){
                    operationType = method.getAnnotation(LoginAnnotation.class).opeationType();
                    operationName = method.getAnnotation(LoginAnnotation.class).opeationName();
                }
            }
        }
        loginLog.setOperationName(operationName);
        loginLog.setOperationType(operationType);
        projectService.addLoginLog(loginLog);
        return result;
    }
}
