package com.szl;

import java.text.DecimalFormat;

/**
 * Created by zsc on 2017/6/5.
 * 格式化
 * 0 表示如果位数不足则以 0 填充
 * # 表示只要有可能就把数字拉上这个位置
 * String 不行
 * String.format
 */
public class DecimalFormatTest {
    public static void main(String args[]) throws Exception {
        double pi = 3.1415927;
        String str = "3.1415927";
        System.out.println(new DecimalFormat("00.####").format(pi));
        System.out.println(new DecimalFormat("##.####").format(pi));
        System.out.println(String.format("%.5f",pi));

    }
}
