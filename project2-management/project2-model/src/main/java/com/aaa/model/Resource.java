package com.aaa.model;

import com.aaa.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/16 15:21
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_resource")
public class Resource extends BaseModel {
    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源大小
     */
    private Long size;

    /**
     * 资源路径
     */
    private String path;

    /**
     * 资源文件类型
     */
    private String type;

    /**
     * 资源后缀名
     */
    @Column(name = "ext_name")
    private String extName;

    /**
     * 文件业务类型
     */
    @Column(name = "ref_biz_type")
    private String refBizType;

    /**
     * 文件关联编号
     */
    @Column(name = "ref_biz_id")
    private Long refBizId;


    /**
     * 备注
     */
    private String memo;

}
