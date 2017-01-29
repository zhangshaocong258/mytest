package com.szl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsc on 2017/1/27.
 * 非递归实现
 * 递减最小变化：
 * 从右往左找第一对10交换，表示想把数变小
 * 但希望减小的最少，则交换点后面的数要尽量大，所以把1全部移到交换点后的高位
 * 即可实现从大到小按序排列
 * 11100
 * 11010
 * 11001
 * 10110
 * 10101
 * 10011
 * 01110
 * 01101
 * 01011
 * 00111
 */
public class Combination3 {

    public static void main(String[] args) {
        List<String> test = new ArrayList<String>();//1234
        List<String> result = new ArrayList<String>();//1234,123,124,...,1,2,3,4
        //初始化输入数据
        for (int i = 1; i < 5; i++) {
            test.add(String.valueOf(i));
        }
        System.out.println("test " + test + "   " + test.size());

        //计算结果
        for (int i = test.size(); i > 0; i--) {
            genCombinations(test.size(), i, test, result);
        }
        System.out.println(result);
    }

    private static void genCombinations(int len, int i, List<String> test, List<String> result) {
        int[] array = new int[len];
        for (int j = 0; j < len; j++) {
            if (j < i) {
                array[j] = 1;
            } else {
                array[j] = 0;
            }
        }

        //得到C(len,len)
        for (int j = 0; j < array.length; j++) {
            if (array[j] == 1) {
                result.add(test.get(j));
            }
        }
        result.add("\n");

        int n = getCountOfCombinations(len, i);//得到C(len,i)组合数个数

        for (int j = 1; j < n; j++) {
            for (int k = array.length - 1; k > 0; k--) {
                if (array[k] == 0 && array[k - 1] == 1) {
                    array[k] = 1;
                    array[k - 1] = 0;
                    int start = k;
                    int end = len - 1;
                    while (true) {
                        while (array[start] == 1) {
                            start++;
                            if (start >= len)
                                break;
                        }
                        while (array[end] == 0) {
                            end--;
                            if (end < i)
                                break;
                        }

                        if (start < end) {
                            int temp = array[end];
                            array[end] = array[start];
                            array[start] = temp;
                        } else {
                            break;
                        }
                    }
                    break;
                }
            }
            //得到一条组合
            for (int k = 0; k < array.length; k++) {
                if (array[k] == 1) {
                    result.add(test.get(k));
                }
            }
            result.add("\n");
        }


    }

    //获取长度为len的组合数C(arrLen,len)的个数
    private static int getCountOfCombinations(int arrLen, int len) {
        int m = 1;
        for (int i = 0; i < len; i++) {
            m *= arrLen - i;
        }
        int n = 1;
        for (int i = len; i > 1; i--) {
            n *= i;
        }
        return m / n;
    }
}
