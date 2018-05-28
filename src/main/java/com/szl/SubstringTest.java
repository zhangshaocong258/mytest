package com.szl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zsc on 2018/3/26.
 * -Xms100m -Xmx100m
 * 1.6 substring 报错，split出来的String对象直接使用原String对象的char[]
 * 1.7 以后做了修改，使用了 System.arraycopy()
 */
public class SubstringTest {
    private static Random rnd = new Random();
    private static final int MEGA = 1024 * 1024;

    public static void main(String[] args) {
        String largeString = new String(new byte[100000]);
        System.out.println(largeString);
        List<String> substrings = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            String randomStr = createRandomStr(MEGA);
            String subStr = randomStr.substring(1000, 1004);
            substrings.add(subStr);
            System.out.println(i);
        }
    }

    private static String createRandomStr(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++){
            sb.append((char) ('a' + rnd.nextInt('z' - 'a' + 1)));
        }
        return sb.toString();
    }

}
