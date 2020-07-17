package com.aaa.utils;

import java.util.Random;

/**
 * @ClassName FileNameUtils
 * @Description TODO
 * @Author jyz
 * @date 2020/7/10 15:16
 **/
public class FileNameUtils {
    private FileNameUtils(){}
    /**
     *@author: Cancer:栗仁杰
     *@description:  生成随机文件名的方法
     *@param: []
     *@date: 11:09 2020/7/17
     *@return:
     *@throws:
     **/
    public static String getFileName() {
        long currentTimeMillis = System.currentTimeMillis();
        Random random = new Random();
        int number = random.nextInt(999);
        return currentTimeMillis + String.format("%03d",number);
    }
    public static Long getFileNameLong() {
        long currentTimeMillis = System.currentTimeMillis();
        Random random = new Random();
        int number = random.nextInt(999);
        return currentTimeMillis + String.format("%03d",number);
    }
}
