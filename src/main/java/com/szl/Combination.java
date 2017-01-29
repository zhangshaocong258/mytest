package com.szl;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zsc on 2016/12/26.
 * 全组合，递归实现
 * 输入1234
 */
public class Combination {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Character> com = new ArrayList<>();
        int len = s.length() + 1;

        for (int i = len - 1; i != 0; i--) {
            getCombinations(list, s.toCharArray(), 0, i, com);
            System.out.println("第" +  i + "次" + list.size());
            for (int j = 0; j < list.size(); j++) {
                System.out.println("当前list " + list.get(j));
            }
        }
        for (int i = 0; i != list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println(getCountOfCombinations(s.length(), 2));
        scan.close();
    }

    private static void getCombinations(ArrayList<String> list, char[] cs, int start, int len, ArrayList<Character> com) {//len为组合的长度
        if (len == 0) {
            String s = "";
            for (int i = 0; i != com.size(); i++) {
                s = s.concat(com.get(i).toString());
            }
            list.add(s);
            return;
        }
        if (start == cs.length) {
            return;
        }
        com.add(cs[start]);
        getCombinations(list, cs, start + 1, len - 1, com);
        com.remove(com.size() - 1);
        getCombinations(list, cs, start + 1, len, com);
    }

    private static int getCountOfCombinations(int arrLen, int len) {//获取长度为len的组合数
        int m = 1;
        for (int i = 0; i != len; i++) {
            m *= arrLen - i;
        }
        int n = 1;
        for (int i = len; i != 1; i--) {
            n *= i;
        }
        return m / n;
    }
}
