package com.szl.netease;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by zsc on 2017/4/1.
 * 360题，求偶串
 * aabb有三个偶串：aa bb aabb
 */
public class OuChuan {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String c = scan.next();

        int num = 0;
        HashMap<Character, Boolean> flag = new HashMap<>();
        for (int i = 0; i < c.length(); i++) {
            flag.put(c.charAt(i), false);
        }

        for (int i = 0; i < c.length() - 1; i++) {
            HashMap<Character, Boolean> flag_new = new HashMap<>(flag); //与flag的hashcode不一样
            int sum_all = 0;
            for (int j = i; j < c.length(); j++) {
                Character word = c.charAt(j);
                if (flag_new.get(word)) {
                    flag_new.put(word, false);
                    sum_all--;
                    if (sum_all == 0) {
                        num++;
                    }
                } else {
                    flag_new.put(word, true);
                    sum_all++;
                }
            }

        }
        System.out.println(num);
    }
}
