package com.aaa.controller;

import com.aaa.annotation.LoginAnnotation;
import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.IProjectService;
import feign.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author jyz
 * @date 2020/7/16 9:39
 **/
@RestController
@Api(value = "用户管理" ,tags = "用户管理接口")
public class UserController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author jyz
     * @Description //TODO 新增用户操作
     * @Date 10:07 2020/7/16
     * @Param [user]
     * @return java.lang.Integer
     **/
    @PostMapping("/add")
    @ApiOperation(value = "添加用户" , notes = "用户管理的新增用户")
    public ResultData insert(User user){
        return iProjectService.insert(user);
    }

    /**
     * @Author jyz
     * @Description //TODO 批量删除用户
     * @Date 17:37 2020/7/16
     * @Param [ids]
     * @return com.aaa.base.ResultData
     **/
    @DeleteMapping("/delUser")
    @ApiOperation(value = "删除用户",notes = "管理员管理的删除用户")
    public ResultData delUser(@RequestBody List<Long> ids){
        return iProjectService.delUser(ids);
    }

    /**
     * @Author jyz
     * @Description //TODO 修改用户信息
     * @Date 17:37 2020/7/16
     * @Param [user]
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户信息" , notes = "用户管理的修改用户信息")
    public ResultData updateUser(@RequestBody User user){
        return iProjectService.updateUser(user);
    }

    @GetMapping("/expotrExcle")
    @ApiOperation(value = "导出Excle",notes = "用户管理的导出用户信息")
    public ResponseEntity<byte[]> exportExcle(){
        ResponseEntity<byte[]> result = null;
        Response response = iProjectService.exportExcle();
        Response.Body body = response.body();
        try (InputStream inputStream = body.asInputStream()) {
            // feign文件下载
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[10240];
            while (true) {
                int len = inputStream.read(buf);
                if (len < 0) {
                    break;
                }
                bos.write(buf, 0, len);
            }
            byte[] b = bos.toByteArray();
            HttpHeaders heads = new HttpHeaders();
            heads.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=user.xls");
            heads.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel;charset=utf-8");
            heads.add(HttpHeaders.CONNECTION, "close");
            result = new ResponseEntity<>(b, heads, HttpStatus.OK);
        } catch (IOException e) {

        }
        return result;
    }
}
