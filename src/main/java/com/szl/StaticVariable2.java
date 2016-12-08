package com.szl;

/**
 * Created by zsc on 2016/3/18.
 */
public class StaticVariable2 {
    public static void main(String[] args){
        int a = People.eyeNum = 10;
        System.out.println(a);
    }
}
class People {
    static int eyeNum = 0;
}