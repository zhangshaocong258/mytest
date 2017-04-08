package com.szl;

/**
 * Created by zsc on 2017/4/4.
 * 翻转数组，两种方法
 */
public class Reverse {

    /**
     * 反转数组
     *
     * @param arr
     */
    private static void reverseByteArray(byte[] arr) {
        byte temp;
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = temp;
        }
    }

    public static void Reverse(char[] chars, int lo, int hi) {
        if (chars == null || chars.length <= 0 || lo < 0 || hi >= chars.length || lo > hi) {
            return;
        }
        char tmp;
        while (lo < hi) {
            tmp = chars[lo];
            chars[lo] = chars[hi];
            chars[hi] = tmp;
            lo++;
            hi--;
        }
    }
}
