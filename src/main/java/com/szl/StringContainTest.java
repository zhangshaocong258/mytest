package com.szl;

/**
 * Created by zsc on 2016/5/9.
 */
public class StringContainTest {
    public static void main(String[] args) {
        String originString = "aa\r\fbb";
        String findString = "\r\f";
        boolean result = originString.contains(findString);
        System.out.println(result);
    }
}
