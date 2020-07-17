package com.aaa.controller;

import com.aaa.model.SpecialPost;
import com.aaa.service.SpecialPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company: AAA软件教育
 * @Author: Cancer:栗仁杰
 * @Date: Created in 2020/7/15 19:43
 * @Description:
 *          测绘管理-单位基本信息-特殊岗位人员
 */
@RestController
public class SpecialPostController {

    @Autowired
    private SpecialPostService specialPostService;


    @PostMapping("/querySpecialPost")
    public List<SpecialPost> selectSpecialPost(@RequestParam("userId") Long userId){
        try {
            List<SpecialPost> specialPosts = specialPostService.selectSpecialPost(userId);
            return specialPosts;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
