package com.szl;

/**
 * Created by zsc on 2016/7/12.
 */
public class SwapTest {
    public static void main(String[] args) {
        int a = 6;
        int b = 3;
        swap(a, b);

        Data data = new Data(6, 3);
        System.out.println("DataSwap前 a: " + data.a + "    b: " + data.b);
        swap(data);
        System.out.println("a: " + a + "    b: " + b);
        System.out.println("DataSwap后 a: " + data.a + "    b: " + data.b);


    }

    private static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    private static void swap(Data data) {
        int temp = data.a;
        data.a = data.b;
        data.b = temp;
    }
}

class Data {
    int a;
    int b;
    public Data(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
