package com.szl;

/**
 * Created by zsc on 2018/4/2.
 * 普通内部类：1.不能声明静态方法和属性 2.可以访问外部类任意方法和属性
 * 静态内部类：1.可以声明静态方法和属性 2.只能访问外部类静态方法和属性 3.可以单独初始化
 * 当外部类需要使用内部类，而内部类无需外部类资源，并且内部类可以单独创建的时候会考虑采用静态内部类的设计。《Effective Java》Builder
 * https://www.cnblogs.com/kungfupanda/p/7239414.html
 */
public class InnerClassTest {
    private static int a = 0;
    private int b = 1;
    class Inner1{
        private int aa = 0;
        void fun() {
            System.out.println(a + b + aa);
        }
    }

    static class Inner2 {
        private int aaa = 0;
        private static int bbb = 0;
        void fun() {
            System.out.println(a + aaa + bbb);
        }
    }

    public static void main(String[] args) {
        InnerClassTest innerClassTest = new InnerClassTest();
        InnerClassTest.Inner1 inner1= innerClassTest.new Inner1();
        Inner1 inner11 = new InnerClassTest().new Inner1();
        Inner2 inner2 = new InnerClassTest.Inner2();
    }

}
