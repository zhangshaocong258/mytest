package com.szl;

/**
 * Created by zsc on 2017/2/22.
 * MyRunnable被传入多个线程，线程1自增3次，最后结果为3，线程2自增5次，最后结果为5，只能用ThreadLocal实现
 * 若用n实现，则记过为8
 */
public class ThreadLocalTest {

    public static class MyRunnable implements Runnable {

        //threadLocal和n都放在run外面，都可以调用
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };

        private int n = 0;

        @Override
        public void run() {
            //inner放在里面，都一样
            int inner = 0;
            System.out.println(Thread.currentThread().getName() + "  n   " + n);
            for (int i = 0; i < 6; i++) {
                threadLocal.set(i);
                n++;
                inner++;
                System.out.println(Thread.currentThread().getName() + "  n   " + n);
                System.out.println(Thread.currentThread().getName() + "  inner   " + inner);

            }
            System.out.println(Thread.currentThread().getName() + "  threadLocal   " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + "  n   " + n);
            System.out.println(Thread.currentThread().getName() + "  inner   " + inner);

        }
    }

    public static void main(String[] args) {
        MyRunnable sharedRunnableInstance = new MyRunnable();
        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);
        thread1.start();
        thread2.start();

    }

}
