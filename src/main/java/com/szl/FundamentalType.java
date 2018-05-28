package com.szl;

/**
 * Created by zsc on 2018/4/9.
 *
 * java基本类型
 *
 *
 * 堆相对进程来说是全局的，能够被所有线程访问；而栈是线程局部的，只能本线程访问。
 *
 *
 * int，double，long 等基本类型属于值类型，在内存中的表示就直接是原始的值，比如int a = 3 可能就是0x00000011；double等浮点数有浮点数的表示法。
 * 至于这个内存放在哪，也就是它的地址是什么，取决于当前语境；比如局部变量那就是在栈上，如果是类的成员变量，static 的在meta space，实例变量堆上。
 *
 *
 * https://www.zhihu.com/question/31203609
 * https://www.zhihu.com/question/20618891/answer/114125846
 *  num
 *  ____
 * |_10_|
 *
 *  str
 *  ____        ____
 * |0x10|  →  |hello|
 *
 *
 * num是基本类型，值就直接保存在变量中。而str是引用类型，变量中保存的只是实际对象的地址。一般称这种变量为"引用"，引用指向实际对象，实际对象中保存着内容。
 * 对于基本类型 num ，赋值运算符会直接改变变量的值，原来的值被覆盖掉。
 * 对于引用类型 str，赋值运算符会改变引用中所保存的地址，原来的地址被覆盖掉。但是原来的对象不会被改变（重要）
 *
 *
 */
public class FundamentalType {
    public static void main(String[] args) {
        int[][] a = new int[3][];
        a[0] = new int[1];
        a[1] = new int[1];
        a[2] = new int[1];
        System.out.println(a[0].length + " " + a[1].length + " " + a[2].length);
        fun(a);
        System.out.println(a[0].length + " " + a[1].length + " " + a[2].length);


    }

    public static void fun(int[][] a) {
        a[0] = new int[2];
        a[1] = new int[2];
        a[2] = new int[2];
    }

}
