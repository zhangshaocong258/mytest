package com.szl;

/**
 * Created by zsc on 2018/3/25.
 * 内部类final
 *
 *
 * 使匿名内部类和外部环境局部变量保持同步
 *
 *
 * java8去掉了强制声明final的编译时检查，但其他没有任何变化，因此最好写上final
 *
 */
interface AnnoInner {
    int addXYZ();
}

public class Outer {
    private int x = 100;
    public AnnoInner getAnnoInner(final int x) {
        final int y = 100;
        return new AnnoInner() {
            int z = 100;
            public int addXYZ() {
                return x + y + z;
            }
//            public void changeY(){y+=1;}	//这个函数无法修改外部环境中的自由变量y。
        };
    }
}