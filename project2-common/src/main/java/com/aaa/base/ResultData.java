package com.aaa.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName ResultData
 * @Description TODO
 * @Author jyz
 * @date 2020/7/8 15:30
 **/
@Data
@Accessors(chain = true)
public class ResultData<T> implements Serializable {
    private  String code;
    private String msg;
    private  String detail;
    private T data;
}
