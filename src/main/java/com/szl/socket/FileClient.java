package com.szl.socket;

/**
 * Created by zsc on 2016/9/8.
 * 同时接收文件 客户端
 */

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class FileClient {
    /**
     * 程序main方法
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//        //同时接收和发送
//        Socket socket = new Socket();
//        socket.connect(new InetSocketAddress("127.0.0.1", 48123));
//        sendFile(socket, 2);
//        receiveFile(socket, 1);

//        //同时发送
//        sendFile2(1);
//        sendFile2(2);

        //同时接收
        receiveFile2(1);
        receiveFile2(2);

    }

    public static void sendFile(final Socket socket, final int count) {
        Runnable runnable = new Runnable() {
            boolean bool;
            DataOutputStream dos = null;
            FileInputStream fis = null;
//            Socket socket = null;
            @Override
            public void run() {
                try {
                    int length = 0;
                    double sumL = 0;
                    byte[] sendBytes = null;
                    bool = false;

                    File file = new File("E:/" + count + ".iso"); //要传输的文件路径
                    long l = file.length();
//                    socket = new Socket();
//                    socket.connect(new InetSocketAddress("127.0.0.1", 48123));
                    dos = new DataOutputStream(socket.getOutputStream());
                    fis = new FileInputStream(file);
                    sendBytes = new byte[102400];
                    while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
                        sumL += length;
//                        System.out.println("已传输：" + ((sumL / l) * 100) + "%");
                        dos.write(sendBytes, 0, length);
                        dos.flush();
                    }
                    //虽然数据类型不同，但JAVA会自动转换成相同数据类型后在做比较
                    if (sumL == l) {
                        bool = true;
                    }
                } catch (Exception e) {
                    System.out.println("客户端文件传输异常");
                    bool = false;
                    e.printStackTrace();
                }
                finally {
//                    try {
//                        if (dos != null)
//                            dos.close();
//                        if (fis != null)
//                            fis.close();
//                        if (socket != null)
//                            socket.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
                System.out.println(bool ? "成功" : "失败");
            }
        };

        new Thread(runnable).start();

    }

    public static void sendFile2(final int count) {
        Runnable runnable = new Runnable() {
            boolean bool;
            DataOutputStream dos = null;
            FileInputStream fis = null;
            Socket socket = null;
            @Override
            public void run() {
                try {
                    int length = 0;
                    double sumL = 0;
                    byte[] sendBytes = null;
                    bool = false;

                    File file = new File("E:/" + count + ".iso"); //要传输的文件路径
                    long l = file.length();
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("127.0.0.1", 48123));
                    dos = new DataOutputStream(socket.getOutputStream());
                    fis = new FileInputStream(file);
                    sendBytes = new byte[102400];
                    while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
                        sumL += length;
//                        System.out.println("已传输：" + ((sumL / l) * 100) + "%");
                        dos.write(sendBytes, 0, length);
                        dos.flush();
                    }
                    //虽然数据类型不同，但JAVA会自动转换成相同数据类型后在做比较
                    if (sumL == l) {
                        bool = true;
                    }
                } catch (Exception e) {
                    System.out.println("客户端文件传输异常");
                    bool = false;
                    e.printStackTrace();
                }
                finally {
//                    try {
//                        if (dos != null)
//                            dos.close();
//                        if (fis != null)
//                            fis.close();
//                        if (socket != null)
//                            socket.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
                System.out.println(bool ? "成功" : "失败");
            }
        };

        new Thread(runnable).start();

    }


    public static void receiveFile(final Socket socket, final int count) throws IOException {
        Runnable runnable = new Runnable() {
            DataInputStream dis = null;
            FileOutputStream fos = null;

            @Override
            public void run() {
                try {
                    byte[] inputByte = null;
                    int length = 0;
//                    Socket socket = new Socket();
//                    socket.connect(new InetSocketAddress("127.0.0.1", 48123));
                    String filePath = "E:/client/" + "client" + count + ".iso";
                    dis = new DataInputStream(socket.getInputStream());
                    File f = new File("E:/client");
                    if (!f.exists()) {
                        f.mkdir();
                    }
                    /*
                    * 文件存储位置
                     */
                    fos = new FileOutputStream(new File(filePath));
                    inputByte = new byte[102400];
                    System.out.println("开始接收数据...");
                    while ((length = dis.read(inputByte, 0, inputByte.length)) > 0) {
                        fos.write(inputByte, 0, length);
                        fos.flush();
                    }
                    System.out.println("完成接收：" + filePath);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    try {
//                        if (fos != null)
//                            fos.close();
//                        if (dis != null)
//                            dis.close();
//                        if (socket != null)
//                            socket.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    public static void receiveFile2(final int count) throws IOException {
        Runnable runnable = new Runnable() {
            DataInputStream dis = null;
            FileOutputStream fos = null;
            Socket socket = null;

            @Override
            public void run() {
                try {
                    byte[] inputByte = null;
                    int length = 0;
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("127.0.0.1", 48123));
                    String filePath = "E:/client/" + "client" + count + ".iso";
                    dis = new DataInputStream(socket.getInputStream());
                    File f = new File("E:/client");
                    if (!f.exists()) {
                        f.mkdir();
                    }
                    /*
                    * 文件存储位置
                     */
                    fos = new FileOutputStream(new File(filePath));
                    inputByte = new byte[102400];
                    System.out.println("开始接收数据...");
                    while ((length = dis.read(inputByte, 0, inputByte.length)) > 0) {
                        fos.write(inputByte, 0, length);
                        fos.flush();
                    }
                    System.out.println("完成接收：" + filePath);//无法执行到这一步，即无法停止

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    try {
//                        if (fos != null)
//                            fos.close();
//                        if (dis != null)
//                            dis.close();
//                        if (socket != null)
//                            socket.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        };
        new Thread(runnable).start();
    }
}
