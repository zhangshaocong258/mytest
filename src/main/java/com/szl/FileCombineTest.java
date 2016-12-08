package com.szl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsc on 2016/8/9.
 */
public class FileCombineTest {

    public static void main(String[] args) {
        FileCombineTest fileCombineTest = new FileCombineTest();
        fileCombineTest.test();
    }
    public void test() {
        String dirPath = "D:\\test\\";
        File dir = new File(dirPath);
        ArrayList<File> files = new ArrayList<File>();
        for (File tmpFile : dir.listFiles()) {
            files.add(tmpFile);
        }
        try {
            //abc_1.txt,abc_2.txt --> abc.txt
            combineFiles(files, dirPath, "10.0.1.2_10.0.2.2.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 合并指定目录下的所有 文件
     *
     * @param files
     * @param dirPath
     * @param saveFileName
     * @throws IOException
     */
    public void combineFiles(List<File> files, String dirPath, String saveFileName)
            throws IOException {

        File outputFile = new File(dirPath + saveFileName);

        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        FileChannel outChannel = new FileOutputStream(outputFile).getChannel();
        FileChannel inChannel;
        for (File file : files) {

            inChannel = new FileInputStream(file).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);

            inChannel.close();
        }
        outChannel.close();
    }
}
