package com.szl.proxy;

/**
 * Created by zsc on 2017/3/1.
 */
public class RealSubject implements Subject{
    @Override
    public void rent()
    {
        System.out.println("我想出租房子");
    }

    @Override
    public void hello(String str)
    {
        System.out.println("hello: " + str);
    }
}
