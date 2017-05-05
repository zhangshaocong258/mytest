package com.szl.polymorphism;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by zsc on 2017/5/5.
 * 多态测试
 * 结果会报错
 * 可以加个判断
 */
public class Main {
    public static void main(String args[]) throws Exception{
        Map<Integer, Parent> map = new HashMap<Integer, Parent>();
        Son son = new Son();
        Daughter daughter = new Daughter();
        map.put(1, son);
        map.put(2, daughter);
        for (Parent child : map.values()) {
            ((Son) child).fight();

//            if (child instanceof Son) {
//                ((Son) child).fight();
//            } else if (child instanceof Daughter) {
//                ((Daughter) child).sing();
//            }
        }
    }
}
