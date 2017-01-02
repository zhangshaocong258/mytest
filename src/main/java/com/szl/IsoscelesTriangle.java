package com.szl;

/**
 * Created by zsc on 2016/3/11.
 * 三角形
 */
public class IsoscelesTriangle {
    public static void main(String[] args) {
//        new CreateIsoscelesTriangle(Integer.parseInt(args[0]));
        new CreateIsoscelesTriangle(4);
    }
}

class CreateIsoscelesTriangle {
    CreateIsoscelesTriangle(int edge) {
        for (int i = 1; i <= edge; i++) {
            for (int j = 0; j < edge - i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}