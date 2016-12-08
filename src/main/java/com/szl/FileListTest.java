package com.szl;

/**
 * Created by zsc on 2016/5/6.
 */

import java.io.File;
import java.util.ArrayList;

public class FileListTest {
    private static ArrayList<String> filelist = new ArrayList<String>();

    public static void main(String[] args) throws Exception {

        String filePath = "D:\\文件库";
        getFiles(filePath);
    }

    /**
     * 通过递归得到某一路径下所有的目录及其文件
     */
    static void getFiles(String filePath) {
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
      /**
       * 递归调用
       */
                getFiles(file.getAbsolutePath());
                filelist.add(file.getAbsolutePath());
                System.out.println("显示" + filePath + "下所有子目录及其文件" + file.getAbsolutePath());
            } else {
                System.out.println("显示" + filePath + "下所有子目录" + file.getAbsolutePath());
            }
        }
    }
}
