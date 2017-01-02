package com.szl;

import java.lang.reflect.Method;

/**
 * Created by zsc on 2016/5/4.
 * 反射测试
 */
public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        DisPlay disPlay = new DisPlay();
        // 获得Class
        Class<?> cls = disPlay.getClass();
        // 通过Class获得DisPlay类的show方法
        Method method = cls.getMethod("show", String.class);
        // 调用show方法
        method.invoke(disPlay, "Wanggc");
    }
}

class DisPlay {
    public void show(String name) {
        System.out.println("Hello :" + name);
    }
}