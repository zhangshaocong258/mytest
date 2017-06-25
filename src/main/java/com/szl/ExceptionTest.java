package com.szl;

/**
 * Created by zsc on 2016/9/7.
 * try catch即可继续运行
 * throws Exception直接停止
 */
public class ExceptionTest {
    public static void main(String[] agrs) {
        int[] arr = {1, 2, 4, 0, 5, 10, 0, 20, 25, 50, 0, 100};
        int r = 0;
        for (int i = 0; i < arr.length; i++) {
            try {
                r = 100 / arr[i];
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println("错误：" + i + "    " + arr[i]);
                continue;
            }
            System.out.println("r: " + r +" " + 100);
        }
//        fun();
    }

    private static void fun() throws Exception{
        int[] arr = {1, 2, 4, 0, 5, 10, 0, 20, 25, 50, 0, 100};
        int r = 0;
        for (int i = 0; i < arr.length; i++) {

            r = 100 / arr[i];

            System.out.println("r: " + r +" " + 100);
        }
    }
}
