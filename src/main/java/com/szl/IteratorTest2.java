package com.szl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zsc on 2017/9/12.
 * 两个线程都用iterator，还是会报错
 */
public class IteratorTest2 {
    public static void main(String[] args) {
        HashMap<String, String> hashmap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            hashmap.put(i + "", i + "");
        }
        new SubThread(hashmap).start();
        new SubThread(hashmap).start();
    }

}

class SubThread extends Thread{
    private HashMap<String,String> hashMap;
    public SubThread(HashMap<String,String> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        for (Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            System.out.println(Thread.currentThread().getName() + "  " + key);
            if (key.equals("5")) {
                iterator.remove();
            }
        }
    }
}
