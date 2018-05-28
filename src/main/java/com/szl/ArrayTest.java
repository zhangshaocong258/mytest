package com.szl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zsc on 2018/4/10.
 * 数组
 * 示例为二维数组，和一维区别不大，因为copyArray[i]是new出来的，而数组中的内容都是复制的，参见ArraycopyTest
 * System.arraycopy()：如果复制的是对象数组，那么复制的只是对象的引用，不是对象本身的拷贝，对应基本类型数组
 * http://zzc1684.iteye.com/blog/2145848
 */
public class ArrayTest {
    public static void main(String[] args) {
        String[][] strArray = new String[][]{
                {"a", "b", "c"},
                {"A", "B", "C", "D"},
                {"1", "2"}
        };
        copy1(strArray);
        copy2(strArray);
        copy3(strArray);
        copy4(strArray);
    }

    /**
     * 无法实例化具有参数化类型的数组，如 List<String>[] list = new ArrayList<String>[5];
     * 可以通过创建非泛型的数组，然后将其转型
     */
    public static void fun() {
        List<String>[] ls;
        List[] la = new ArrayList[5];
        ls = (List<String>[]) la;
    }

    /**
     * 输出二维数组
     *
     * @param array
     */
    public static void printArray(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }
    }


    /**
     * 使用循环语句遍历复制
     * 由于copyArray[i]是new出来的，所以是深拷贝
     * @param strArray
     */
    public static void copy1(String[][] strArray) {
        String[][] copyArray = new String[strArray.length][];
        for (int i = 0; i < strArray.length; i++) {
            copyArray[i] = new String[strArray[i].length];
            for (int j = 0; j < strArray[i].length; j++) {
                copyArray[i][j] = strArray[i][j];
            }
        }
        printArray(copyArray);
        System.out.println(strArray[0] == copyArray[0] ? "浅拷贝" : "深拷贝");
    }

    /**
     * 使用java.lang.System.arraycopy()方法复制（推荐使用）
     * 由于copyArray[i]是new出来的，所以是深拷贝
     * @param strArray
     */
    public static void copy2(String[][] strArray) {
        String[][] copyArray = new String[strArray.length][];
        for (int i = 0; i < strArray.length; i++) {
            copyArray[i] = new String[strArray[i].length];
            System.arraycopy(strArray[i], 0, copyArray[i], 0, strArray[i].length);
        }
        printArray(copyArray);
        System.out.println(strArray[0] == copyArray[0] ? "浅拷贝" : "深拷贝");
    }

    /**
     * 使用java.lang.Object类的clone()方法复制（不推荐使用）
     *
     * @param strArray
     */
    public static void copy3(String[][] strArray) {
        String[][] copyArray = new String[strArray.length][];
        for (int i = 0; i < strArray.length; i++) {
            copyArray[i] = strArray[i].clone();
        }
        printArray(copyArray);
        System.out.println(strArray[0] == copyArray[0] ? "浅拷贝" : "深拷贝");
    }

    /**
     * 使用java.util.Arrays.copyOf()方法复制（内部实现是调用System.arraycopy）
     *
     * @param strArray
     */
    public static void copy4(String[][] strArray) {
        String[][] copyArray = new String[strArray.length][];
        for (int i = 0; i < strArray.length; i++) {
            copyArray[i] = Arrays.copyOf(strArray[i], strArray[i].length);
        }
        printArray(copyArray);
        System.out.println(strArray[0] == copyArray[0] ? "浅拷贝" : "深拷贝");
    }

}
