package com.szl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zsc on 2016/7/27.
 * pcap 字符串转为byte
 */
public class String2Byte {
    public static void main(String[] args) {

        //字符串前面补0
        String a = "123456789";
        byte[] aa = a.getBytes();

        byte[] b = new byte[100];
        System.out.println("bbb: " + b.length);
        byte[] temp = a.getBytes();
        int length = temp.length;
        if (length <= 100) {
            for (int i = 0; i < 100; i++) {
                if (i < 100 - length) {
                    b[i] = 0x00;
                } else {
                    b[i] = temp[i - 100 + length];
                }
            }
        }

        for (byte i : b) {
            System.out.print(i + ",");
        }

        //转换
        String c = new String(b);
        System.out.println("string: " + c);

        //读写
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("E:\\HW.dat");
            out.write(b);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream in = null;
        byte[] r = new byte[100];
        try {
            in = new FileInputStream("E:\\HW.dat");
            in.read(r);
            String s = new String(r);
            System.out.println("out: " + new String(r).trim());
        } catch (IOException e) {
            e.printStackTrace();
        }


        //合并
        byte[] cc = byteMerger(aa, b);
        System.out.println("cc: " + cc.length);

        byte[] byte_3 = new byte[20];
        System.arraycopy(aa, 0, byte_3, 0, aa.length);
        System.arraycopy(b, 0, byte_3, aa.length, b.length);
        System.out.println("byte_3: " + new String(byte_3));
        String d = "987654321";
        byte[] dd = d.getBytes();
        String a2 = "7777777";
        byte[] aa2 = a2.getBytes();
        System.arraycopy(dd, 0, byte_3, 0, dd.length);
        System.arraycopy(aa2, 0, byte_3, dd.length, aa2.length);
        System.out.println("byte_3: " + new String(byte_3));
    }


    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }
}
