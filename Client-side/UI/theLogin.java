package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

import Data.MainData;

/* -------------  the Login Frame  ----------------*/
@SuppressWarnings({ "serial", "unused" })
public class theLogin extends JFrame{
	
	public static ImageIcon user;
	
	/*----------------- 初始化生成登陆界面的UI ----------*/
	public theLogin(){
		
		Font InputTextFont = new Font("calibri", Font.PLAIN, 16);
		float HBS[] = new float[3];
		Color.RGBtoHSB(176,196,222, HBS);
		
		Data.MainData.userNamePanel = new JPanel();
		Data.MainData.userPasswordPanel = new JPanel();
		Data.MainData.userNameLabel = new JLabel("账户 ");
		Data.MainData.userPasswordLabel = new JLabel("密码 ");
		Data.MainData.userNameText = new TextField(30);
		Data.MainData.userPasswordText = new TextField(30);
		Data.MainData.confirmButton = new JButton("登陆");
		Data.MainData.registerButton = new JButton("注册");
		Data.MainData.ImageLabel = new JLabel();
		
		user = new ImageIcon("/Users/liyuchao/Downloads/user2.png");
		user.setImage(user.getImage().getScaledInstance(36,36,Image.SCALE_DEFAULT));
		Data.MainData.ImageLabel.setIcon(user);
			
		Data.MainData.userNameText.setFont(InputTextFont);
		Data.MainData.userPasswordText.setFont(InputTextFont);
		Data.MainData.userPasswordText.setEchoChar('*');
		
		Data.MainData.userNamePanel.setLayout(null);
		Data.MainData.userNamePanel.setBounds(0, 0, 480, 160);
		Data.MainData.userNameLabel.setBounds(140, 120, 50, 20);
		Data.MainData.userNameText.setBounds(200, 120, 140, 20);
		Data.MainData.ImageLabel.setBounds(225, 60, 36, 36);
		
		Data.MainData.userPasswordPanel.setLayout(null);
		Data.MainData.userPasswordPanel.setBounds(0, 160, 480, 200);
		Data.MainData.userPasswordLabel.setBounds(140, 0, 50, 20);
		Data.MainData.userPasswordText.setBounds(200, 0, 140, 20);
		Data.MainData.confirmButton.setBounds(180, 50, 60, 30);
		Data.MainData.registerButton.setBounds(260, 50, 60, 30);
		
		Data.MainData.confirmButton.addActionListener(new logActionListener());
		Data.MainData.registerButton.addActionListener(new regActionListener());
		                                                      
		Data.MainData.userNamePanel.add(Data.MainData.ImageLabel);
		Data.MainData.userNamePanel.add(Data.MainData.userNameLabel);
		Data.MainData.userNamePanel.add(Data.MainData.userNameText);
		Data.MainData.userPasswordPanel.add(Data.MainData.userPasswordLabel);
		Data.MainData.userPasswordPanel.add(Data.MainData.userPasswordText);
		Data.MainData.userPasswordPanel.add(Data.MainData.confirmButton);
		Data.MainData.userPasswordPanel.add(Data.MainData.registerButton);
		
		float HBS1[] = new float[3];
		Color.RGBtoHSB(243,239,235, HBS1);
		Data.MainData.userNamePanel.setBackground(Color.getHSBColor(HBS1[0], HBS1[1], HBS1[2]));
		Data.MainData.userPasswordPanel.setBackground(Color.getHSBColor(HBS1[0], HBS1[1], HBS1[2]));
		
		
		/* Frame */
		Data.MainData.loginFrame = new JFrame("Online-Dictionary-Login");
		Data.MainData.loginFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Data.MainData.loginFrame.setLayout(null);
		
		Data.MainData.loginFrame.add(Data.MainData.userNamePanel);
		Data.MainData.loginFrame.add(Data.MainData.userPasswordPanel);
		
		Data.MainData.loginFrame.setBackground(Color.getHSBColor(HBS1[0], HBS1[1], HBS1[2]));
		Data.MainData.loginFrame.setSize(480,350);
		Data.MainData.loginFrame.setLocationRelativeTo(null);
		Data.MainData.loginFrame.setVisible(true);
		
	}
	
	/*------------------ 登陆button事件监听 ---------------*/
	class logActionListener implements ActionListener {
		 public void actionPerformed(ActionEvent ev){
			 int num = 0;
			 for(int i = 0; i < Data.MainData.userNameText.getText().length(); i++){
				 if(Data.MainData.userNameText.getText().charAt(i) >='0' && Data.MainData.userNameText.getText().charAt(i) <= '9'){
					 num++;
				 }
			 }
			 
			 if(num != 11 || Data.MainData.userNameText.getText().length() != 11){
				 JOptionPane.showMessageDialog(null, "账户应为11位数字，请重新输入", "提示", JOptionPane.ERROR_MESSAGE); 
			 }
			 else{
				 String result = Client.Client.loginSearch(Data.MainData.userNameText.getText());
				 String first_str = result.substring(0, 1);
				 	
				 if(first_str.equals("N")){
					 JOptionPane.showMessageDialog(null, "此账户不存在，请您先注册再登录", "Error", JOptionPane.ERROR_MESSAGE); 
				 }
				 else{
					 String pwd = result.substring(2);
					 if(Data.MainData.userPasswordText.getText().equals(pwd)){
						 Data.MainData.isLogin = true;
						 Data.MainData.currentUser = Data.MainData.userNameText.getText();
						 Client.Client.setUserOnline(Data.MainData.currentUser);
						 JOptionPane.showMessageDialog(null, "登陆成功！", "提示", JOptionPane.INFORMATION_MESSAGE); 			
						 Data.MainData.loginFrame.dispose();//本窗口销毁,释放内存资源
					 }
					 else{
						 JOptionPane.showMessageDialog(null, "账户密码错误，请重新输入", "Error", JOptionPane.ERROR_MESSAGE); 					
					 }
				 }
			 }
		 }
	 }
	

	/*------------------ 注册button事件监听 ---------------*/
	class regActionListener implements ActionListener {
		 public void actionPerformed(ActionEvent ev){
			theRegister reg = new theRegister();
			Data.MainData.loginFrame.setVisible(false);
		 }
	 }
}
