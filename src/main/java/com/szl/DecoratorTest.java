package com.szl;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by zsc on 2016/10/21.
 * 把list装到set中
 */
public class DecoratorTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("a1");
        list.add("a1");
        list.add("b1");
        list.add("c1");
        System.out.println("List =" + list);
        HashSet<String> set = new HashSet<String>(list);
        System.out.println("Set =" + set);
        System.out.println("List =" + list);
    }
}
