package com.szl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by zsc on 2016/7/7.
 */
public class BigFileReaderTest {


    private static String filePathName = "C:\\Users\\zsc\\Desktop\\tttt.txt";
    public static final int BUFFSIZE =  5 * 1024 * 1024;

    public static void main(String[] args) throws IOException {

        //generateBigFile();
        long a = System.currentTimeMillis();
        readFile1();
        long b = System.currentTimeMillis();
        System.out.println("时间1：" + (b - a));

        readFile2();
        long c = System.currentTimeMillis();
        System.out.println("时间2：" + (c - b));

        readFile3();
        long d = System.currentTimeMillis();
        System.out.println("时间3：" + (d - c));

        readFile4();
        long e = System.currentTimeMillis();
        System.out.println("时间5：" + (e - d));

    }

    /**
     * 读大文件
     * BufferedReader + char[]
     * @throws IOException
     */
    public static void readFile1() throws IOException{

        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(filePathName));

        char[] buff = new char[BUFFSIZE];
        int len = -1;
        while( (len = br.read(buff)) != -1 ){
            //System.out.print(new String(buff, 0, len));
        }
        br.close();
        long end = System.currentTimeMillis();
        System.out.println("读大文件   BufferedReader + char[], 耗时="+(end-start));
    }


    /**
     * 读大文件
     * FileChannel + ByteBuffer
     * @throws IOException
     */
    private static void readFile2() throws IOException{

        long start = System.currentTimeMillis();
        FileChannel fc = new FileInputStream(filePathName).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFSIZE);

        while(fc.read(buffer) != -1){

            buffer.flip();
            //System.out.print(Charset.forName("UTF-8").newDecoder().decode(buffer));;
            buffer.clear();
        }
        fc.close();
        long end = System.currentTimeMillis();
        System.out.println("读大文件  FileChannel + ByteBuffer, 耗时="+(end-start));
    }

    /**
     * 读大文件
     * BufferedReader + CharBuffer
     * @throws IOException
     */
    public static void readFile3() throws IOException{

        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(filePathName));
        CharBuffer buff = CharBuffer.allocate(BUFFSIZE);
        while( br.read(buff) != -1 ){
            buff.flip();
            //System.out.print(buff);
            buff.clear();
        }
        br.close();
        long end = System.currentTimeMillis();
        System.out.println("读大文件   BufferedReader + CharBuffer, 耗时="+(end-start));
    }


    /**
     * 读大文件
     * 自带的读所有行
     * @throws IOException
     */
    public static void readFile4() throws IOException {
        int count = 0;
        List<String> lines = Files.readAllLines(Paths.get(filePathName),
                    Charset.defaultCharset());
            for (String curLine : lines) {
                count++;
            }
    }

    /**
     * 读大文件
     * map
     * @throws IOException
     */
//    public static void readFile4() throws IOException{
//
//      long start = System.currentTimeMillis();
//      FileChannel fc = new FileInputStream(filePathName).getChannel();
//      int begin = 0, size = 1024;
//
//      MappedByteBuffer mappedByteBuffer =
//         fc.map(FileChannel.MapMode.READ_ONLY, begin, size);
//
//      while(mappedByteBuffer.capacity() > 0){
//
//          begin += mappedByteBuffer.capacity();
//          mappedByteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, begin, size);
//
//      }
//      long end = System.currentTimeMillis();
//      System.out.println("nio读大文件   FileChannel + MappedByteBuffer, 耗时="+(end-start));
//    }

    /**
     * 生成一个大文件 a.txt
     * @throws IOException
     */
    private static void generateBigFile() throws IOException{

        long start = System.currentTimeMillis();
        File bigFile = new File(filePathName);
        FileWriter fileWriter = new FileWriter(bigFile);
        for(int i=0;i<100000000;i++){
            fileWriter.write(Math.random()+"\r\n");
        }
        fileWriter.close();
        long end = System.currentTimeMillis();
        System.out.println("生成一个大文件 a.txt , 耗时="+(end-start));
    }
}
