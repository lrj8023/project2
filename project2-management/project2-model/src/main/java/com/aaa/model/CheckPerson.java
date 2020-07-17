package com.aaa.model;

import com.aaa.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/16 10:06
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name ="t_check_person")
public class CheckPerson extends BaseModel {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     *
     *姓名
     */
    private String name;
    /**
     * 单位名称
     */
    @Column(name = "unit_name")
    private String unitName;
    /**
     * 职务
     */
    private String duty;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 备注
     */
    private String memo;
}
