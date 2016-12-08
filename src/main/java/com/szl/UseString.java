package com.szl;

/**
 * Created by zsc on 2016/3/8.
 */
public class UseString {

    public static void main(String[] args){
        String str1 = new String() ;
        String str2 = null ;
        String str3 = "" ;
        String str4 = new String("") ;

        System.out.println(str1==str2);                //内存地址的比较，返回false
        System.out.println(str1.equals(str2));         //值的比较，返回false
        System.out.println(str2==str3);                //内存地址的比较，返回false
        System.out.println(str3.equals(str2));         //值的比较，返回false
        System.out.println(str1==str3);                //内存地址的比较，返回false
        System.out.println(str1.equals(str3));
        System.out.println(str1.equals(str4));
        System.out.println(str3.equals(str4));
    }
}
