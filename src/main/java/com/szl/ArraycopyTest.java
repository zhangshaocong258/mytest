package com.szl;

/**
 * Created by zsc on 2018/3/26.
 * 基本类型和string深拷贝
 * 引用类型浅拷贝
 * http://www.tmser.com/post-142.html
 *
 * 根本原因在于java只存在传值，该值为基本类型或者引用
 * string之所以System.arraycopy()与基本类型效果相同，是因为string是不可变的，即string类在设计时，没有修改char[]中内容，
 * 无论是substring()还是其他函数，都会返回一个新的string，原来的string并不改变
 *
 * 只要没有new这个关键字，也没有深clone，那么引用类型在内存中只存在一份
 */
public class ArraycopyTest {
    public static void main(String[] args) {
        arraycopyTest();
        copyTest();
    }

    //将原数组中的内容拷贝到新数组
    public static void copyTest() {
        //初始化
        String[] strings = new String[]{"a", "b", "c"};

        char[] chars = new char[]{'1', '2', '3'};

        StringBuilder[] sb = new StringBuilder[3];
        sb[0] = new StringBuilder("aaa");
        sb[1] = new StringBuilder("bbb");
        sb[2] = new StringBuilder("ccc");


        String[] stringsCopy = new String[2];
        char[] charsCopy = new char[2];
        StringBuilder[] sbCopy = new StringBuilder[2];

        for (int i = 0; i < stringsCopy.length; i++) {
            stringsCopy[i] = strings[i];
        }
        for (int i = 0; i < charsCopy.length; i++) {
            charsCopy[i] = chars[i];
        }
        for (int i = 0; i < sbCopy.length; i++) {
            sbCopy[i] = sb[i];
        }

        print(strings, stringsCopy, chars, charsCopy, sb, sbCopy);
        strings[0] = "aa";
        chars[0] = '0';
//        sb[0].append("11");
        sb[0]= new StringBuilder("111");
        print(strings, stringsCopy, chars, charsCopy, sb, sbCopy);
        System.out.println();
    }

    public static void arraycopyTest() {
        //初始化
        String[] strings = new String[]{"a", "b", "c"};

        char[] chars = new char[]{'1', '2', '3'};

        StringBuilder[] sb = new StringBuilder[3];
        sb[0] = new StringBuilder("aaa");
        sb[1] = new StringBuilder("bbb");
        sb[2] = new StringBuilder("ccc");


        String[] stringsCopy = new String[2];
        char[] charsCopy = new char[2];
        StringBuilder[] sbCopy = new StringBuilder[2];

        System.arraycopy(strings, 0, stringsCopy, 0, 2);
        System.arraycopy(chars, 0, charsCopy, 0, 2);
        System.arraycopy(sb, 0, sbCopy, 0, 2);
        print(strings, stringsCopy, chars, charsCopy, sb, sbCopy);

        strings[0] = "aa";
        chars[0] = '0';
        sb[0].append("11");
        print(strings, stringsCopy, chars, charsCopy, sb, sbCopy);
        System.out.println();

    }

    public static void print(String[] strings, String[] stringsCopy, char[] chars, char[] charsCopy, StringBuilder[] sb, StringBuilder[] sbCopy) {
        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < stringsCopy.length; i++) {
            System.out.print(stringsCopy[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < charsCopy.length; i++) {
            System.out.print(charsCopy[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < sb.length; i++) {
            System.out.print(sb[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < sbCopy.length; i++) {
            System.out.print(sbCopy[i] + " ");
        }
        System.out.println();

    }
}
