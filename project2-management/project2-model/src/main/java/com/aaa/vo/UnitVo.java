package com.aaa.vo;

import com.aaa.model.MappingUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/17 10:30
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UnitVo implements Serializable {
    private MappingUnit mappingUnit;

    private Integer pageNo;

    private Integer pageSize;
}
