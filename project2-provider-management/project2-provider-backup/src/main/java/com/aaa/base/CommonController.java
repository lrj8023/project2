package com.aaa.base;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.util.Sqls;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.aaa.status.OperationStatus.*;

/**
 * @ClassName CommonController
 * @Description TODO
 * @Author jyz
 * @date 2020/7/8 16:32
 **/
public abstract class CommonController<T> extends BaseController {
    /**
     * @Author jyz
     * @Description //TODO 钩子函数，在某些函数之前进行某些操作
     * @Date 16:22 2020/7/9
     * @Param [map]
     * @return void
     **/
    protected void beforeAdd(Map map) {
        // TODO AddMethod Before to do something
    }

    /**
     * @Author jyz
     * @Description //TODO 钩子函数，再新增之后去进行某些操作
     * @Date 16:20 2020/7/9
     * @Param [map]
     * @return void
     **/
    protected void afterAdd(Map map) {
        // TODO AddMethod After to do something
    }

    public abstract BaseService<T> getBaseService();

    /**
     * @Author jyz
     * @Description //TODO 通用的新增方法
     * @Date 16:20 2020/7/9
     * @Param [map]
     * @return com.aaa.base.ResultData
     **/
    public ResultData add(@RequestBody Map map) {
        // 因为根据咱们的封装规则，在service中是需要传递泛型的，就意味着service需要接收固定的实体类
        // 但是controller是一个Map类型
        beforeAdd(map);
        // 1.Map转实体类
        T instance = getBaseService().newInstance(map);
        // 2.通用service
        Integer addResult = getBaseService().add(instance);
        if(addResult > 0) {
            afterAdd(map);
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @Author jyz
     * @Description //TODO 删除操作
     * @Date 16:17 2020/7/9
     * @Param [map]
     * @return com.aaa.base.ResultData
     **/
    public ResultData delete(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        Integer deleteResult = getBaseService().delete(instance);
        if(deleteResult > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @Author jyz
     * @Description //TODO 批量删除
     * @Date 16:17 2020/7/9
     * @Param [ids]
     * @return com.aaa.base.ResultData
     **/
    public ResultData batchDelete(@RequestParam("ids[]") Integer[] ids) {
        Integer deleteResult = getBaseService().deleteByIds(Arrays.asList(ids));
        if(deleteResult > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @Author jyz
     * @Description //TODO 更新操作
     * @Date 17:13 2020/7/9
     * @Param [map]
     * @return com.aaa.base.ResultData
     **/
    public ResultData update(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        Integer updateResult = getBaseService().update(instance);
        if (updateResult > 0){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @Author jyz
     * @Description //TODO 批量更改
     * @Date 18:01 2020/7/9
     * @Param [map, ids]
     * @return com.aaa.base.ResultData
     **/
    public  ResultData batchUpdate(@RequestBody Map map, @RequestParam("ids[]") Integer[] ids){
        T instance = getBaseService().newInstance(map);
        Integer updateResult = getBaseService().batchUpdate(instance,ids);
        if (updateResult > 0){
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @Author jyz
     * @Description //TODO 查询一条数据
     * @Date 18:16 2020/7/9
     * @Param [map]
     * @return com.aaa.base.ResultData
     **/
    public ResultData selectOne(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        T selectResult = getBaseService().selectOne(instance);
        if (null == selectResult || "".equals(selectResult)){
            return super.operationFailed(REQUEST_IS_NULL);
        }
        return super.operationSuccess();
    }

    /**
     * @Author jyz
     * @Description //TODO 通过多条件查询一条数据
     * @Date 18:25 2020/7/9
     * @Param [where, orderByField, fileds]
     * @return com.aaa.base.ResultData
     **/
    public ResultData selectOneByFiled(@RequestBody Sqls where,@RequestBody String orderByField,@RequestBody String... fileds){
        T selectResult = getBaseService().selectOneByFiled(where, orderByField, fileds);
        if (null == selectResult || "".equals(selectResult)){
            return super.operationFailed(REQUEST_IS_NULL);
        }
        return super.operationSuccess();
    }

    /**
     * @Author jyz
     * @Description //TODO 通过条件查询一个列表
     * @Date 18:28 2020/7/9
     * @Param [where, orderByField, fileds]
     * @return com.aaa.base.ResultData
     **/
    public ResultData selectListByFiled(@RequestBody Sqls where,@RequestBody String orderByField,@RequestBody String... fileds){
        List<T> selectResult =getBaseService().selectListByFiled(where, orderByField, fileds);
        if (null == selectResult || "".equals(selectResult)){
            return super.operationFailed(REQUEST_IS_NULL);
        }
        return super.operationSuccess();
    }

    /**
     * @Author jyz
     * @Description //TODO 查询并分页
     * @Date 18:37 2020/7/9
     * @Param [pageNo, pageSize, where, orderFiled, fileds]
     * @return com.aaa.base.ResultData
     **/
    public ResultData selectListByPageAndFiled(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize,@RequestBody Sqls where,@RequestBody String orderFiled,@RequestBody String... fileds){
        PageInfo<T> selectResult = getBaseService().selectListByPageAndFiled(pageNo, pageSize, where, orderFiled, fileds);
        if (null == selectResult || "".equals(selectResult)){
            return super.operationFailed(REQUEST_IS_NULL);
        }
        return super.operationSuccess();
    }

    /**
     * @Author jyz
     * @Description //TODO 查询集合，条件查询
     * @Date 18:46 2020/7/9
     * @Param [map]
     * @return com.aaa.base.ResultData
     **/
    public ResultData selectList(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        List<T> selectResult = getBaseService().selectList(instance);
        if (null == selectResult || "".equals(selectResult)){
            return super.operationFailed(REQUEST_IS_NULL);
        }
        return super.operationSuccess();
    }

    /**
     * @Author jyz
     * @Description //TODO 查询集合，分页查询
     * @Date 18:51 2020/7/9
     * @Param [map, pageNo, pageSize]
     * @return com.aaa.base.ResultData
     **/
    public ResultData selectListByPage(@RequestBody Map map,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        T instance = getBaseService().newInstance(map);
        PageInfo<T> selectResult = getBaseService().selectListByPage(instance,pageNo,pageSize);
        if (null == selectResult || "".equals(selectResult)){
            return super.operationFailed(REQUEST_IS_NULL);
        }
        return super.operationSuccess();
    }

    /**
     * @Author jyz
     * @Description //TODO 从本地当前线程中获取request对象
     * @Date 15:49 2020/7/10
     * @Param []
     * @return javax.servlet.http.HttpServletRequest
     **/
    public HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if (requestAttributes instanceof ServletRequestAttributes) {
            servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * @Author jyz
     * @Description //TODO 获取当前客户端session对象，如果没有则创建一个新的session
     * @Date 15:50 2020/7/10
     * @Param []
     * @return javax.servlet.http.HttpSession
     **/
    public HttpSession getSession(){
        return getServletRequest().getSession();
    }

    /**
     * @Author jyz
     * @Description //TODO 获取当前客户端session对象 如果没有则直接返回null
     * @Date 15:51 2020/7/10
     * @Param []
     * @return javax.servlet.http.HttpSession
     **/
    public HttpSession getExistSession() {
        return getServletRequest().getSession(false);
    }
}
