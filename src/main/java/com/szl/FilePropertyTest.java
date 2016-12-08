package com.szl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zsc on 2016/11/10.
 */
public class FilePropertyTest {

    private static ArrayList<File> fileList = new ArrayList<File>();
    public static void main(String[] args) {
        File file = new File("E:\\0.txt");
        String folder = "E:\\multiPath";
//        FilePropertyTest.modifyProperty(file);
//        FilePropertyTest.modifyTimeLastChange(file);
        FilePropertyTest.getFileList(folder);
        FilePropertyTest.modifyPcapTime(fileList);

    }

    //修改只读属性
    private static void modifyProperty(File file) {
        if (file.canWrite()) {
            file.setWritable(false);
        }
    }

    //修改最后修改时间属性
    private static void modifyTimeLastChange(File file) {
        Date fileTime = new Date(file.lastModified());
        System.out.println("fileTime: " + fileTime);
        System.out.println("cccc: " + System.currentTimeMillis());

        file.setLastModified(System.currentTimeMillis());
    }

    //设置最后修改时间
    private static void modifyPcapTime(ArrayList<File> fileList) {
        for (int i = 0; i < fileList.size(); i++) {
//            fileList.get(i).setLastModified(1478012400000l + 60000 * i);//2016/11/1 23:00:00按分钟加，后面加3个0即可
            fileList.get(i).setLastModified(1478023200000l + 60000 * i);//2016/11/2 2:00:00按分钟加，后面加3个0即可
        }
    }

    //按长度修改最后修改时间
    private static void getFileList(String fpath) {
        File ff = new File(fpath);
            if (ff.isFile() && ff.getName().length() == 23) {
                fileList.add(ff);
            } else if (ff.isDirectory()) {
                File[] files = ff.listFiles();
                for (File f : files) {
                    String path = f.getPath();
                    getFileList(f.getAbsolutePath());
                }
            }
    }
}
