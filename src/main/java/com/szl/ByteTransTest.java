package com.szl;

/**
 * Created by zsc on 2016/11/23.
 * 字节转int等
 * 主要用了移位，与
 */
public class ByteTransTest {

    public static void main(String[] args) {
        byte[] a1 = new byte[1];
        a1[0] = 0x11;
        System.out.println(a1[0]);


        int a2 = 68492;
        byte[] b = new byte[4];
        b = intToByte(a2);
        reverseByteArray(b);
        System.out.println(byteArrayToInt(b, 0));//输出68492
    }

    public static byte[] intToByte(int number) {
        byte[] abyte = new byte[4];
        // "&" 与（AND），对两个整型操作数中对应位执行布尔代数，两个位都为1时输出1，否则0。
        abyte[0] = (byte) (0xff & number);
        // ">>"右移位，若为正数则高位补0，若为负数则高位补1
        abyte[1] = (byte) ((0xff00 & number) >> 8);
        abyte[2] = (byte) ((0xff0000 & number) >> 16);
        abyte[3] = (byte) ((0xff000000 & number) >> 24);
        return abyte;
    }

    //移位，相当于乘以多少倍
    private static int byteArrayToInt(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }

    private static long byteArrayToLong(byte[] b, int offset) {
        long value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            long temp = b[i + offset] & 0x00000000000000FF;
            value += temp << shift;
        }
        if (value < 0) {
            System.out.println("here");
        }
        return value;
    }

    private static short byteArrayToShort(byte[] b, int offset) {
        short value = 0;
        for (int i = 0; i < 2; i++) {
            int shift = (2 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }

    private static int byteArrayToPort(byte[] b, int offset) {
        int port = 0;
        for (int i = 0; i < 2; i++) {
            int shift = (2 - i - 1) * 8;
            port += (b[i + offset] & 0x000000FF) << shift;
        }
        return port;
    }

    private static String byteArrayToIP(byte[] b, StringBuilder sb) {
        String str;
        for (int i = 0; i < 3; i++) {
            sb.append(b[i] + ".");
        }
        sb.append(b[3]);
        str = new String(sb.toString()).intern();//指向常量池中的string
        return str;
    }

    /**
     * 反转数组
     *
     * @param arr
     */
    private static void reverseByteArray(byte[] arr) {
        byte temp;
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = temp;
        }
    }
}
