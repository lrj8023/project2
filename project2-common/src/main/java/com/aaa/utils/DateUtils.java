package com.aaa.utils;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/7/10 11:56
 * @Description
 **/
public class DateUtils {

    private DateUtils() {

    }
    public static final String DATE_TYPE = "yyyy-MM-dd";
    public static final String TIME_TYPE = "yyyy-MM-dd HH:mm:ss";
    /**
     * @Author jyz
     * @Description //TODO 按照日期格式进行转换
     * @Date 15:54 2020/7/16
     * @Param [date]
     * @return java.lang.String
     **/
    public static String formatDate(Object date){
        if (null == date){
            return null;
        }else{
            return formatDate(date,DATE_TYPE);
        }
    }
    /**
     * @Author jyz
     * @Description //TODO 按照指定格式日期进行转换
     * @Date 15:59 2020/7/16
     * @Param [date, formatType]
     * @return java.lang.String
     **/
    public static final String formatDate(Object date,String formatType){
        if (null == date){
            return null;
        }else{
            if (StringUtils.isNotEmpty(formatType)){
                //说明需要根据客户所定义的格式来转换
                SimpleDateFormat format = new SimpleDateFormat(formatType);
                return format.format(date);
            }else{
                //说明没有指定格式
                return formatDate(date);
            }
        }
    }
    /**
     * @Author jyz
     * @Description //TODO 将时间转换为字符串
     * @Date 15:58 2020/7/16
     * @Param [millisecond]
     * @return java.lang.String
     **/
    public static String formatDateAgo(long millisecond){
        StringBuilder stringBuilder = new StringBuilder();
        //判断传进来的时间大小
        if (1000 > millisecond){
            stringBuilder.append(millisecond).append("秒");
        }else{
            //说明传进来的秒数大于1000
            Integer ss = 1000;
            Integer mi = ss * 60;
            Integer hh = mi * 60;
            Integer dd = hh * 24;

            Long day = millisecond / dd;
            Long hour = (millisecond - day * dd) / hh;
            Long minute = (millisecond - day * dd - hour * hh) / mi;
            Long second = (millisecond - day * dd - hour * hh - minute * mi) / ss;

            if (day > 365){
                return formatDate(new Date(millisecond),"yyyy年MM月dd日 HH时mm分ss秒");
            }
            if (day > 0){
                stringBuilder.append(day).append("日");
            }
            if (hour > 0){
                stringBuilder.append(hour).append("时");
            }
            if (minute > 0){
                stringBuilder.append(minute).append("分");
            }
            if (second > 0){
                stringBuilder.append(second).append("秒");
            }
        }
        return stringBuilder.toString();
    }
    /**
     * @Author jyz
     * @Description //TODO 获取当前的日期
     * @Date 15:59 2020/7/16
     * @Param []
     * @return java.lang.String
     **/
    public static final String getCurrentDate(){
        return formatDate(new Date());
    }
    /**
     * @Author jyz
     * @Description //TODO 获取当前年份
     * @Date 15:59 2020/7/16
     * @Param []
     * @return java.lang.Integer
     **/
    public static Integer getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }


}
