package com.szl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zsc on 2016/12/7.
 */
public class MD5Test {
    public static void main(String args[]) throws NoSuchAlgorithmException {
        System.out.println("http://www.zhihu.com/people/zhangjiawei");
        System.out.println(stringMD5("http://www.zhihu.com/people/zhangjiawei"));
    }

    public static String stringMD5(String input) throws NoSuchAlgorithmException {
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");


            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes();

            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);


            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            System.out.println(resultByteArray.toString());
            for (int i = 0; i < resultByteArray.length; i++) {
                System.out.println(resultByteArray[i]);
            }

            System.out.println(bytesToHex(resultByteArray));
            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }



    public static String byteArrayToHex(byte[] byteArray) {

        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };

        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray =new char[byteArray.length * 2];



        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }

        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
    /**
     * 二进制转十六进制
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        //把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            //计算机存的是补码，如10001000，转换为int是-120，转换为16进制是88，
            // 而toHexString返回为无符号整数基数为16的整数参数的字符串表示形式，即10001000的无符号数136，因此要加256
            if(digital < 0) {
                digital += 256;
            }
            if(digital < 16){//小于16说明前4位为0
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }
}
