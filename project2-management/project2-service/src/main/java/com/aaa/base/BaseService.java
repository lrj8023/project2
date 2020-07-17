package com.aaa.base;

import com.aaa.mapper.UserMapper;
import com.aaa.model.User;
import com.aaa.utils.BaseUtils;
import com.aaa.utils.Map2BeanUtils;
import com.aaa.utils.SpringContextUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.OrderStatic.*;

/**
 * @ClassName BaseService
 * @Description TODO 通用service
 * @Author jyz
 * @date 2020/7/8 16:25
 **/
public abstract class BaseService<T> {
    //全局变量缓存子类的泛型类型
    private Class<T> cache = null;

    @Autowired
    private Mapper<T> mapper;

    @Autowired
    private UserMapper userMapper;

    protected Mapper getMapper(){
        return mapper;
    }
    /**
     * @Author jyz
     * @Description //TODO 新增数据
     * @Date 14:55 2020/7/9
     * @Param [t]
     * @return java.lang.Integer
     **/
    public Integer add(T t){
        return mapper.insert(t);
    }
    /**
     * @Author jyz
     * @Description //TODO 根据主键进行删除
     * @Date 14:56 2020/7/9
     * @Param [t]
     * @return java.lang.Integer
     **/
    public Integer delete(T t){
        return mapper.deleteByPrimaryKey(t);
    }

    /**
     * @Author jyz
     * @Description //TODO 根据主键进行批量删除
     * @Date 15:02 2020/7/9
     * @Param [ids]
     * @return java.lang.Integer
     **/
    public Integer deleteByIds(List<Integer> ids){
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id" , ids)).build();
        return mapper.deleteByExample(example);
    }

    /**
     * @Author jyz
     * @Description //TODO 更新操作
     * @Date 15:04 2020/7/9
     * @Param [t]
     * @return java.lang.Integer
     **/
    public Integer update(T t){
        return mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * @Author jyz
     * @Description //TODO 批量更新
     * @Date 15:09 2020/7/9
     * @Param [t, ids]
     * @return java.lang.Integer
     **/
    public Integer batchUpdate(T t , Integer[] ids){
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return mapper.updateByExample(t , example);
    }

    /**
     * @Author jyz
     * @Description //TODO 查询一条数据
     * @Date 15:10 2020/7/9
     * @Param [t]
     * @return T
     **/
    public T selectOne(T t){
        return mapper.selectOne(t);
    }

    /**
     * @Author jyz
     * @Description //TODO 查询一条数据
     * @Date 15:22 2020/7/9
     * @Param [where, orderByField, fileds]
     * @return T
     **/
    public T selectOneByFiled(Sqls where, String orderByField, String... fileds){
        return (T) selectByFileds(null,null,where, orderByField, null, fileds).get(0);
    }

    /**
     * @Author jyz
     * @Description //TODO 通过条件查询一个列表
     * @Date 15:23 2020/7/9
     * @Param [where, orderByField, fileds]
     * @return java.util.List<T>
     **/
    public List<T> selectListByFiled(Sqls where, String orderByField, String... fileds){
        return selectByFileds(null, null, where, orderByField, null, fileds);
    }

    /**
     * @Author jyz
     * @Description //TODO 实现查询的分页
     * @Date 15:41 2020/7/9
     * @Param [pageNo, pageSize, where, orderFiled, fileds]
     * @return com.github.pagehelper.PageInfo<T>
     **/
    public PageInfo<T> selectListByPageAndFiled(
             Integer pageNo, Integer pageSize, Sqls where, String orderFiled, String... fileds) {
        return new PageInfo<T>(selectByFileds(pageNo, pageSize, where, orderFiled, null, fileds));
    }

    /**
     * @Author jyz
     * @Description //TODO 查询集合，条件查询
     * @Date 15:42 2020/7/9
     * @Param [t]
     * @return java.util.List<T>
     **/
    public List<T> selectList(T t) {
        return mapper.select(t);
    }

    /**
     * @Author jyz
     * @Description //TODO 查询集合，分页查询
     * @Date 15:43 2020/7/9
     * @Param [t, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo<T>
     **/
    public PageInfo<T> selectListByPage(T t, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<T> select = mapper.select(t);
        PageInfo<T> pageInfo = new PageInfo<T>(select);
        return pageInfo;
    }

    /**
     * @Author jyz
     * @Description //TODO map转换实体类型
     * @Date 15:44 2020/7/9
     * @Param [map]
     * @return T
     **/
    public T newInstance(Map map) {
        return (T) Map2BeanUtils.map2Bean(map, getTypeArguement());
    }

    /**
     * @Author jyz
     * @Description //TODO 实现查询通用
     * @Date 15:17 2020/7/9
     * @Param [pageNo, pageSize, where, orderByFiled, orderWord, fileds]
     * @return java.util.List<T>
     **/
    private List<T> selectByFileds(Integer pageNo, Integer pageSize, Sqls where, String orderByFiled, String orderWord, String... fileds) {
        Example.Builder builder = null;
        if(null == fileds || fileds.length == 0) {
            // 查询所有数据
            builder = Example.builder(getTypeArguement());
        } else {
            // 说明需要进行条件查询
            builder = Example.builder(getTypeArguement()).select(fileds);
        }
        if(where != null) {
            // 说明有用户自定义的where语句条件
            builder = builder.where(where);
        }
        if(orderByFiled != null) {
            // 说明我需要对某个字段进行排序
            if(DESC.equals(orderWord.toUpperCase())) {
                // 说明需要倒序
                builder = builder.orderByDesc(orderByFiled);
            } else if(ASC.equals(orderWord.toUpperCase())) {
                builder = builder.orderByAsc(orderByFiled);
            } else {
                builder = builder.orderByDesc(orderByFiled);
            }
        }
        Example example = builder.build();
        // 实现分页
        if(pageNo != null & pageSize != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        return getMapper().selectByExample(example);
    }
    /**
     * @Author jyz
     * @Description //TODO 获取子类泛型类型
     * @Date 15:03 2020/7/9
     * @Param []
     * @return java.lang.Class<T>
     **/
    public  Class<T> getTypeArguement(){
        if (null == cache){
            cache = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return cache;
    }

    /**
     * @Author jyz
     * @Description //TODO 获取spring容器/获取spring的上下文
     * @Date 15:39 2020/7/9
     * @Param []
     * @return org.springframework.context.ApplicationContext
     **/
    public ApplicationContext getApplicationContext() {
        return SpringContextUtils.getApplicationContext();
    }
    /**
     * @Author jyz
     * @Description //TODO 分页多表查询
     * @Date 16:30 2020/7/16
     * @Param [map]
     * @return com.github.pagehelper.PageInfo<java.util.HashMap>
     **/
    public PageInfo<User> selectUserPageInfo(HashMap map){
        PageHelper.startPage(BaseUtils.transToInt(map.get("pageNo")),BaseUtils.transToInt(map.get("pageSize")));
        List<User> list = userMapper.selectUserAll(map);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        if (null != pageInfo && !"".equals(pageInfo)){
            return pageInfo;
        }
        return null;
    }


}
