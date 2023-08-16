package shopping.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import shopping.model.User;


public class LoginGui {
	public LoginGui() {
		initInterGui();
	}
    //  登录界面GUI
	public void initInterGui() {
		JFrame inter = new JFrame("请登录");

		inter.setLayout(null); // 清除布局函数
		inter.setResizable(false); // 设置窗体大小不可变
		inter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		inter.setLayout(new BorderLayout()); // 新建BorderLayout布局

		// 背景
		JPanel panel0 = new JPanel(new FlowLayout());
		ImageIcon icon = new ImageIcon("image/log.png");
		icon.setImage(icon.getImage().getScaledInstance(325, 160, 0));
		JLabel label = new JLabel(icon);
		panel0.add(label);
		inter.add(panel0, BorderLayout.NORTH);

		// 账号密码框
		JPanel panel1 = new JPanel(new GridLayout(2, 1, 0, 20));
		panel1.setBorder(new EmptyBorder(10, 10, 10, 60));

		JLabel jl1 = new JLabel("账  号"); // 创建账号标签
		jl1.setFont(new Font("微软雅黑", Font.BOLD, 16)); // 设置字体、样式、大小
		jl1.setHorizontalAlignment(JTextField.CENTER); // 设置水平居中
		JTextField jt1 = new JTextField(10); // 创建文本框
		jt1.setFont(new Font("微软雅黑", Font.PLAIN, 16)); // 设置字体、样式、大小

		JLabel jl2 = new JLabel("密  码"); // 创建密码标签
		jl2.setFont(new Font("微软雅黑", Font.BOLD, 16)); // 设置字体、样式、大小
		jl2.setHorizontalAlignment(JTextField.CENTER); // 设置居中
		JPasswordField jt2 = new JPasswordField(10); // 创建密码文本框
		jt2.setEchoChar('*'); // 设置回显字符为 *
		jt2.setFont(new Font("微软雅黑", Font.PLAIN, 16)); // 设置字体、样式、大小

		panel1.add(jl1);
		panel1.add(jt1);
		panel1.add(jl2);
		panel1.add(jt2);

		inter.add(panel1, BorderLayout.CENTER);

		// 登录按钮面板
		JPanel panel2 = new JPanel(new FlowLayout());

		JButton jb1 = new JButton("登录");
		// 登录按钮触发事件
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jb1.setText("正在登录......");
				String connectDB="jdbc:mysql://localhost:3306/shopping";
				String user="root";
				String password="123123";
				try {
					Connection conn=DriverManager.getConnection(connectDB,user,password);
					String sql="select UserPassword from users where UserName=? and UserPassword=?";
					PreparedStatement pst=conn.prepareStatement(sql);
					pst.setString(1, jt1.getText());
					String pwd=new String(jt2.getPassword());
					pst.setString(2, pwd);
					ResultSet rSet=pst.executeQuery();
					if (rSet.next()) {
						new MenuGui();
						User u=new User();
						u.setUserName(jt1.getText());
					}
					else {
						JOptionPane.showMessageDialog(inter, "你输入的密码和账户名不匹配");
						jt2.setText("");
						jb1.setText("登录");
					}
				}
				catch(Exception ex)
				{
					System.out.print(ex.getMessage());
				}
			}
		});
		
		// 注册标签
		JButton jb2 = new JButton("注册");
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new SignupGui();
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.print(e2.getMessage());
				}
							
			}
		});
		

		panel2.add(jb1);
		panel2.add(jb2);

		inter.add(panel2, BorderLayout.SOUTH);

		// Display the window.
		inter.pack();
		inter.setVisible(true);
		// 设置窗口居中显示
		SettingWindow.setFrameCenter(inter);

	}

}
