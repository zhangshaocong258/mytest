package com.szl;

/**
 * Created by zsc on 2018/3/15.
 * java8 可以引入default方法，此default非访问权限的default
 */
public interface MyInterfaceTest {
//    public static int a = 5;

    public abstract void fun();

    default void fun2() {
        System.out.println("2");
    }
}
