package com.aaa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.List;

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
public class RoleVo implements Serializable {

    private List<Long> menuId;

    private Role role;

    private Integer pageNo;

    private Integer pageSize;
}
