package com.aaa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName LoginAnnotation
 * @Description TODO
 * @Author jyz
 * @date 2020/7/15 14:42
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginAnnotation {
    /**
     * @Author jyz
     * @Description //TODO 要执行的操作类型
     * @Date 14:46 2020/7/15
     * @Param []
     * @return java.lang.String
     **/
    String opeationType();

    /**
     * @Author jyz
     * @Description //TODO 所要执行的具体操作内容
     * @Date 14:46 2020/7/15
     * @Param []
     * @return java.lang.String
     **/
    String opeationName();
}
