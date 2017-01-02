package com.szl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zsc on 2016/12/25.
 * list交集
 */
public class retainAllTest {
    public static void main(String args[]) {
        Set<Integer> result = new HashSet<Integer>();
        Set<Integer> set1 = new HashSet<Integer>() {{
            add(1);
            add(3);
            add(5);
        }};

        Set<Integer> set2 = new HashSet<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};

        Set<Integer> set3 = new HashSet<Integer>() {{
            add(1);
            add(2);
            add(7);
        }};
        result.clear();
        result.addAll(set1);
        result.retainAll(set2);
        result.retainAll(set3);
        System.out.println("交集：" + result);
    }
}
