package shopping.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import shopping.db.UserDao;
import shopping.model.User;

public class SignupGui {
		public SignupGui() {
			initSignupGui();
		}
		public void initSignupGui() {
			JFrame signup = new JFrame("商城用户注册");
			signup.setResizable(false);
			signup.setSize(240, 166);
			signup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			//signup.setLayout(new BorderLayout());
			// 账号密码框
			JPanel panel1 = new JPanel();
			//panel1.setBorder(new EmptyBorder(10, 10, 10, 50));

			JLabel jl1 = new JLabel("账      号"); // 创建账号标签
			jl1.setFont(new Font("微软雅黑", Font.BOLD, 16)); // 设置字体、样式、大小
			jl1.setHorizontalAlignment(JTextField.CENTER); // 设置水平居中
			JTextField jt1 = new JTextField(10); // 创建文本框
			jt1.setFont(new Font("微软雅黑", Font.PLAIN, 16)); // 设置字体、样式、大小

			JLabel jl2 = new JLabel("密      码"); // 创建密码标签
			jl2.setFont(new Font("微软雅黑", Font.BOLD, 16)); // 设置字体、样式、大小
			jl2.setHorizontalAlignment(JTextField.CENTER); // 设置居中
			JPasswordField jt2 = new JPasswordField(10); // 创建密码文本框
			jt2.setEchoChar('*'); // 设置回显字符为 *
			jt2.setFont(new Font("微软雅黑", Font.PLAIN, 16)); // 设置字体、样式、大小
			
			JLabel jl3 = new JLabel("确认密码"); // 创建密码标签
			jl3.setFont(new Font("微软雅黑", Font.BOLD, 16)); // 设置字体、样式、大小
			jl3.setHorizontalAlignment(JTextField.CENTER); // 设置居中
			JPasswordField jt3 = new JPasswordField(10); // 创建密码文本框
			jt3.setEchoChar('*'); // 设置回显字符为 *
			jt3.setFont(new Font("微软雅黑", Font.PLAIN, 16)); // 设置字体、样式、大小
			

			panel1.add(jl1);
			panel1.add(jt1);
			panel1.add(jl2);
			panel1.add(jt2);
			panel1.add(jl3);
			panel1.add(jt3);
			signup.add(panel1, BorderLayout.CENTER);
			
			JPanel panel2 = new JPanel(new FlowLayout());
			JButton jb1 = new JButton("确认");
			jb1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					User u1=new User();
					u1.setUserName(jt1.getText());
					String uname=new String(jt1.getText());
					String code1=new String(jt2.getPassword());
					String code2=new String(jt3.getPassword());
					if (code1.equals(code2)&&code1!=""&&code2!=""&&uname!="") {
						u1.setPassword(code1);
						int count=UserDao.insertUser(u1);
						if (count==1) {
							JOptionPane.showMessageDialog(null, "注册成功");
							new LoginGui();
						}
					}else {
						JOptionPane.showMessageDialog(signup, "两次密码不一致，请重新确认");
						jt3.setText("");
						jt2.setText("");
					}		
				}
			});
			panel2.add(jb1);

			signup.add(panel2, BorderLayout.SOUTH);

			
			// signup.pack();
			signup.setVisible(true);
			// 设置窗口居中显示
			SettingWindow.setFrameCenter(signup);

			
		}
}
		
	