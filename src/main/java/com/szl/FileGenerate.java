package com.szl;

import java.io.*;

/**
 * Created by zsc on 2016/7/7.
 */
public class FileGenerate {

    public static void main(String[] args) throws IOException{
        generateBigFile();
    }

    private static void generateBigFile() throws IOException {

        long start = System.currentTimeMillis();
        File bigFile = new File("C:\\Users\\zsc\\Desktop\\aaa.txt");
        FileWriter fileWriter = new FileWriter(bigFile);
        for (int i = 0; i < 40000000; i++) {
            fileWriter.write("10.0.13.2,10.0.2.2,49153,9,1290,186666,14-1,38,1" + "\r\n");
        }
        fileWriter.close();
        long end = System.currentTimeMillis();
        System.out.println("生成一个1.86G大文件 aaa.txt , 耗时=" + (end - start));
    }
}
