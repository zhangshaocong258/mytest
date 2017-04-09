package com.szl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsc on 2017/4/8.
 * 采用位图法，不保证顺序
 * n中取m
 * 001，010,100,011，101,110,111
 */
public class Combination4 {

    public static void main(String args[]) {
        List<String> result = new ArrayList<String>();
        gen(result);
        System.out.println(result);
    }

    private static void gen(List<String> result) {
        String[] str = {"a", "b", "c", "d"};
        //n中取m个数
        int m = 2;
        //元素个数
        int n = str.length;
        //求出位图全组合的结果个数：2^n
        // “<<” 表示左移:各二进位全部左移若干位，高位丢弃，低位补0。即求出2^n=2Bit
        int nbit = 1 << n;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < nbit; i++) {
            list.add(i);
        }
        System.out.println("全组合结果个数为：" + nbit);

        //结果有nbit个。输出结果从数字小到大输出：即输出0,1,2,3,....2^n
        for (int i = 0; i < list.size(); i++) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            //每个数二进制最多可以左移n次，即遍历完所有可能的变化新二进制数值了
            for (int j = 0; j < n; j++) {
                int tmp = 1 << j;
                //& 表示与，两个位都为1时，结果才为1
                if ((tmp & list.get(i)) != 0) {
                    count++;
                    sb.append(str[j]);
                }
            }
            if (count >= m) {
                result.add(sb.toString());
            }
        }
    }
}
