package com.szl;

/**
 * Created by zsc on 2016/5/6.
 * 打开文件目录
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FileChooser extends JFrame implements ActionListener{
    JButton open=null;
    public static void main(String[] args) {
        new FileChooser();
    }
    public FileChooser(){
        open=new JButton("open");
        this.add(open);
        this.setBounds(400, 200, 100, 100);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        open.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
//        jfc.showDialog(new JLabel(), "选择1");
        int ret = jfc.showOpenDialog(null);

        //打开和取消操作，==是打开
        if (ret == JFileChooser.APPROVE_OPTION)

        {
            long beginTime = System.currentTimeMillis();
            File file = jfc.getSelectedFile();
            //输出文件大小
            long l = file.length();
            System.out.println(l);
            String path = jfc.getSelectedFile().getAbsolutePath();
            String folderName = jfc.getSelectedFile().getName();
            int index = path.length() - folderName.length();
            String subfolder = path.substring(index);
            System.out.println(path);
            System.out.println(folderName);
            System.out.println(subfolder);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(beginTime);
            System.out.println(endTime);
            System.out.println(endTime - beginTime);

        }
        File file=jfc.getSelectedFile();
        if(file.isDirectory()){
            System.out.println("文件夹:"+file.getAbsolutePath());
        }else if(file.isFile()){
            System.out.println("文件:"+file.getAbsolutePath());
        }
        System.out.println(jfc.getSelectedFile().getName());

    }

}