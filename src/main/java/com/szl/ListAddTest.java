package com.szl;


import java.util.ArrayList;

/**
 * Created by zsc on 2016/7/18.
 */
public class ListAddTest {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        list2.add("d");
        list2.add("e");
        for (String str : list1) {
            System.out.println("list1: " + str);
        }
        list1.addAll(list2);
        for (String str : list1) {
            System.out.println("list1_add: " + str);
        }
    }
}
