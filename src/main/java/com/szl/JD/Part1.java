package com.szl.JD;

import java.util.Scanner;

/**
 * Created by zsc on 2017/4/8.
 * 通过考试
 * 91
 * 91 89 9 39 8 62 57 25 50 82 39 0 18 86 13 59 58 59 29 38 4 8 33 96 44 59 39 6 69 62 63 2 100 100 28 60 33 35 14 97 31 52 28 70 80 33 65 100 33 46 90 41 21 5 80 46 86 80 52 18 2 24 0 78 23 15 74 29 21 12 36 86 91 63 77 17 67 3 37 81 84 78 39 66 96 42 70 100 94 67 61
 *
 * 65
 * 86 65 71 71 49 52 100 14 4 53 90 4 33 68 56 0 90 1 56 49 57 54 60 2 80 96 13 86 20 35 61 89 46 38 18 20 98 9 0 65 86 43 69 25 5 29 50 60 79 94 86 7 51 25 96 60 63 54 61 47 2 9 41 36 88
 *
 */
public class Part1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double[] p = new double[n];
        for (int i = 0; i < n; i++) {
            p[i] = (double) in.nextInt() / 100;
        }
        double[][] dp = new double[n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = dp[i - 1][0] * (1 - p[i - 1]);
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = dp[i - 1][j - 1] * p[i - 1] + dp[i - 1][j] * (1 - p[i - 1]);
            }
        }
        double result = 0;
        //保证进一，因为0.6乘以任何一个数小数点后最小为2，因此最后加上4/5
        for (int i = (3 * n + 4) / 5; i <= n; i++) {
            result += dp[n][i];
        }
        System.out.println(String.format("%.5f", result));
        in.close();
    }

}
