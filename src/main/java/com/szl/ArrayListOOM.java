package com.szl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zsc on 2018/4/8.
 * 保留list中的某个引用，其他的可以被正确回收
 * -Xms100m -Xmx100m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=gc.hprof -XX:+PrintGCDetails
 */
public class ArrayListOOM {
    private static Random rnd = new Random();
    private static final int MEGA = 1024 * 1024;

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            List<String> subList = createRandomList(MEGA);
            list.add(subList.get(0));
            System.out.println(i);
        }
        System.out.println("size " + list.size());
    }

    private static List<String> createRandomList(int length) {
        List<String> subList = new ArrayList<String>(length);

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + rnd.nextInt('z' - 'a' + 1)));
        }
        subList.add(sb.toString());
//        subList.add(sb.toString());//注释与否对结果没有影响，因此判断正确回收
        return subList;
    }

}
