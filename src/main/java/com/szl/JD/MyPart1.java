package com.szl.JD;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zsc on 2017/4/8.
 * 利用全组合求解，最后时间超了
 */
public class MyPart1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Double> list = new ArrayList<Double>();
        List<Double> result = new ArrayList<Double>();
        for (int i = 0; i < n; i++) {
            int temp = scanner.nextInt();
            list.add(Double.valueOf((double) temp / 100));

        }

        int i = (int) Math.ceil(n * 0.6);
        double sum = 0;
        for (int j = n; j >= i; j--) {
            sum = sum + genCombinations(n, j, list, result);
        }
        System.out.println(String.format("%.5f",sum));


    }

    private static double genCombinations(int len, int i, List<Double> test, List<Double> result) {
        double num = 1;
        double sum = 0;
        int[] array = new int[len];
        for (int j = 0; j < len; j++) {
            if (j < i) {
                array[j] = 1;
            } else {
                array[j] = 0;
            }
        }

        for (int j = 0; j < array.length; j++) {
            if (array[j] == 1) {
                result.add(test.get(j));
            } else {
                result.add(1 - test.get(j));
            }
        }
        for (int j = 0; j < result.size(); j++) {
            num = num * result.get(j);
        }

        sum = sum + num;
        result.clear();

        int n = getCountOfCombinations(len, i);

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
            for (int k = 0; k < array.length; k++) {
                if (array[k] == 1) {
                    result.add(test.get(k));
                } else if (array[k] == 0) {
                    result.add(1 - test.get(k));
                }
            }
            num = 1;
            for (int kk = 0; kk < result.size(); kk++) {
                num = num * result.get(kk);
            }

            sum = sum + num;
            result.clear();
        }

        return sum;

    }

    private static int getCountOfCombinations(int arrLen, int len) {
        BigInteger m = BigInteger.valueOf(1);
        for (int i = 0; i < len; i++) {
            m = m.multiply(BigInteger.valueOf(arrLen - i)) ;
        }
        BigInteger n = BigInteger.valueOf(1);
        for (int i = len; i > 1; i--) {
            n = n.multiply(BigInteger.valueOf(i));
        }
        return (m.divide(n)).intValue() ;
    }
}
