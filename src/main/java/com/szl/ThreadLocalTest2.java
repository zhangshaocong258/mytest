package com.szl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zsc on 2017/2/22.
 */
public class ThreadLocalTest2 {

    public static void main(String[] args) {
        MessageStore messageStore = new MessageStore();
        TestRunnable testRunnable = new TestRunnable(messageStore);
        Thread thread1 = new Thread(testRunnable);
        Thread thread2 = new Thread(testRunnable);
        thread1.start();
        thread2.start();
        messageStore.printMap();
    }
}

class MessageStore{

    private ThreadLocal<Map<String, List<String>>> threadLocal = ThreadLocal.withInitial(() -> new HashMap<>());

    public void put(String key,String value) {
        Map<String, List<String>> map = threadLocal.get();
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            map.put(key, new ArrayList<String>() {{
                add(value);
            }});
        }
    }

    public void printMap() {
        for (Map.Entry<String, List<String>> entry : threadLocal.get().entrySet()) {
            System.out.println("key "+ entry.getKey() + "  value " + entry.getValue());
        }
    }

}

class TestRunnable implements Runnable {

    private MessageStore messageStore;

    public TestRunnable(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            messageStore.put(Thread.currentThread().getName(), i + "");
        }
        messageStore.printMap();
    }
}
