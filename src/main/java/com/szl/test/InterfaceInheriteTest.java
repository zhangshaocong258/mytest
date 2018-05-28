package com.szl.test;


import com.szl.MyInterfaceTest;
import com.szl.MyInterfaceTest2;

/**
 * Created by zsc on 2018/3/15.
 * 测试接口default方法
 * 若2个接口的成员变量命名相同，则报错
 * 若2个接口有相同的方法，不报错
 */
public class InterfaceInheriteTest implements MyInterfaceTest, MyInterfaceTest2 {
    @Override
    public void fun() {
        System.out.println("1");
    }

    public static void main(String[] args) {
        InterfaceInheriteTest interfaceInheriteTest = new InterfaceInheriteTest();
        interfaceInheriteTest.fun();
        interfaceInheriteTest.fun2();
        System.out.println(com.szl.test.InterfaceInheriteTest.a);
    }
}
