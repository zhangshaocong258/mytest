package com.szl;

import redis.clients.jedis.Jedis;

/**
 * Created by zsc on 2016/12/1.
 */
public class JedisTest {
    public static void main(String args[]) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.sadd("user","liuling2");
        jedis.sadd("user","xinxin2");
        jedis.sadd("user","ling2");
        jedis.sadd("user","zhangxinxin2");
        jedis.sadd("user","who2");
        jedis.save();

        System.out.println(jedis.smembers("user"));
        System.out.println(jedis.sismember("user", "xinxin"));//判断 who 是否是user集合的元素

    }
}
