package com.szl;

/**
 * Created by zsc on 2016/3/4.
 * 内部类使用
 */
public class StaticVariable {
    public static void main(String[] args){
        StaticVariable s = new StaticVariable();
        StaticVariable.People p1 = s.new People();
        StaticVariable.People p2 = s.new People();
        p1.name = "p1";
        p2.name = "p2";
        p1.eyeNum = 1;
        p2.eyeNum = 3;

        System.out.println(p1.eyeNum);
    }
    class People {
        int eyeNum = 0;
        String name = "";

    }

}


