package com.nextyu.study.spring.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nextyu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class MyTest {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    // private ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();


    // 下面直接报错
   /* @Autowired
    @Qualifier("redisTemplate")
    private ValueOperations<String, String> valueOperations;*/

    // 对应Redis 里面不同类型的操作
    // 使用@Resource注解，正确执行，不用管报红叉
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;// Redis string (or value) operations

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOperations;// Redis list operations

    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOperations;// Redis set operations

    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOperations;// Redis zset (or sorted set) operations

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOperations;// Redis hash operations

    @Test
    public void testSet() {
        // 方法1
        //redisTemplate.boundValueOps("key1").append("value1");
        // 方法2
        valueOperations.set("key2", "value2");
    }

    @Test
    public void testGet() {
        // 方法1
        //String key1 = redisTemplate.boundValueOps("key1").get();
        // 方法2
        String key2 = valueOperations.get("testKey1");
        System.out.println(key2);
    }

    @Test
    public void testRedisCallback() {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Long dbSize = connection.dbSize();


                System.out.println(dbSize);
                // Can cast to StringRedisConnection if using a StringRedisTemplate
                return null;
            }
        });
    }

    @Test
    public void testListOperations() {
        // lpush
//        listOperations.leftPush()

        // rpush
//        listOperations.rightPush()

        listOperations.leftPush("mylist1", "world");
        listOperations.leftPush("mylist1", "hello");

        listOperations.leftPush("mylist1", "world", "there");

        List<String> mylist1 = listOperations.range("mylist1", 0, 1);
        System.out.println(mylist1);


    }


    @Test
    public void testHashOperations() {

        // hset
//        hashOperations.put("myhash1", "filed1", "value2");

        // hsetnx
//        Boolean absent = hashOperations.putIfAbsent("myhash1", "filed1", "value1");
//        System.out.println(absent);

        // hmset
//        Map<String, String> map = new HashMap<>();
//        map.put("filed2", "value2");
//        hashOperations.putAll("myhash1", map);

        // hget
//        String s = hashOperations.get("myhash1", "filed2");

        // hmget
        List<String> keyList = new ArrayList<>();
        keyList.add("filed1");
        keyList.add("filed2");
        List<String> stringList = hashOperations.multiGet("myhash1", keyList);
        System.out.println(stringList);

        // hincrby
//        hashOperations.increment()

        // hexists
//        hashOperations.hasKey()

        // hlen
//        hashOperations.size()

        // hdel
//        hashOperations.delete();

        // hkeys
//        hashOperations.keys()

        // hvals
//        hashOperations.values()

        // hgetall
//        hashOperations.entries()


    }

}
