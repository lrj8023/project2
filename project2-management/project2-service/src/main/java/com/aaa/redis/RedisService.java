package com.aaa.redis;

import com.aaa.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.aaa.staticproperties.RedisProperties.*;

/**
 * @ClassName RedisService
 * @Description TODO
 * @Author jyz
 * @date 2020/7/10 14:31
 **/
@Service
public class RedisService<T> {
    private RedisSerializer keySerializer = null;
    /**
     * @Author jyz
     * @Description //TODO 初始化redis的key的序列化器
     * @Date 14:48 2020/7/10
     * @Param []
     * @return void
     **/
    @PostConstruct
    public void initRedisSerializer (){
        if (this.keySerializer == null) {
            this.keySerializer = new JdkSerializationRedisSerializer(this.getClass().getClassLoader());
        }
    }

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * @Author jyz
     * @Description //TODO 想redis中存入数据
     * @Date 14:57 2020/7/10
     * @Param [key, value, nxxx, expx, seconds]
     * @return java.lang.String
     **/
    public String set(String key,T value, String nxxx, String expx, Integer seconds){
        if (null != seconds && 0 < seconds &&
        EX.equals(expx) || PX.equals(expx) &&
        XX.equals(nxxx) || NX.equals(nxxx)){
            return jedisCluster.set(key, JSONUtils.toJsonString(value),nxxx,expx,seconds);
        }else {
            if (NX.equals(nxxx)){
                return String.valueOf(jedisCluster.setnx(key, JSONUtils.toJsonString(value)));
            }else if(XX.equals(nxxx)) {
                return jedisCluster.set(key, JSONUtils.toJsonString(value));
            }
        }
        return NO;
    }

    /**
     * @Author jyz
     * @Description //TODO 从redis中查询单个数据
     * @Date 15:06 2020/7/10
     * @Param [key]
     * @return T
     **/
    public T getOne(String key){
        return (T) JSONUtils.toObject(jedisCluster.get(key), Object.class);
    }

    /**
     * @Author jyz
     * @Description //TODO 从redis中查询数据(value值是字符串)
     * @Date 15:07 2020/7/10
     * @Param [key]
     * @return java.lang.String
     **/
    public String getString(String key){
        return jedisCluster.get(key);
    }

    /**
     * @Author jyz
     * @Description //TODO 从redis中获取数据集合数据
     * @Date 15:09 2020/7/10
     * @Param [key]
     * @return java.util.List<T>
     **/
    public List<T> getList(String key){
        return (List<T>) JSONUtils.toList(jedisCluster.get(key),Object.class);
    }

    /**
     * @Author jyz
     * @Description //TODO 批量删除
     * @Date 15:11 2020/7/10
     * @Param [keys]
     * @return java.lang.Long
     **/
    public Long delMany(Collection<T> keys){
        if(CollectionUtils.isEmpty(keys)) {
            return 0L;
        } else {
            byte[][] bytes = this.rawkeys(keys);
            return jedisCluster.del(bytes);
        }
    }
    /**
     * @Author jyz
     * @Description //TODO 把Object对象转换为字节数组
     * @Date 15:13 2020/7/10
     * @Param [key]
     * @return byte[]
     **/
    private byte[] rawKey(Object key) {
        Assert.notNull(key, "non null key required");
        return this.keySerializer == null && key instanceof byte[] ?
                (byte[]) key : this.keySerializer.serialize(key);
    }

    private byte[][] rawkeys(Collection<T> keys) {
        byte[][] rks = new byte[keys.size()][];
        int i = 0;
        Object key;
        for(Iterator i9 = keys.iterator(); i9.hasNext(); rks[i++] = this.rawKey(key)) {
            key = i9.next();
        }
        return rks;
    }

}
