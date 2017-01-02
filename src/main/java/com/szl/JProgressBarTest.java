package com.szl;

import javax.swing.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * Created by zsc on 2016/8/24.
 * 进度条显示
 */
public class JProgressBarTest extends JFrame {
    private static final long serialVersionUID = 6823378424400186364L;

    static JProgressBarTest frame;

    public JProgressBarTest() {
        JLabel label = new JLabel("欢迎使用在线升级功能！");
        JProgressBar progressBar = new JProgressBar();
        JButton button = new JButton("完成");
        button.setEnabled(false);
        Container container = getContentPane();
        container.setLayout(new GridLayout(3, 1));
        JPanel panel1= new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel2= new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel3= new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel1.add(label);
        panel2.add(progressBar);
        panel3.add(button);
        container.add(panel1);
        container.add(panel2);
        container.add(panel3);

        progressBar.setStringPainted(true);
//        progressBar.setString("升级进行中...");
//      progressBar.setIndeterminate(true);
        new Progress(progressBar, button).start();

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }

        });
    }

    private class Progress extends Thread {
        JProgressBar progressBar;
        JButton button;
        int[] progressValues = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] progressValues2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};


        Progress(JProgressBar progressBar, JButton button) {
            this.progressBar = progressBar;
            this.button = button;
        }

        public void run() {
            progressBar.setMaximum(progressValues.length * 2);
            for (int i = 0; i < progressValues.length; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setValue(progressValues[i]);
            }

            //第一个任务执行完后，将第二个任务初始设为50%
            progressBar.setMaximum(progressValues2.length * 2);
            progressBar.setString("50%");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progressBar.setValue(progressValues2.length);

            for (int i = 0; i < progressValues2.length; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setValue(progressValues2[i] + progressValues2.length);
            }


            progressBar.setIndeterminate(false);
//            progressBar.setString("100%");
            button.setEnabled(true);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        frame = new JProgressBarTest();
        frame.setTitle("使用进度条");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
}
