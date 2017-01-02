package com.szl;

/**
 * Created by zsc on 2016/12/12.
 * for循环的try catch 重复了
 */
public class ForCirculation {
    public static void main(String args[]) {
        try {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(100 / (i - 5));
//                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        } finally {
            System.out.println("finally");
        }
    }
}
