package com.szl;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zsc on 2017/9/16.
 * 两个线程交替打印
 * 需要2个condition？
 *
 */
public class AlternatePrint {
    private static Lock lock = new ReentrantLock(true);
    private static Condition condition = lock.newCondition();
    private static Condition condition2 = lock.newCondition();

    public static void main(String args[]) {
        new Thread(new MyRunnable1()).start();
        new Thread(new MyRunnable2()).start();
    }

    public static class MyRunnable1 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                lock.lock();
                try {
                    if (i % 2 == 0) {
                        System.out.println(i);
                        condition.await();
                    } else {
                        condition.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }


        }
    }

    public static class MyRunnable2 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                lock.lock();
                try {
                    if (i % 2 == 1) {
                        System.out.println(i);
                        condition.await();
                    } else {
                        condition.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }

}


