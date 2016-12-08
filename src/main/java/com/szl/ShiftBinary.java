package com.szl;

/**
 * Created by zsc on 2016/11/30.
 */
public class ShiftBinary {
    //移位运算
    public static void main(String[] args) {
        //原始数二进制
        int number = 11;
        printInfo(number);

        //左移一位
        number = number << 1;
        printInfo(number);

        //右移一位
        number = number >> 1;
        printInfo(number);

        //右移一位
        number = number >> 1;
        printInfo(number);

        //右移一位
        number = number >> 1;
        printInfo(number);
    }

    /**
     * 输出一个int的二进制数
     *
     * @param num
     */
    private static void printInfo(int num) {
        System.out.println(Integer.toBinaryString(num));
    }
}
