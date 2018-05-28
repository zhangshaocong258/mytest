package com.szl;

/**
 * Created by zsc on 2018/3/31.
 * 携程笔试，初始化
 */
public class StaticTest2 {
    public static StaticTest2 s1 = new StaticTest2();
    public static StaticTest2 s2 = new StaticTest2();
    {
        System.out.println("构造块");
    }

    public static void main(String[] args) {
        System.out.println("main");
        StaticTest2 s = new StaticTest2();
    }
    static {
        System.out.println("静态块");
    }
}
/* Output:
构造块
构造块
静态块
main
构造块
*///:~
