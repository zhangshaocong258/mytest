package com.szl.netease;

import java.util.Scanner;

/**
 * Created by zsc on 2017/3/25.
 * GGGGGBBGG
 */
public class Part2 {
    public static void main(String[] args)  {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        int count1 = getNum(str);
        int count2 = getNum2(str);
        System.out.println(count1 + "   " + count2);
        System.out.println(count1 < count2? count1:count2);

    }


    static int getNum(String s) {
        char[] chars = s.toCharArray();
        int size = chars.length;
        int i = 0;
        int j = size - 1;
        int count = 0;
        boolean flag = false;
        while (j>i) {
            flag = true;
            if (chars[i] == 'G') {
                i++;
                flag = false;
            }

            if (chars[j] == 'B') {
                j--;
                flag = false;
            }
            if (flag) {
                count = count + j - i;
                i++;
                j--;
            }


        }
        return count;
    }

    static int getNum2(String s) {
        char[] chars = s.toCharArray();
        int size = chars.length;
        int i = 0;
        int j = size - 1;
        int count = 0;
        boolean flag = false;
        while (j>i) {
            flag = true;
            if (chars[i] == 'B') {
                i++;
                flag = false;
            }

            if (chars[j] == 'G') {
                j--;
                flag = false;
            }
            if (flag) {
                count = count + j - i;
                i++;
                j--;
            }


        }
        return count;
    }
}
