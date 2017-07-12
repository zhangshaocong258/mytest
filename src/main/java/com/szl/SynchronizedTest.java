package com.szl;

/**
 * Created by zsc on 2017/7/12.
 * 静态同步方法和非静态同步方法是可能同时运行的，因为它们使用的是不同的对象锁。
 * 即1和2竞争锁 1和5不竞争锁
 *
 * 4和5竞争锁
 *
 * 特殊 1和3不竞争锁  2和3不竞争锁
 */
public class SynchronizedTest {

    public static void main(String args[]) {

        TestClass testClass = new TestClass();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                testClass.demoMethod2();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                testClass.demoMethod3();
            }
        });

        thread1.start();
        thread2.start();

    }

}

class TestClass {
    private final static Object lock = new Object();

    public synchronized void demoMethod1() {
        for (int i = 0; i < 15; i++) {
            System.out.println("method1 " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void demoMethod2() {
        synchronized (this) {
            for (int i = 0; i < 15; i++) {
                System.out.println("method2 " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void demoMethod3() {
        synchronized (lock) {
            for (int i = 0; i < 15; i++) {
                System.out.println("method3 " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized static void demoMethod4() {
        for (int i = 0; i < 15; i++) {
            System.out.println("method4 " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void demoMethod5() {
        synchronized (TestClass.class) {
            for (int i = 0; i < 15; i++) {
                System.out.println("method5 " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
