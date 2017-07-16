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
    public static void main(String args[]) throws IOException{
        Jedis jedis = new Jedis("localhost", 6379);

//        Set<String> set1 = jedis.smembers("visitedUrl");
//        HashSet<String> hashSet = new HashSet<>(set1);
//        saveObj(hashSet,"E:\\set1");
//        Set<String> set2 = jedis.smembers("410Url");
//        HashSet<String> hashSet2 = new HashSet<>(set2);
//        saveObj(hashSet2,"E:\\set2");


//        HashSet<String> hashSet3 = (HashSet<String>)getObj("E:\\set1");
//        for (String str : hashSet3) {
//            jedis.sadd("visitedUrl", str);
//        }
//        HashSet<String> hashSet4 = (HashSet<String>)getObj("E:\\set2");
//        for (String str : hashSet4) {
//            jedis.sadd("410Url", str);
//        }
        jedis.save();

    }

    private static void saveObj(Object object,String path) throws IOException{
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
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

    private static Object getObj(String path) {
        Object object = null;
        try {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(new File(path)));
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
