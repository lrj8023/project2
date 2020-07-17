package com.aaa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName TokenVo
 * @Description TODO
 * @Author jyz
 * @date 2020/7/15 15:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TokenVo implements Serializable {
    private String token;
    private Boolean ifSuccess;
    private Integer type;
}
