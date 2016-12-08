package com.szl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by zsc on 2016/11/28.
 */
public class ScannerTest {
    public static void main(String[] args) throws IOException{
        /**
         * e.g.1
         */
//        Scanner s1 = new Scanner(System.in);
//        System.out.println("请输入字符串：");
//        while (true) {
//            String line = s1.nextLine();
//            if (line.equals("exit")) break;
//            System.out.println(">>>" + line);
//        }


        /**
         * e.g.2
         */
        Scanner s2 = new Scanner("123 asdf sd 45 789 sdf asdfl,sdf.sdfl,asdf asdfkl    las");
        s2.useDelimiter(" |,|\\.");
        while (s2.hasNext()) {
            System.out.println(s2.next());
        }


        /**
         * e.g.3
         */
//        InputStream in = new FileInputStream(new File("E:\\test.txt"));
//        Scanner s3 = new Scanner(in);
//        while(s3.hasNextLine()){
//            System.out.println(s3.nextLine());
//        }
    }
}
