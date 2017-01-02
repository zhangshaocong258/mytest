package com.szl;

import java.io.IOException;

/**
 * Created by zsc on 2016/4/23.
 * string 到 ascii转换
 */
public class StringToAscii {
    public static void main(String[] args) throws IOException {
        System.out.println(stringToAscii("9" + "\r"));
    }

    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]).append(",");
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();
    }
}
