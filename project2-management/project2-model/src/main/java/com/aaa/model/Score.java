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
 * @Date: Created in 2020/7/16 15:10
 * @Description:
 */
@Table(name = "t_score")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Score extends BaseModel {

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 增加的分值
     */
    @Column(name = "score_plus")
    private Integer scorePlus;

    /**
     * 减少的分值
     */
    @Column(name = "score_subtract")
    private Integer scoreSubtract;

    /**
     * 当前分值
     */
    private Integer score;

    /**
     * 关联单位编号
     */
    @Column(name = "unit_id")
    private Long unitId;

    /**
     * 增加/减少分值的原因
     */
    private String reason;
}
