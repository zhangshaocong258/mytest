package com.szl.tencent;

/**
 * Created by zsc on 2017/4/4.
 * 求逆序对，只有ABCD四个字母，A<B<C<D，时间复杂度O(n)
 */
public class Part2 {
    public static void main(String args[]) {
        String[] str = {"D", "B", "B", "A", "C"};
        fuction(str, 5);
    }

    static int fuction(String[] str, int len)//求逆序数
    {
        int i;
        int count = 0;
        int a[] = new int[3];//用一个数组个保存字母出现的次数
        for (i = len - 1; i >= 0; i--) {
            switch (str[i]) {
                case "A":
                    a[0]++;
                    a[1]++;
                    a[2]++;
                    break;
                case "B":
                    a[1]++;
                    a[2]++;
                    count += a[0];
                    break;
                case "C":
                    a[2]++;
                    count += a[1];
                    break;
                case "D":
                    count += a[2];
            }
        }
        System.out.println(count);
        return count;
    }
}
