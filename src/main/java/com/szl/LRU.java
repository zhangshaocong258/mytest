package com.szl;

/**
 * Created by zsc on 2016/6/12.
 * LRU算法 中兴笔试
 */
public class LRU {
    public static void main(String[] args) {
        int max_size = 3;
        int queue[] = {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0};
        int count = LuaMethod(max_size, queue);
        System.out.println("结果：" + count);
    }
    public static int LuaMethod(int max_size, int queue[]) {
        int count = 0;//计数
        int current_count = 0;//当前长度
        int back = 0;//插入位置
        int array[] = new int[max_size];
        boolean flag;//判断是否更改

        //对每一个输入的字符进行判断
        for (int i = 0; i < queue.length; i++) {
            flag = false;
            //先判断是否重复
            for (int j = 0; j < current_count; j++) {
                //如果重复，则删除，并将当前长度减一，插入位置减一
                if (array[j] == queue[i]) {
                    flag = true;
                    for (int k = j; k < current_count - 1; k++) {
                        array[k] = array[k + 1];
                    }
                    current_count--;
                    back--;
                }
            }

            //判断数组是否满了，未满，则直接添加，否则，删除第一个，即最长未使用，微端添加
            if (current_count < max_size) {
                array[back] = queue[i];
                back++;
                current_count++;
                if (!flag) {
                    count++;
                }
                System.out.println("count" + count + " back " + back + "current" + current_count);
                System.out.println("array" + array.length);
            } else if (current_count == max_size) {
                //删除头部
                for (int j = 0; j < max_size - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[max_size - 1] = queue[i];//尾端添加
                if (!flag) {
                    count++;
                }            }

            //输出每次的数组内的内容
            for (int j = 0; j < current_count; j++) {
                System.out.print(array[j] + " ");
            }
            System.out.println();
        }
        return count;
    }
}

