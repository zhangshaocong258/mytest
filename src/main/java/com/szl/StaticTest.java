package com.szl;

/**
 * Created by zsc on 2016/10/21.
 */
public class StaticTest {
    static int j = getI();
    static int i = 1;
    static int getI () {
        return i;
    }
    static int k;
    static StaticTest staticTest= new StaticTest();//添加或不添加static
    public StaticTest() {
        k = 5;
    }


    public static void main(String[] args) {
        System.out.println(StaticTest.j);
        System.out.println(StaticTest.getI());
        System.out.println(StaticTest.i);
        System.out.println(StaticTest.k);
    }
}
