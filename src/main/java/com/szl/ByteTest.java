package com.szl;

/**
 * Created by zsc on 2018/4/17.
 * string = new byte[10000];
 * byte字节数少
 * 
 */
public class ByteTest {
    static byte[] bytes = new byte[2];
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            System.out.println(bytes[i]);
        }
        String aa = new String(bytes);
        System.out.println(aa);
        bytes[0] = 97;
        bytes[1] = 98;
        String a = new String(bytes);
        System.out.println(a);

        String s = "我";
        byte[] sb = s.getBytes();
        for (int i = 0; i < sb.length; i++) {
            System.out.println(sb[i]);
        }
        s = new String(sb);
        System.out.println(s);

    }
}
