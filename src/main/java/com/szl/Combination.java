package com.szl;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zsc on 2016/12/26.
 * 全组合，递归实现
 * 输入1234
 * 假设我们的集合为{1，2，3} ，我们从头扫描集合的元素，第一个元素为1。对于这个元素，我们可以把他放到组合集中，然后在剩下的集合里再去选择；
 * 也可以不把他放到组合集中，在剩下的集合里去选择元素放到组合集中。一般化的，假设我们的集合有n个元素，要求m个元素的组合。我们扫描每一个元素，
 * 针对该元素，我们可以将其放到组合集中，然后在剩下的n-1个元素中再选择m-1个元素；我们也可以不放该元素进集合，而直接从剩下的n-1个元素中选择m个元素。
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
