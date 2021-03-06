package com.szl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zsc on 2016/7/7.
 * 读文件 一堆方法
 */
public class ReadFileTools {

    /**
     * execute readByBufferReader spend 444 million sencond!
     * execute readByBufferedInputStreamNoArray spend 27903 million sencond!
     * execute readByBufferedInputStream spend 192 million sencond!
     * execute readByChannel spend 484 million sencond!
     * execute readByChannelMap spend 42 million sencond!
     * execute readByDataInputStream spend 440 million sencond!
     *
     * @param args
     * @throws Exception
     */
    public static final int BUFFSIZE = 5 * 1024 * 1024;
    public static final String root = "C:\\Users\\zsc\\Desktop\\";
    public static final boolean printContext = false;

    public static void main(String[] args) throws Exception {
        String file = root + "aaaa.txt";
        ReadFileTools bi = new ReadFileTools();

        long a = System.currentTimeMillis();
        bi.readByBufferReader(file);
        long b = System.currentTimeMillis();
        System.out.println("时间readByBufferReader：" + (b - a));

//        bi.readByBufferedInputStreamNoArray(file);
        long c = System.currentTimeMillis();
//        System.out.println("时间readByBufferReader：" + (c - b));

        bi.readByBufferedInputStream(file);
        long d = System.currentTimeMillis();
        System.out.println("时间readByBufferedInputStream：" + (d - c));

        bi.readByChannel(file);
        long e = System.currentTimeMillis();
        System.out.println("时间readByChannel：" + (e - d));

        bi.readByChannelMap(file);
        long f = System.currentTimeMillis();
        System.out.println("时间readByChannelMap：" + (f - e));

        bi.readByDataInputStream(file);
        long g = System.currentTimeMillis();
        System.out.println("时间readByDataInputStream：" + (g - f));

    }

    /*
     * execute readBuffer spend 421 million sencond! execute readByte spend
     * 36172 million sencond!
     */
    public String readByBufferReader(String file) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(file)));
            String line;
            long count = 0;
            while ((line = br.readLine()) != null) {
                if (printContext) {
                    System.out.println(line);
                }

                sb.append(line);//可能会溢出报错
                count += line.length();
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public void readByDataInputStream(String file) throws Exception {

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(new ReadFileTools().readByBufferReader(file).getBytes()));
        while (dis.available() > 0) {
            char c = (char) dis.read();
            if (printContext) {
                System.out.println(c);
            }
        }
    }

    //this method not use byte array to get byte
    public String readByBufferedInputStreamNoArray(String file) {
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(new File(file)));
            while (is.available() > 0) {
                char c = (char) is.read();
                if (printContext) {
                    System.out.println(c);
                }
            }
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //use byte array to get bytes from file
    public void readByBufferedInputStream(String file) throws Exception {
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[BUFFSIZE];
        while (input.available() > 0) {
            input.read(bytes);
        }
        input.close();
    }

    //use file channel to get byte from file
    public void readByChannel(String file) throws Exception {

        FileChannel in = new FileInputStream(file).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFSIZE);
        while (in.read(buffer) != -1) {
            buffer.flip(); // Prepare for writing
            if (printContext) {
                System.out.println(buffer.getChar());
            }
            buffer.clear(); // Prepare for reading
        }
        in.close();
    }

    //use MappedByteBuffer to read byte from file
    public void readByChannelMap(String file) throws Exception {
        System.out.println("进入");
        FileChannel fc = new FileInputStream(new File(file)).getChannel();
        CharBuffer cb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asCharBuffer();
        char c;
        while (cb.hasRemaining())
            c = cb.get();
        if (printContext) {
            System.out.println(c);
        }
        fc.close();
    }

    public void readByChannelMap2(String file) throws Exception {
        System.out.println("进入");
        FileChannel fc = new FileInputStream(new File(file)).getChannel();
        long length = fc.size();
        MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        System.out.println("length: " + length);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (int) length; i++) {
            if (buffer.get(i) == 10) {//判断遇到换行符，处理此行数据
                System.out.println(sb.toString());
                String str[] = sb.toString().split(",");
                for(int k=0;k<str.length;k++)
                    System.out.println(str[k]);
                sb.delete(0, sb.length());
            } else if (i == length - 1) {//判断到了最后一行，处理此行数据
                sb.append((char) buffer.get(i));
                System.out.println(sb.toString());
//                fileManager.excuteContent(sb.toString());
            } else {//拼接成一行数据
                sb.append((char) buffer.get(i));
            }
        }
        buffer = null;
        sb = null;

//        char c;
//        while (cb.hasRemaining())
//            c = cb.get();
//        if (printContext) {
//            System.out.println(c);
//        }
        fc.close();
    }


    public void copyFileByChannel(String file, String file2) throws Exception {

        FileChannel in = new FileInputStream(file).getChannel();
        FileChannel out = new FileOutputStream(file2).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFSIZE);
        while (in.read(buffer) != -1) {
            buffer.flip(); // Prepare for writing
            out.write(buffer);
            buffer.clear(); // Prepare for reading
        }
    }

    public void test() {
        System.out.println("test");
    }

    public void copyFile(String source, String dest) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File(source)));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dest)));
        String temp;
        while ((temp = br.readLine()) != null) {
            bw.write(temp + "\n");
        }
    }

    public void storingAndRecoveringData(String file) throws Exception {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        dos.writeBoolean(false);
        dos.writeByte(10);
        dos.writeDouble(1213654);
        dos.writeUTF("aihua");
        dos.close();
        System.out.println(dis.readBoolean());
        System.out.println(dis.readByte());
        System.out.println(dis.readDouble());
        System.out.println(dis.readUTF());
        dis.close();

    }

    public void doCopyFile(String src, String dest) throws IOException {
        File srcFile = new File(src);
        File destFile = new File(dest);
        if (destFile.exists()) {
            boolean d = destFile.delete();

            if (d) {
                System.out.print("删除成功！");
            } else {
                System.out.print("删除失败！");
            }
        }
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(srcFile));
        try {
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(destFile));
            try {
                byte[] buffer = new byte[4096];
                int n = 0;
                while (-1 != (n = input.read(buffer))) {
                    output.write(buffer, 0, n);
                }
                System.out.println("Copy Successful::" + dest);
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ioe) {
                System.out.println("failed src file:" + src + " reason:" + ioe.getMessage());
            }
        }
    }

}
