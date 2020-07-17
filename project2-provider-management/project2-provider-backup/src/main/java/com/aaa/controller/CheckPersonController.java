package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.model.CheckPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/16 18:40
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/checkPerson")
public class CheckPersonController extends CommonController {















    @Override
    public BaseService<CheckPerson> getBaseService() {
        return null;
    }
}
