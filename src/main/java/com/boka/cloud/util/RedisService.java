package com.boka.cloud.util;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by boka on 15-1-19.
 */
@Component
public class RedisService {

    @Resource(name="redisTemplate")
    private HashOperations<String, String, String> hashOps;

    @Resource(name="redisTemplate")
    private RedisOperations<String, String> redisOps;

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOps;

    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    public String hget(String key, String field, int expire, TimeUnit timeUnit) {
        if(expire > 0) {
            redisOps.expire(key, expire, timeUnit);
        }
        return hashOps.get(key, field);
    }

    public Map<String, String> hgetAll(String key) {
        return hashOps.entries(key);
    }

    public void hset(String key, String field, String val, int expire, TimeUnit timeUnit) {
        hashOps.put(key, field, val);
        if(expire > 0) {
            redisOps.expire(key, expire, timeUnit);
        }
    }

    public void inc(String key, String field, int num) {
        hashOps.increment(key, field, num);
    }


    public void expireAt(String key, Date date) {
        redisOps.expireAt(key, date);
    }

    public boolean hasKey(String key){
        return redisOps.hasKey(key);
    }

    public HashOperations<String, String, String> getHashOps() {
        return hashOps;
    }

    public RedisOperations<String, String> getRedisOps() {
        return redisOps;
    }

    public void setHashOps(HashOperations<String, String, String> hashOps) {
        this.hashOps = hashOps;
    }

    public void setRedisOps(RedisOperations<String, String> redisOps) {
        this.redisOps = redisOps;
    }

    public ValueOperations<String, String> getValueOps() {
        return valueOps;
    }

    public void setValueOps(ValueOperations<String, String> valueOps) {
        this.valueOps = valueOps;
    }

    public ListOperations<String, String> getListOps() {
        return listOps;
    }

    public void setListOps(ListOperations<String, String> listOps) {
        this.listOps = listOps;
    }
}
