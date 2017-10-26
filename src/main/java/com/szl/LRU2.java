package com.szl;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zsc on 2017/8/27.
 * 使用LinkedHashMap特性实现LRU
 */
public class LRU2 {
    public static void main(String args[]) {
        LRUCache<Integer, Integer> lruCache = new LRUCache<Integer, Integer>(3);
        int queue[] = {1, 2, 3, 4, 2, 1, 5, 6, 2, 1, 2, 3, 7, 6, 3, 2, 1, 2, 3, 6};
        for (int i = 0; i < queue.length; i++) {
            lruCache.put(queue[i], queue[i]);
        }
        System.out.println(lruCache.getCount());

    }
}

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int cacheSize;
    private int count;

    public LRUCache(int cacheSize) {
        super(16, 0.75f, true);
        this.cacheSize = cacheSize;
        this.count = 0;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        count++;
        return size() >= cacheSize;
    }

    public int getCount() {
        return count;
    }
}
