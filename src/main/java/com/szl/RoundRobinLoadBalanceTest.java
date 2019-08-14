package com.szl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhangshaocong
 * @date 2019-07-01
 * @desc dubbo 负载均衡
 */
public class RoundRobinLoadBalanceTest {

    private AtomicInteger smallCount = new AtomicInteger(0);
    private AtomicInteger mediumCount = new AtomicInteger(0);
    private AtomicInteger largeCount = new AtomicInteger(0);


    protected static class WeightedRoundRobin {
        private int weight;
        private AtomicLong current = new AtomicLong(0);
        private long lastUpdate;

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
            current.set(0);
        }

        public long increaseCurrent() {
            return current.addAndGet(weight);
        }

        public void sel(int total) {
            current.addAndGet(-1 * total);
        }

        public long getLastUpdate() {
            return lastUpdate;
        }

        public void setLastUpdate(long lastUpdate) {
            this.lastUpdate = lastUpdate;
        }
    }

    private ConcurrentMap<String, ConcurrentMap<String, WeightedRoundRobin>> methodWeightMap =
            new ConcurrentHashMap<String, ConcurrentMap<String, WeightedRoundRobin>>();
    private AtomicBoolean updateLock = new AtomicBoolean();

    public static void main(String[] args) {
        RoundRobinLoadBalanceTest roundRobinLoadBalanceTest = new RoundRobinLoadBalanceTest();
        MyInvoker myInvoker1 = new MyInvoker(1, "small");
        MyInvoker myInvoker2 = new MyInvoker(3, "medium");
        MyInvoker myInvoker3 = new MyInvoker(5, "large");
        List<MyInvoker> myInvokers = new ArrayList<>();
        myInvokers.add(myInvoker1);
        myInvokers.add(myInvoker2);
        myInvokers.add(myInvoker3);
//        for (int i = 0; i < 18; i++) {
//            System.out.println(roundRobinLoadBalanceTest.doSelect(myInvokers, "test"));
//        }

        //模拟10000人并发请求，用户钱包
        CountDownLatch latch = new CountDownLatch(1);
        //模拟10000个用户
        for (int i = 0; i < 3600; i++) {
            AnalogUser analogUser = new AnalogUser(roundRobinLoadBalanceTest, latch, myInvokers);
            analogUser.start();
        }
        //计数器減一  所有线程释放 并发访问。
        latch.countDown();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(roundRobinLoadBalanceTest.smallCount);
        System.out.println(roundRobinLoadBalanceTest.mediumCount);
        System.out.println(roundRobinLoadBalanceTest.largeCount);

    }


    protected MyInvoker doSelect(List<MyInvoker> myInvokers, String invocationGetMethodName) {
        String key = myInvokers.get(0).getKey() + "." + invocationGetMethodName;
        ConcurrentMap<String, WeightedRoundRobin> map = methodWeightMap.get(key);
        if (map == null) {
            methodWeightMap.putIfAbsent(key, new ConcurrentHashMap<String, WeightedRoundRobin>());
            map = methodWeightMap.get(key);
        }
        int totalWeight = 0;
        long maxCurrent = Long.MIN_VALUE;
        long now = System.currentTimeMillis();
        MyInvoker selectedInvoker = null;
        WeightedRoundRobin selectedWRR = null;
        for (MyInvoker myInvoker : myInvokers) {
            String identifyString = myInvoker.getKey();
            WeightedRoundRobin weightedRoundRobin = map.get(identifyString);
            int weight = getWeight(myInvoker);
            if (weight < 0) {
                weight = 0;
            }
            if (weightedRoundRobin == null) {
                weightedRoundRobin = new WeightedRoundRobin();
                weightedRoundRobin.setWeight(weight);
                map.putIfAbsent(identifyString, weightedRoundRobin);
                weightedRoundRobin = map.get(identifyString);
            }
            if (weight != weightedRoundRobin.getWeight()) {
                //weight changed
                weightedRoundRobin.setWeight(weight);
            }
            long cur = weightedRoundRobin.increaseCurrent();
//            weightedRoundRobin.setLastUpdate(now);
            if (cur > maxCurrent) {
                maxCurrent = cur;
                selectedInvoker = myInvoker;
                selectedWRR = weightedRoundRobin;
            }
            totalWeight += weight;
        }
//        if (!updateLock.get() && invokers.size() != map.size()) {
//            if (updateLock.compareAndSet(false, true)) {
//                try {
//                    // copy -> modify -> update reference
//                    ConcurrentMap<String, WeightedRoundRobin> newMap = new ConcurrentHashMap<String,
//                    WeightedRoundRobin>();
//                    newMap.putAll(map);
//                    Iterator<Map.Entry<String, WeightedRoundRobin>> it = newMap.entrySet().iterator();
//                    while (it.hasNext()) {
//                        Map.Entry<String, WeightedRoundRobin> item = it.next();
//                        if (now - item.getValue().getLastUpdate() > RECYCLE_PERIOD) {
//                            it.remove();
//                        }
//                    }
//                    methodWeightMap.put(key, newMap);
//                } finally {
//                    updateLock.set(false);
//                }
//            }
//        }
        if (selectedInvoker != null) {
            selectedWRR.sel(totalWeight);
            System.out.println(selectedInvoker);
            if (selectedInvoker.getKey().equals("small")) {
                smallCount.incrementAndGet();
            } else if (selectedInvoker.getKey().equals("medium")) {
                mediumCount.incrementAndGet();
            } else {
                largeCount.incrementAndGet();
            }
            return selectedInvoker;
        }
        // should not happen here
        return myInvokers.get(0);
    }

    private Integer getWeight(MyInvoker myInvoker) {
        return myInvoker.getWeight();
    }


}

class MyInvoker {
    private Integer weight;
    private String key;

    public MyInvoker(Integer weight, String key) {
        this.weight = weight;
        this.key = key;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "MyInvoker{" +
                "weight=" + weight +
                ", key='" + key + '\'' +
                '}';
    }
}

class AnalogUser extends Thread {
    private CountDownLatch latch;
    private RoundRobinLoadBalanceTest roundRobinLoadBalanceTest;
    private List<MyInvoker> myInvokers;


    public AnalogUser(RoundRobinLoadBalanceTest roundRobinLoadBalanceTest,CountDownLatch latch, List<MyInvoker> myInvokers) {
        super();
        this.roundRobinLoadBalanceTest = roundRobinLoadBalanceTest;
        this.latch = latch;
        this.myInvokers = myInvokers;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            latch.await(); //一直阻塞当前线程，直到计时器的值为0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        post();//发送post 请求


    }

    public void post() {
        roundRobinLoadBalanceTest.doSelect(myInvokers, "test");
    }
}

