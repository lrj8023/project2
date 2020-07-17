package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.redis.RedisService;
import com.aaa.service.LoginService;
import com.aaa.service.UserService;
import com.aaa.utils.ExcelUtils;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import jdk.internal.dynalink.linker.LinkerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.status.LoginStatus.*;
import static com.aaa.status.OperationStatus.*;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author jyz
 * @date 2020/7/15 19:00
 **/
@RestController
@Slf4j
public class UserController extends CommonController<User> {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;
    @Override
    public BaseService<User> getBaseService() {
        return userService;
    }

    /**
     * @Author jyz
     * @Description //TODO 新增用户,初始密码为pwd123456
     * @Date 9:15 2020/7/16
     * @Param [user]
     * @return java.lang.Integer
     **/
    @PostMapping("/addUser")
    public ResultData insert(@RequestBody User user) {
        TokenVo tokenVo = userService.insert(user);
        if (tokenVo.getIfSuccess()){
            return super.operationSuccess(tokenVo.getToken());
        }else if (tokenVo.getType() == 2){
            return super.operationFailed(USER_EXIST.getMsg());
        }else {
        return super.operationFailed(SYSTEM_EXCEPTION.getMsg());
        }
    }

    /**
     * @Author jyz
     * @Description //TODO 逻辑删除
     * @Date 9:52 2020/7/16
     * @Param [user]
     * @return java.lang.Integer
     **/
    @PostMapping("/deleteUser")
    public Integer delete(@RequestBody User user){
        if (user.getStatus().equals("0")){
            user.setStatus("1");
        }else {
            user.setStatus("0");
        }
       return getBaseService().update(user);
    }

    /**
     * @Author jyz
     * @Description //TODO 批量删除
     * @Date 16:41 2020/7/16
     * @Param [ids]
     * @return com.aaa.base.ResultData
     **/
    @DeleteMapping("/delUser")
    public ResultData delUser(@RequestBody List<Long> ids){
        Map<String, Object> resultMap = userService.delUser(ids);
        if (SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.operationSuccess();
        }else{
            return super.operationFailed();
        }
    }

    /**
     * @Author jyz
     * @Description //TODO 更新操作
     * @Date 10:23 2020/7/16
     * @Param [user]
     * @return java.lang.Integer
     **/
    @PostMapping("/updateUser")
    public ResultData updateUser(@RequestBody User user){
        Map<String, Object> resultMap = userService.updateUser(user);
        if (SUCCESS.getCode().equals(resultMap.get("code"))){
            return super.operationSuccess();
        }else{
            return super.operationFailed();
        }
    }

    /**
     * @Author jyz
     * @Description //TODO 带条件查询用户信息
     * @Date 16:48 2020/7/16
     * @Param [map]
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/selectUserAll")
    public ResultData selectUserAll(@RequestBody HashMap map){
        Map<String, Object> userAll = userService.selectUserAll(map,redisService);
        if (SUCCESS.getCode().equals(userAll.get("code"))){
            return super.operationSuccess(userAll);
        }else if (FAILED.getCode().equals(userAll.get("code"))){
            return super.operationFailed();
        }else{
            return super.operationFailed("数据不存在");
        }
    }
    /**
     * @Author jyz
     * @Description //TODO 分页查询
     * @Date 10:31 2020/7/16
     * @Param [user, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<com.aaa.model.User>
     **/
    @PostMapping("/selectUser")
    public PageInfo<User> selectUser(@RequestBody User user, @RequestParam("pageNo") Integer pageNo,@RequestParam("pageNo") Integer pageSize){
        return getBaseService().selectListByPage(user,pageNo,pageSize);
    }

    /**
     * @Author jyz
     * @Description //TODO 导出用户信息
     * @Date 17:02 2020/7/16
     * @Param [response]
     * @return void
     **/
    @GetMapping("/exportExcle")
    public void exportExcle(HttpServletResponse response){
        Map<String, Object> map = userService.selectAll();
        if (SUCCESS.getCode().equals(map.get("code"))){
            List<User> users = (List<User>) map.get("data");
            //不为空，开始进行导出
            if (null != users && !users.isEmpty()){
                //list存放表格数据
                List<List<String>> excelData = new ArrayList<List<String>>();
                if(null != users){
                    //表格头
                    List<String> headList = new ArrayList<String>();
                    headList.add("用户ID");
                    headList.add("用户名");
                    headList.add("部门ID");
                    headList.add("邮箱");
                    headList.add("联系电话");
                    headList.add("状态");
                    headList.add("创建时间");
                    headList.add("修改时间");
                    headList.add("最近访问时间");
                    headList.add("性别");
                    headList.add("描述");
                    headList.add("用户类型");
                    //把表头放入表格数据中
                    excelData.add(headList);
                    //遍历表格数据并放入excelData
                    for (User user : users) {
                        List<String> list = new ArrayList<String>();
                        list.add(String.valueOf(user.getId()));
                        list.add(String.valueOf(user.getUsername()));
                        list.add(String.valueOf(user.getDeptId()));
                        list.add(String.valueOf(user.getEmail()));
                        list.add(String.valueOf(user.getMobile()));
                        if ("0".equals(user.getStatus())){
                            list.add("锁定");
                        }else if ("1".equals(user.getStatus())){
                            list.add("有效");
                        }
                        list.add(String.valueOf(user.getCreateTime()));
                        list.add(String.valueOf(user.getModifyTime()));
                        list.add(String.valueOf(user.getLastLoginTime()));
                        if ("0".equals(user.getSsex())){
                            list.add("男");
                        }else if ("1".equals(user.getSsex())){
                            list.add("女");
                        }else if ("2".equals(user.getSsex())){
                            list.add("保密");
                        }
                        list.add(String.valueOf(user.getDescription()));
                        if ("0".equals(user.getType())){
                            list.add("单位用户");
                        }else if ("1".equals(user.getType())){
                            list.add("审核用户");
                        }else if ("2".equals(user.getType())){
                            list.add("管理员");
                        }
                        //把数据放入excelData
                        excelData.add(list);
                    }
                }
                String sheetName = "用户信息";
                String fileName = "用户信息表";
                try {
                    ExcelUtils.exportExcel(response, excelData, sheetName, fileName, 12);
                } catch (IOException e) {
                    log.error("用户信息数据导出失败！");
                }

            }
        }else{
            log.error("用户管理中的导出数据出错！");
        }
    }

    /**
     * @Author jyz
     * @Description //TODO 根据主键进行查询
     * @Date 10:35 2020/7/16
     * @Param [user]
     * @return com.aaa.model.User
     **/
    @PostMapping("/selectOneUser")
    public User selectOne(@RequestBody User user){
        return getBaseService().selectOne(user);
    }


}
