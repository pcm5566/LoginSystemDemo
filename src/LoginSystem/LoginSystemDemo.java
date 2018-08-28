package LoginSystem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

class ActionHandle {
    private JFrame frame = new JFrame("登录系统");
    private JLabel lab1 = new JLabel("用户名：");
    private JLabel lab2 = new JLabel("密  码：");
    public JLabel infoLab = new JLabel("欢迎登录！");
    public JTextField nameText = new JTextField();
    public JPasswordField passText = new JPasswordField();
    public JButton button1 = new JButton("登录");
    public JButton button2 = new JButton("注册");
    public ActionHandle() {
        Font fnt = new Font("Serief", Font.ITALIC, 12);
        lab1.setFont(fnt);
        lab2.setFont(fnt);
        infoLab.setFont(fnt);
        lab1.setBounds(10, 10, 80, 30);
        lab2.setBounds(20, 50, 80, 30);
        infoLab.setBounds(10, 90, 200, 30);
        nameText.setBounds(100, 10, 200, 30);
        passText.setBounds(100, 50, 200, 30);
        passText.setEchoChar('*');
        button1.setFont(fnt);
        button2.setFont(fnt);
        button1.setBounds(100, 170, 80, 30);
        button2.setBounds(220, 170, 80, 30);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                System.exit(1);
            }
        });
        frame.setLayout(null);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(infoLab);
        frame.add(nameText);
        frame.add(passText);
        frame.add(button1);
        frame.add(button2);
        frame.setFont(fnt);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
public class LoginSystemDemo{
    public static void main(String []args){
        new ActionHandle();
        ActionHandle yu = new ActionHandle();
        yu.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
                final String DBURL = "jdbc:mysql://localhost:3306/mysqltest" +
                        "?useUnicode=true&serverTimezone=GMT&characterEncoding=utf8" +
                        "&useSSL=false&autoReconnect=true&failOverReadOnly=false&maxReconnects=2";
                final String DBUSER = "root";
                final String DBPASS = "12345";
                String accountcheck = yu.nameText.getText();
                String passcheck =new String(yu.passText.getPassword());
                String sql1 = "select * from accountpassword where Account='"+accountcheck+"'";
                try {
                    Class.forName(DBDRIVER);
                }catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                try {
                    Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
                    Statement stat1 = conn.createStatement();
                    ResultSet rs = stat1.executeQuery(sql1);
                    if (rs.next()){
                        if (passcheck.equals(rs.getString("Password"))) {
                            yu.infoLab.setText("登录成功！");
                        }else {
                            yu.infoLab.setText("密码错误！");
                        }
                    }else {
                        yu.infoLab.setText("账号不存在，请注册！");
                    }
                }catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        });
        yu.button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String DBDRIVER2 = "com.mysql.cj.jdbc.Driver";
                final String DBURL2 = "jdbc:mysql://localhost:3306/mysqltest" +
                        "?useUnicode=true&serverTimezone=GMT&characterEncoding=utf8" +
                        "&useSSL=false&autoReconnect=true&failOverReadOnly=false&maxReconnects=2";
                final String DBUSER2 = "root";
                final String DBPASS2 = "12345";
                String accountcheck2 = yu.nameText.getText();
                String passcheck2 =new String(yu.passText.getPassword());
                String sql2 = "select * from accountpassword where Account='"+accountcheck2+"'";
                String sql3 = "insert into accountpassword(Account, Password)" +
                        " values ('"+accountcheck2+"','"+passcheck2+"')";
                try {
                    Class.forName(DBDRIVER2);
                }catch (ClassNotFoundException e3) {
                    e3.printStackTrace();
                }
                try {
                    Connection conn2 = DriverManager.getConnection(DBURL2, DBUSER2, DBPASS2);
                    Statement stat2 = conn2.createStatement();
                    ResultSet rs2 = stat2.executeQuery(sql2);
                    if (rs2.next()) {
                        yu.infoLab.setText("账号已存在！");
                    }else {
                        stat2.executeUpdate(sql3);
                        yu.infoLab.setText("注册成功！");
                    }
                }catch (SQLException e4) {
                    e4.printStackTrace();
                }
            }
        });
    }
}