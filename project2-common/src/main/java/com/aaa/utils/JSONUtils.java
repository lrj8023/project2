package com.aaa.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @ClassName JSONUtils
 * @Description TODO
 * @Author jyz
 * @date 2020/7/10 14:58
 **/
public class JSONUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * @Author jyz
     * @Description //TODO 把对象转换为json字符串
     * @Date 14:59 2020/7/10
     * @Param object
     * @return java.lang.String
     **/
    public static String toJsonString(Object object){
        try {
            String jsonString = OBJECT_MAPPER.writeValueAsString(object);
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author jyz
     * @Description //TODO 把json转换为指定的对象
     * @Date 15:02 2020/7/10
     * @Param [jsonData, beanType]
     * @return T
     **/
    public static <T> T toObject(String jsonData,Class<T> beanType) {
        try {
            T t = OBJECT_MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author jyz
     * @Description //TODO 把Json转换为指定List集合
     * @Date 15:05 2020/7/10
     * @Param [jsonData, beanType]
     * @return java.util.List<T>
     **/
    public static <T> List<T> toList(String jsonData,Class<T> beanType) {
        // 1.为List集合添加一个指定的泛型
        // List  User.class ---> 通过constructParametricType方法把List和User合并，也就是说为List指定一个User对象的泛型(List<User>)
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = OBJECT_MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
