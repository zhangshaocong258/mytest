package com.szl;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zsc on 2017/5/5.
 * 大于返回1，小于返回-1
 * 从小到大排列
 */
public class CompareTest {
    public static void main(String args[]) {
        ArrayList<myClazz> list = new ArrayList<myClazz>();
        myClazz c1 = new myClazz(1);
        myClazz c3 = new myClazz(3);
        myClazz c2 = new myClazz(2);
        list.add(c1);
        list.add(c3);
        list.add(c2);
        Collections.sort(list);
        System.out.println(list.get(0).num);
        System.out.println(list.get(1).num);
        System.out.println(list.get(2).num);

    }
}

class myClazz implements Comparable<myClazz>{
    int num;
    myClazz(int num) {
        this.num = num;
    }
    @Override
    public int compareTo(myClazz clazz){
        if (this.num > clazz.num) {
            return 1;
        } else {
            return -1;
        }
    }
}

