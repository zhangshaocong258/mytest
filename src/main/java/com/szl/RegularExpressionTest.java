package com.szl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zsc on 2018/3/27.
 * 正则表达式
 */
public class RegularExpressionTest {
    public static void main(String[] args) {
        replace();
//        groupTest();
//        findTest();
//        findIndexTest();

    }

    public static void replace() {
        System.out.println("123".matches("\\d*"));
        System.out.println("abac".replaceAll("a(\\w)", "$1$1"));
        System.out.println("\\ \\".replaceAll("\\\\", "\\\\\\\\"));// /和$
        System.out.println("\\\\");
    }

    public static void groupTest() {
        Pattern pattern = Pattern.compile("a(\\w)");
        Matcher matcher = pattern.matcher("abac");
        System.out.println(matcher.groupCount());
    }

    public static void findTest() {
        Pattern pattern = Pattern.compile("(abc)+");
        Matcher matcher = pattern.matcher("abcaabc");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * 从i的位置开始，找到后输出，然后i++，重复此操作
     */
    public static void findIndexTest() {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher("Evering is full of the linnet's wings");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        System.out.println("*******************************");

        int i = 0;
        while (matcher.find(i)) {
            System.out.println(matcher.group());
            i++;
        }
    }
}
