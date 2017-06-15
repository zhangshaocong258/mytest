package com.szl;

import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zsc on 2016/12/1.
 */
public class JedisTest {
    public static void main(String args[]) {
        Jedis jedis = new Jedis("localhost", 6379);

//        Set<String> set = jedis.smembers("visitedUrl");
//        HashSet<String> hashSet = new HashSet<>(set);
//        saveObj(hashSet);
//        HashSet<String> hashSet2 = (HashSet<String>)getObj();
//        for (String str : hashSet2) {
//            jedis.sadd("visitedUrl", str);
//        }
        jedis.save();

    }

    private static void saveObj(Object object) throws IOException{
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File("E:\\set")));
            oos.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("write model error");
        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }

    private static Object getObj() {
        Object object = null;
        try {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(new File("E:\\set")));
                object = ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (ois != null) {
                    ois.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
