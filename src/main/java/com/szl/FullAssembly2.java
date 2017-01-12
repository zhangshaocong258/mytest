package com.szl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zsc on 2017/1/12.
 * 采用位图法，不保证顺序
 * 需要按001，010,100,011，101,110,111排序
 * 怎么排？
 *
 *
 */
public class FullAssembly2 {
    public static void main(String args[]) {
        List<String> result = new ArrayList<String>();
        gen(result);
        System.out.println(result);
    }

    private static void gen(List<String> result) {
        String[] str = {"a" , "b" ,"c"};
        int n = str.length;                                  //元素个数。
        //求出位图全组合的结果个数：2^n
        int nbit = 1<<n;                                     // “<<” 表示 左移:各二进位全部左移若干位，高位丢弃，低位补0。:即求出2^n=2Bit。
        System.out.println("全组合结果个数为："+nbit);

        for(int i=0 ;i<nbit ; i++) {                        //结果有nbit个。输出结果从数字小到大输出：即输出0,1,2,3,....2^n。
            System.out.print("组合数值  "+i + " 对应编码为： ");
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<n ; j++) {                        //每个数二进制最多可以左移n次，即遍历完所有可能的变化新二进制数值了
                int tmp = 1<<j ;
                if((tmp & i)!=0) {                            //& 表示与。两个位都为1时，结果才为1
                    sb.append(str[j]);
                }
            }
            result.add(sb.toString());
            System.out.println();
        }
    }
}
