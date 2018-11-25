package com.Duchess;

import com.Duchess.Notebook;
import com.Duchess.zhuce;
import java.awt.event.*;

import java.awt.*;
import javax.swing.*;

public class LoginView {

    public static void main(String[] args) {

        final JFrame frame = new JFrame("登录界面");
        frame.setLayout(null);



        JPanel panel2 = new JPanel();
        JLabel nameLabel = new JLabel("用户名:");
        final JTextField nameField = new JTextField(12);
        panel2.add(nameLabel);
        panel2.add(nameField);
        frame.add(panel2);
        panel2.setBounds(100,70,200,30);
        // 登录按钮事件


        JPanel panel3 = new JPanel();
        JLabel passLabel = new JLabel("密码    :");
        final JPasswordField passField = new JPasswordField(12);
        panel3.add(passLabel);
        panel3.add(passField);
        frame.add(panel3);
        panel3.setBounds(100,130,200,30);




        JPanel panel5 = new JPanel();
        JButton arrive = new JButton("登录");
        arrive.setBackground(Color.GRAY);
        panel5.add(arrive);
        frame.add(panel5);
        panel5.setBounds(170,200,70,40);



        arrive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeber=nameField.getText().toString();
                String pwd=passField.getText().toString();
                int i;
                boolean flag=false;	//用于记录登录是否成功的标记变量
                String nickname="";
                for(i=0;i<com.Duchess.zhuce.user.length;i++)
                {
                    if(numeber.equals(com.Duchess.zhuce.user[i][0])){
                        if(pwd.equals(com.Duchess.zhuce.user[i][1])){
                            nickname=com.Duchess.zhuce.user[i][2];
                            flag=true;
                            break;
                        }
                    }

                }
                if(flag)
                {
                    nameField.requestFocus();
                    // System.out.println(name);

                    JOptionPane.showMessageDialog(frame, "密码正确，允许进入....");
                    new Notebook().init();
                    frame.setVisible(false);
                }else {
                    nameField.setText("");
                    passField.setText("");
                    nameField.requestFocus();
                    JOptionPane.showMessageDialog(null, "密码错误，请重新输入....");


                }


            }
        });

        ImageIcon img1 = new ImageIcon("d7991157b4fd467b2c7dade342ec10d2.jpg");
        JLabel jl_bg1 = new JLabel(img1);
        jl_bg1.setBounds(0,00,420,350);
        frame.add(jl_bg1);





        initFrame(frame, 420, 350);
        frame.setVisible(true);
        frame.setResizable(false);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public static void initFrame(JFrame frame,int width,int height){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension  d = toolkit.getScreenSize();
        int x = (int) d.getWidth();
        int y = (int) d.getHeight();

        frame.setBounds((x-width)/2, (y-height)/2, width, height);

    }

}
