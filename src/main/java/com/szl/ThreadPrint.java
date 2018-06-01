package com.szl;

/**
 * Created by zsc on 2018/6/1.
 */
public class ThreadPrint {
    static int num = 0;
    static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; num < 100; ) {

                    if (!flag && (num == 0 || ++num % 2 == 0)) {
                        System.out.println("t1 " + num);
                        flag = true;
                    } else {
                        System.out.println("else t1 " + num);

                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; num < 100; ) {

                    if (flag && (++num % 2 != 0)) {
                        System.out.println("t2 " + num);
                        flag = false;
                    } else {
                        System.out.println("else t2 " + num);
                    }
                }
            }
        });
        t1.start();
        t2.start();

    }
}
