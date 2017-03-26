package com.szl.netease;

import java.util.Scanner;

/**
 * Created by zsc on 2017/3/25.
 * 出租车问题
 */
public class Part1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//出租车个数
        int[] tx = new int[n];//出租车x位置
        for (int i = 0; i < n; i++) {
            tx[i] = scanner.nextInt();
        }

        int[] ty = new int[n];//出租车y位置
        for (int i = 0; i < n; i++) {
            ty[i] = scanner.nextInt();
        }

        //公司的位置
        int gx = scanner.nextInt();
        int gy = scanner.nextInt();

        int walkTime = scanner.nextInt();//走路用时
        int taxiTime = scanner.nextInt();//出租车用时

        int tmin = (Math.abs(gx) + Math.abs(gy)) * walkTime;//最小时间，初始化为走路时间
        int i = 0;
        int t = 0;
        while (i < n) {
            if (tx[i] * tx[i] + ty[i] * ty[i] > gx * gx + gy * gy) {
                i++;
                continue;
            }
            t = (Math.abs(tx[i]) + Math.abs(ty[i])) * walkTime + (Math.abs(gx - tx[i]) + Math.abs(gy - ty[i])) * taxiTime;
            if (t < tmin){
                tmin = t;
            }
            i++;
        }
        System.out.println(tmin);
    }

}
