package com.szl.JD;

import java.util.Scanner;

/**
 * Created by zsc on 2017/4/8.
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
        for (int i = (3 * n + 4) / 5; i <= n; i++) {
            result += dp[n][i];
        }
        System.out.println(String.format("%.5f", result));
        in.close();
    }

}
