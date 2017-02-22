package com.szl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zsc on 2017/1/12.
 * 采用位图法，不保证顺序
 * 需要按001，010,100,011，101,110,111排序
 * 怎么排？
 *
 * 四个错误
 * [1, 2, 4, 8, 3, 5, 6, 9, 10, 12, 7, 11, 13, 14, 15]
 * 全组合结果个数为：16
 * [a, b, c, d, ab, ac, bc, ad, bd, cd, abc, abd, acd, bcd, abcd]
 *
 * 应为
 * [1, 2, 4, 8, 3, 5, 9, 6, 10, 12, 7, 11, 13, 14, 15]
 */
public class Combination2 {
    public static void main(String args[]) {
        List<String> result = new ArrayList<String>();
        gen(result);
        System.out.println(result);
//        sort(7);
    }

    private static void gen(List<String> result) {
        String[] str = {"a", "b", "c", "d"};
        int n = str.length;                                  //元素个数。
        //求出位图全组合的结果个数：2^n
        int nbit = 1 << n;                                     // “<<” 表示 左移:各二进位全部左移若干位，高位丢弃，低位补0。:即求出2^n=2Bit。
        List<Integer> list = sort(nbit - 1);
        System.out.println("全组合结果个数为：" + nbit);

        for (int i = 0; i < list.size(); i++) {                        //结果有nbit个。输出结果从数字小到大输出：即输出0,1,2,3,....2^n。
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {                        //每个数二进制最多可以左移n次，即遍历完所有可能的变化新二进制数值了
                int tmp = 1 << j;
                if ((tmp & list.get(i)) != 0) {                            //& 表示与。两个位都为1时，结果才为1
                    sb.append(str[j]);
                }
            }
            result.add(sb.toString());
        }
    }

    private static List<Integer> sort(int n) {
        class Num implements Comparable {
            private int n;

            Num(int n) {
                this.n = n;
            }

            @Override
            public String toString() {
                return String.valueOf(n);
            }

            @Override
            public int compareTo(Object o) {
                Num num = (Num) o;
                if (bitCount1(n) == bitCount1(num.n)) {
                    return n > num.n ? 1 : -1;
                } else {
                    return bitCount1(n) > bitCount1(num.n) ? 1 : -1;
                }
            }
        }
        List<Num> list = new ArrayList<Num>();
        for (int i = 1; i <= n; i++) {
            list.add(new Num(i));
        }
        Collections.sort(list);
        System.out.println(list.toString());
        List<Integer> listInteger = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            listInteger.add(list.get(i).n);
        }
        return listInteger;
    }

    //1的个数
    private static int bitCount1(int n) {
        n = (n & 0x55555555) + ((n >> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >> 16) & 0x0000ffff);
        return n;
    }


}
