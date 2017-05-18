package com.szl;

import java.util.Random;

/**
 * Created by zsc on 2016/10/25.
 *
 * ！！！！！！！！！运行时每一部分分开运行，否则结果出错
 * intern 美团博客
 *
 */
public class InternTest {
    static final int MAX = 1000 * 10000;
    static final String[] arr = new String[MAX];

    public static void main(String[] args) throws Exception {

        /**
         * 正确例子
         */
        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < DB_DATA.length; i++) {
            DB_DATA[i] = random.nextInt();
        }
        long t = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
//            arr[i] = String.valueOf(DB_DATA[i % DB_DATA.length]);
            arr[i] = String.valueOf(DB_DATA[i % DB_DATA.length]).intern();
        }
        System.out.println((System.currentTimeMillis() - t) + "ms");
        System.gc();


        /**
         * 易混淆例子
         * 注意区分
         */
        String s = new String("1");//指向堆内存新建的对象，先在常量池找"1"，找不到则在常量池创建，因此生成2个对象：常量池"1" 和堆中的字符串对象
        s.intern();//s对象去常量池中寻找后发现"1"已经在常量池里了
        String s2 = "1";// s2的引用指向常量池中的"1"对象
        System.out.println(s == s2);
        System.out.println(s == s.intern());//常量池中有，但s指向并不变，还是指向堆中的字符串对象

        String s3 = new String("1") + new String("1");//指向堆中新建的字符串对象，常量池中只有"1"；一共生成4个对象：常量池"1"，2个new String("1")，s3指向的字符串对象
        s3.intern();//将s3中的"11"字符串放入String常量池中，因为此时常量池中不存在"11"字符串，但常量池中不需要再存储（新生成）一份对象了，可以直接存储堆中的引用，这份引用指向 s3 引用的对象，也就是说引用地址是相同的
        String s4 = "11";//指向常量池中"11"，即s3指向的对象
        System.out.println(s3 == s4);//s3intern后，相同
        System.out.println(s3 == s3.intern());//直接将s3指向的对象（堆中）“移动”到常量池中，因此s3指向由指向堆变为指向常量池


        /**
         * 交换位置，变为false
         */
        String s33 = new String("1") + new String("1");//此时s33指向堆中对象，常量池中只有"1"
        String s44 = "11";//常量池创建"11"
        s33.intern();//常量池中已存在"11"，不用移动s33指向
        System.out.println(s33 == s44);//因此结果为false

        /**
         * String 连接
         */

        String a = "ab";
        String a2 = "a" + "b";
        System.out.println(a == a2);//true 编译时优化

        String a3 = "a" + new String("b");
        System.out.println(a == a3);//false


    }
}
