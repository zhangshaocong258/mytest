package com.szl.tencent;

import java.util.Scanner;

/**
 * Created by zsc on 2017/4/3.
 * 按格式输出，个数是16的倍数
 */
public class Part1 {
    public static void main(String args[]) {
//        System.out.println((int ) a);
//        System.out.println(Integer.toHexString((int)a));
//        System.out.print(String.format("%08d", Integer.valueOf(Integer.toHexString((int)a))));

        String s = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv";
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length / 16; i++) {
            System.out.print(String.format("%08d", i));
            System.out.print("  ");
            for (int j = 0; j < 16; j++) {
                System.out.print(Integer.toHexString(chars[16 * i + j]) + " ");
                if (j == 7 || j == 15) {
                    System.out.print(" ");
                }
            }
            for (int j = 0; j < 16; j++) {
                System.out.print(chars[16 * i + j] + " ");
            }
            System.out.println();

        }

    }
}
