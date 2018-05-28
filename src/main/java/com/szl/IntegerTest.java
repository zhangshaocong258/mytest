package com.szl;

/**
 * Created by zsc on 2018/3/27.
 */
public class IntegerTest {
    public static void main(String[] args) {
//        Integer integer1 = 3; //equal
//        Integer integer2 = 3;

//        Integer integer1 = new Integer(3); //!equal
//        Integer integer2 =  new Integer(3);

        Integer integer1 = Integer.valueOf(3); //equal
        Integer integer2 =  Integer.valueOf(3);

        if (integer1 == integer2) {
            System.out.println("integer1 == integer2");
        } else {
            System.out.println("integer1 != integer2");
        }

        Integer integer3 = 300;
        Integer integer4 = 300;

        if (integer3 == integer4) {
            System.out.println("integer3 == integer4");
        } else {
            System.out.println("integer3 != integer4");
        }

    }
}
