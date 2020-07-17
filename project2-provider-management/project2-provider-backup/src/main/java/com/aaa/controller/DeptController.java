package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.model.Dept;
import com.aaa.model.User;
import com.aaa.service.DeptService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName DeptController
 * @Description TODO
 * @Author jyz
 * @date 2020/7/16 11:25
 **/
@RestController
public class DeptController extends CommonController<Dept> {
    @Autowired
    private DeptService deptService;
    @Override
    public BaseService<Dept> getBaseService(){
        return deptService;
    }
    /**
     * @Author jyz
     * @Description //TODO 新增部门
     * @Date 11:43 2020/7/16
     * @Param [dept]
     * @return java.lang.Integer
     **/
    @PostMapping("/addDept")
    public Integer add(@RequestBody Dept dept){
        return getBaseService().add(dept);
    }

    /**
     * @Author jyz
     * @Description //TODO 删除部门
     * @Date 11:46 2020/7/16
     * @Param [dept]
     * @return java.lang.Integer
     **/
    @PostMapping("/deleteDept")
    public Integer delete(@RequestBody Dept dept){
        return getBaseService().delete(dept);
    }
    /**
     * @Author jyz
     * @Description //TODO 更改部门信息
     * @Date 11:47 2020/7/16
     * @Param [dept]
     * @return java.lang.Integer
     **/
    @PostMapping("/updateDept")
    public Integer update(@RequestBody Dept dept){
        return getBaseService().update(dept);
    }
    /**
     * @Author jyz
     * @Description //TODO 分页查询部门信息
     * @Date 11:51 2020/7/16
     * @Param [dept, pageInfo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.model.Dept>
     **/
    @PostMapping("/selectListPageInfoDept")
    public PageInfo<Dept> selectListPageInfo(@RequestBody Dept dept, @RequestParam("pageInfo") Integer pageInfo, @RequestParam("pageSize") Integer pageSize){
        return getBaseService().selectListByPage(dept,pageInfo,pageSize);
    }
    /**
     * @Author jyz
     * @Description //TODO 查询一条部门信息
     * @Date 11:52 2020/7/16
     * @Param [dept]
     * @return com.aaa.model.Dept
     **/
    @PostMapping("/selectOneDept")
    public Dept selectOne(@RequestBody Dept dept){
        return getBaseService().selectOne(dept);
    }
}
