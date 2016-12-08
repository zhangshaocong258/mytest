package com.szl;
/**
 * Created by zsc on 2016/3/23.
 */

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;

public class UserLogin extends JFrame {
    /*定义用户名和密码*/
    private String userName, password;
    private File file1, file2;
    /*声明组件*/
    private JPanel mb1, mb2, mb3, mb4, mb5;
    private JLabel jl1, jl2, jl3, jl4;
    private JTextField jt1;
    private JPasswordField password1;
    private JButton an1, an2;

    public static void main(String[] args) {
        UserLogin User = new UserLogin(); //创建本类对象
        User.setVisible(true); //设置窗体显示
    }

    public UserLogin() {
/*转换容器*/
        Container c = this.getContentPane();
        mb1 = (JPanel) c;
/*创建组件*/
        URL url = UserLogin.class.getResource("");
        Icon icon = new ImageIcon(url);
        jl1 = new JLabel(icon);
        URL jfurl = UserLogin.class.getResource("");
        Icon icon1 = new ImageIcon(jfurl);
        mb2 = new JPanel();
        mb3 = new JPanel();
        mb4 = new JPanel();
        mb5 = new JPanel();
        jl2 = new JLabel("用 户 登 录");
        jl3 = new JLabel("用户名：");
        jt1 = new JTextField(10);
        jl4 = new JLabel("密 码：");
        password1 = new JPasswordField(10);
        an1 = new JButton("登录");
        an2 = new JButton("重置");
/*设置组件属性*/
        mb1.setOpaque(true);
        mb2.setOpaque(true);
        mb3.setOpaque(false);
        mb4.setOpaque(false);
        mb5.setOpaque(false);
        jl2.setFont(new Font(null, Font.BOLD, 20));
        jl3.setFont(new Font(null, Font.PLAIN, 16));
        jl4.setFont(new Font(null, Font.PLAIN, 16));
        jt1.setToolTipText("请输入您的姓名！");
        password1.setToolTipText("输入的内容必须是6~17字母、数字和下划线！");
        password1.setEchoChar('*');
        jl1.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        mb1.setLayout(new GridLayout(4, 1));
/*为组件添加事件*/
        password1.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                if ((password1.getPassword().length == 0) && (jt1.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "用户名和密码不能为空！");
                } else if (password1.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "密码不能为空！");
                } else if (jt1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空！");
                }
            }

            public void focusGained(FocusEvent e) {
            }
        });
        an1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                file1 = new File("User.txt");
                file2 = new File("Password.txt");
                if (file1.exists() && file2.exists()) {
                    this.proof();
                } else {
                    try {
                        file1.createNewFile();
                        file2.createNewFile();
                        this.proof();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            private void proof() {
                try {
                    FileReader in1 = new FileReader(file1);
                    BufferedReader buin1 = new BufferedReader(in1);
                    FileReader in2 = new FileReader(file2);
                    BufferedReader buin2 = new BufferedReader(in2);
                    String str1 = null;
                    String str2 = null;
                    while ((str1 = buin1.readLine()) != null && (str2 = buin2.readLine()) != null) {
                        userName = jt1.getText();
                        password = new String(password1.getPassword(), 0, password1.getPassword().length);
                        if (str1.equals(userName) && str2.equals(password)) {
                            JOptionPane.showMessageDialog(null, "登录成功!");
                            return;
                        }
                    }
                    if ((password1.getPassword().length != 0) && !(jt1.getText().equals(""))) {
                        JOptionPane.showMessageDialog(null, "用户不存在或输入错误！");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        an2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jt1.setText(null);
                password1.setText(null);
                jt1.requestFocus();
            }
        });
/*添加组件*/
        mb2.add(jl2);
        mb3.add(jl3);
        mb3.add(jt1);
        mb4.add(jl4);
        mb4.add(password1);
        mb5.add(an1);
        mb5.add(an2);
        mb1.add(mb2);
        mb1.add(mb3);
        mb1.add(mb4);
        mb1.add(mb5);
/*设置窗体属性*/
        this.setTitle("用户登录");
        this.setSize(300, 200);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getLayeredPane().add(jl1, new Integer(Integer.MIN_VALUE));
        System.out.println("test");
    }
}
