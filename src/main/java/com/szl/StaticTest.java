package com.szl;

/**
 * Created by zsc on 2016/10/21.
 * 静态初始化，调用时初始化
 * 执行main函数的时候就对StaticTest进行初始化
 * 若1有static关键字，那么在执行main时，k被赋值为5，所以输出5
 * 若1没有static关键字，那么在执行main时，k为0
 * 执行main之前，所有static全部执行完毕，所以1有static关键字，那么会执行；对于j = getI()，由于按顺序，所以j=0
 *
 */
public class StaticTest {
    static int j = getI();
    static int i = 1;
    static int getI () {
        return i;
    }
    static int k;
    static StaticTest staticTest= new StaticTest();//1 添加或不添加static
    public StaticTest() {
        System.out.println("构造器" + k);
        k = 5;
    }


    public static void main(String[] args) {
        System.out.println(StaticTest.j);
        System.out.println(StaticTest.getI());
        System.out.println(StaticTest.i);
        System.out.println(StaticTest.k);
    }
}
