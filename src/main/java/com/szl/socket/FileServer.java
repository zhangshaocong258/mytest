package com.szl.socket;

/**
 * Created by zsc on 2016/9/8.
 */

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class FileServer {
    /**
     * 工程main方法
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            final ServerSocket server = new ServerSocket(48123);
            Thread th = new Thread(new Runnable() {
                public void run() {
                    int count = 1;
                    while (true) {
                        try {
                            System.out.println("开始监听...");
                            /*
                             * 如果没有访问它会自动等待
                             */
                            Socket socket = server.accept();
                            System.out.println("有链接");

//                            //同时接收和发送
//                            receiveFile(socket, count);
//                            sendFile(socket, count);

//                            //同时接收
//                            receiveFile(socket, count);


                            //同时发送
                            sendFile(socket, count);

                            count += 1;
                        } catch (Exception e) {
                            System.out.println("服务器异常");
                            e.printStackTrace();
                        }
                    }
                }
            });
            th.run(); //启动线程运行
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 接收文件方法
     *
     * @param socket
     * @throws IOException
     */
    public static void receiveFile(Socket socket, int count) throws IOException {
        Runnable runnable = new Runnable() {
            DataInputStream dis = null;
            FileOutputStream fos = null;

            @Override
            public void run() {
                try {
                    byte[] inputByte = null;
                    int length = 0;
                    String filePath = "E:/server/" + "server" + count + ".iso";
                    dis = new DataInputStream(socket.getInputStream());
                    File f = new File("E:/server");
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

    public static void sendFile(Socket socket, int count) {
        Runnable runnable = new Runnable() {
            boolean bool;//判断成功失败
            DataOutputStream dos = null;
            FileInputStream fis = null;

            @Override
            public void run() {
                try {
                    int length = 0;
                    double sumL = 0;
                    byte[] sendBytes = null;
//                    Socket socket = null;

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
                } finally {
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

}
