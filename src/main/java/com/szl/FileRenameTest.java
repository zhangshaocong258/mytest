package com.szl;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by zsc on 2016/11/8.
 * 得到filelist，更改文件名、后缀名
 */
public class FileRenameTest {
    private static ArrayList<File> fileList = new ArrayList<File>();

    public static void main(String[] args) {
        String path = "E:\\m";
        FileRenameTest.getFileList(path, "");
        FileRenameTest.modifyTxt(fileList);

    }

    private static void getFileList(String fpath, String type) {
        File ff = new File(fpath);
        if (type.equals("")) {
            if (ff.isFile()) {
                fileList.add(ff);
                System.out.println(ff.getAbsolutePath().substring(0, ff.getAbsolutePath().lastIndexOf(".") + 1));
            } else if (ff.isDirectory()) {
                File[] files = ff.listFiles();
                for (File f : files) {
                    String path = f.getPath();
                    getFileList(f.getAbsolutePath(), type);
                }
            }
        } else {
            if (ff.isFile() && fpath.endsWith(type)) {
                fileList.add(ff);
            } else if (ff.isDirectory()) {
                File[] files = ff.listFiles();
                for (File f : files) {
                    String path = f.getPath();
                    getFileList(f.getAbsolutePath(), type);
                }
            }
        }
    }

    public static void modifyTxt(ArrayList<File> fileList) {
        System.out.println(fileList.size());
        for (int i = 0; i < fileList.size(); i++) {
            if (fileList.get(i).getName().endsWith("pcap")) {
                fileList.get(i).renameTo(new File(fileList.get(i).getAbsolutePath().
                        substring(0, fileList.get(i).getAbsolutePath().lastIndexOf(".") + 1) + "pcap"));

//                fileList.get(i).renameTo(new File(fileList.get(i).getAbsolutePath().
//                        substring(0, fileList.get(i).getAbsolutePath().lastIndexOf("\\") + 1) + "Mul-" +fileList.get(i).getName()  + "pcap"));
            }
        }
    }
}
