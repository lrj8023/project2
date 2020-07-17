package com.aaa.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import java.io.Serializable;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/15 8:48
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BaseModel implements Serializable {
    @Id
    @NonNull
    private Long id;

    @Column(name = "create_time")
    @Max(value = 100, message = "时间长度最长不能超过100")
    private String createTime;

    @Column(name = "modify_time")
    @Max(value = 100, message = "时间长度最长不能超过100")
    private String modifyTime;
}
