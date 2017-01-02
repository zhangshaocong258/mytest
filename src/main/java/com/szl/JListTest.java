package com.szl;

/**
 * Created by zsc on 2016/3/23.
 * 下拉列表
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;

public class JListTest{

    public static void main(String[] args) throws Exception{
        JFrame jFrame = new JFrame();
        JList jl1;
        JButton jButton1;
        JButton jButton2;
        JButton jButton3;
        JButton jButton4;

        JScrollPane mb1;

        Vector<String> listValues = new Vector<String>();
        DefaultListModel listModel = new DefaultListModel();
        jl1 = new JList(listModel);
        mb1 = new JScrollPane(jl1);
        jButton1 = new JButton("aaa");
        jButton2 = new JButton("bbb");
        jButton3 = new JButton("ccc");
        jButton4 = new JButton("ddd");

        Box box = Box.createHorizontalBox();
        Box right = Box.createVerticalBox();

        box.add(mb1);
        right.add(jButton1);
        right.add(jButton2);
        right.add(jButton3);
        right.add(jButton4);
        box.add(right);

        jFrame.add(box);

        jFrame.setTitle("下拉列表");
        jFrame.setSize(250, 300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation((screenSize.width - jFrame.getWidth()) / 2,
                (screenSize.height - jFrame.getHeight()) / 2);
        jFrame.setResizable(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true); // 设置显示窗体

        Thread.sleep(2000);
        listModel.addElement("bb");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.addElement("aa");
            }
        });
    }

}


