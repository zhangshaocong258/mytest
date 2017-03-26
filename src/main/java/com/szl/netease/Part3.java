package com.szl.netease;

import java.util.Scanner;

/**
 * Created by zsc on 2017/3/25.
 * 魔力环问题
 */
public class Part3 {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int k = scanner.nextInt();

        Scanner scanner2 = new Scanner(System.in);
        String[] array = scanner2.nextLine().split(" ");
        int[] test = new int[length];
        for (int i = 0; i < length; i++) {
            test[i] = Integer.parseInt(array[i]);
        }
        int[] a = getArray(test,k);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }


    }

    static int[] getArray(int[] str,int k) {
        int[] reStr = reverse(str);
        int[] result = new int[reStr.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = str[i] + reStr[i];
            if (result[i] >= 100) {
                result[i] = result[i] % 100;
            }
        }
        k = k - 1;
        for (int i2 = 0; i2 < k; i2++) {
            str = result;
            reStr = reverse(str);
            result = new int[reStr.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = str[i] + reStr[i];
                if (result[i] >= 100) {
                    result[i] = result[i] % 100;
                }
            }
        }

        return result;
    }

    static int[] reverse(int[] str) {
        int[] re = new int[str.length];
        for (int i = 0; i <= str.length - 2; i++) {
            re[i] = str[i + 1];
        }
        re[re.length - 1] = str[0];
        return re;
    }
}
