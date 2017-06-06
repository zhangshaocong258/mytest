package com.szl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsc on 2017/6/6.
 * 遍历文件，找到包含某字符串的文件
 */
public class FindFile {
    private static String input = "E:\\cc";
    private static List<File> fileList = new ArrayList<File>();
    public static void main(String args[])throws Exception  {
        segment();

    }
    private static void segment() throws Exception {
        File file = new File(input);
        getFiles(file);
        for (File f : fileList) {
            executeSeg(f);
        }
    }

    private static void executeSeg(File file) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("Created by ")) {
                System.out.println("结果 " + file.getAbsolutePath());
            }

        }
        br.close();
    }

    //得到所有文件
    private static void getFiles(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    getFiles(f);
                } else {
                    fileList.add(f);
                }
            }
        }
    }
}
