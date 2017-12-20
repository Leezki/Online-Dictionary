package UI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

import Data.MainData;


/* -------------  the Register Frame  ----------------*/
public class theRegister extends JFrame{
	
	public static ImageIcon user;
	
	/*----------------- 初始化生成注册界面的UI ----------*/
	public theRegister(){
		Data.MainData.registerSubmitButton = new JButton(" 提交 ");
		Data.MainData.registerCancelButton = new JButton(" 取消 ");
		Data.MainData.registerNameLabel = new JLabel("用户名");
		Data.MainData.registerImageLabel =  new JLabel();
		Data.MainData.registerNamePanel = new JPanel();
		Data.MainData.registerNameText = new TextField(20);
		Data.MainData.registerPasswordLabel = new JLabel("密码");
		Data.MainData.registerPasswordPanel = new JPanel();
		Data.MainData.registerPasswordText = new TextField(20);
		Data.MainData.registerPasswordConfirmLabel = new JLabel("密码确认");
		Data.MainData.registerPasswordConfirmPanel = new JPanel();
		Data.MainData.registerPasswordConfirmText = new TextField(20);
		
		user = new ImageIcon("/Users/liyuchao/Downloads/user2.png");
		user.setImage(user.getImage().getScaledInstance(36,36,Image.SCALE_DEFAULT));
		Data.MainData.registerImageLabel.setIcon(user);
		
		Data.MainData.registerNamePanel.setLayout(null);
		Data.MainData.registerNamePanel.setBounds(0, 0, 480, 120);
		Data.MainData.registerNameLabel.setBounds(140, 100, 50, 20);
		Data.MainData.registerNameText.setBounds(210, 100, 140, 20);
		Data.MainData.registerImageLabel.setBounds(230, 40, 36, 36);
		
		Data.MainData.registerPasswordPanel.setLayout(null);
		Data.MainData.registerPasswordPanel.setBounds(0, 120, 480, 30);
		Data.MainData.registerPasswordLabel.setBounds(140, 10, 50, 20);
		Data.MainData.registerPasswordText.setBounds(210, 10, 140, 20);
		Data.MainData.registerPasswordText.setEchoChar('*');
		
		Data.MainData.registerPasswordConfirmPanel.setLayout(null);
		Data.MainData.registerPasswordConfirmPanel.setBounds(0, 150, 480, 200);
		Data.MainData.registerPasswordConfirmLabel.setBounds(140, 10, 60, 20);
		Data.MainData.registerPasswordConfirmText.setBounds(210, 10, 140, 20);
		Data.MainData.registerPasswordConfirmText.setEchoChar('*');
		Data.MainData.registerSubmitButton.setBounds(180, 60, 60, 30);
		Data.MainData.registerCancelButton.setBounds(270, 60, 60, 30);
		
		Data.MainData.registerSubmitButton.addActionListener(new registerSubmitActionListener());
		Data.MainData.registerCancelButton.addActionListener(new registerCancelActionListener());
		
		Data.MainData.registerNamePanel.add(Data.MainData.registerImageLabel);
		Data.MainData.registerNamePanel.add(Data.MainData.registerNameLabel);
		Data.MainData.registerNamePanel.add(Data.MainData.registerNameText);
		Data.MainData.registerPasswordPanel.add(Data.MainData.registerPasswordText);
		Data.MainData.registerPasswordPanel.add(Data.MainData.registerPasswordLabel);
		Data.MainData.registerPasswordConfirmPanel.add(Data.MainData.registerPasswordConfirmText);
		Data.MainData.registerPasswordConfirmPanel.add(Data.MainData.registerPasswordConfirmLabel);
		Data.MainData.registerPasswordConfirmPanel.add(Data.MainData.registerSubmitButton);
		Data.MainData.registerPasswordConfirmPanel.add(Data.MainData.registerCancelButton);
		
		float HBS[] = new float[3];
		Color.RGBtoHSB(243,239,235, HBS);
		Data.MainData.registerNamePanel.setBackground(Color.getHSBColor(HBS[0], HBS[1], HBS[2]));
		Data.MainData.registerPasswordPanel.setBackground(Color.getHSBColor(HBS[0], HBS[1], HBS[2]));
		Data.MainData.registerPasswordConfirmPanel.setBackground(Color.getHSBColor(HBS[0], HBS[1], HBS[2]));
		
		Data.MainData.registerFrame = new JFrame("Online-Dictionary-Register");
		Data.MainData.registerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Data.MainData.registerFrame.setLayout(null);
		Data.MainData.registerFrame.add(Data.MainData.registerNamePanel);
		Data.MainData.registerFrame.add(Data.MainData.registerPasswordPanel);
		Data.MainData.registerFrame.add(Data.MainData.registerPasswordConfirmPanel);
		
		Data.MainData.registerFrame.setSize(480,350);
		Data.MainData.registerFrame.setLocationRelativeTo(null);
		Data.MainData.registerFrame.setVisible(true);
	}
	
	/*------------------ 提交注册button事件监听 ---------------*/
	class registerSubmitActionListener implements ActionListener{
		 public void actionPerformed(ActionEvent ev){		
			 int num = 0;
			 for(int i = 0; i < Data.MainData.registerNameText.getText().length(); i++){
				 if( Data.MainData.registerNameText.getText().charAt(i) >='0' && Data.MainData.registerNameText.getText().charAt(i) <= '9')
					 num++;
			 }
						
			 if(num != 11 || Data.MainData.registerNameText.getText().length() != 11){
				 JOptionPane.showMessageDialog(null, "账户应为11位数字，请重新输入", "提示", JOptionPane.ERROR_MESSAGE); 									
			 } 
			 
			 else if(!(Data.MainData.registerPasswordConfirmText.getText().equals(Data.MainData.registerPasswordText.getText())))
				 JOptionPane.showMessageDialog(null, "两次密码输入不一样，请重新输入", "提示", JOptionPane.ERROR_MESSAGE); 		
			 
			 else{
				 String result = new String();
				 result = Client.Client.userAdd(Data.MainData.registerNameText.getText(),Data.MainData.registerPasswordText.getText());
				
				 if(result.equals("Y")){
					 JOptionPane.showMessageDialog(null, "注册成功！", "提示", JOptionPane.INFORMATION_MESSAGE); 
					 //Data.MainData.registerFrame.setVisible(false);
					 Data.MainData.registerFrame.dispose();
					 Data.MainData.loginFrame.setVisible(true);
				 }
				 else{
					 JOptionPane.showMessageDialog(null, "此账户已被注册，请直接登陆", "提示", JOptionPane.ERROR_MESSAGE); 
					 Data.MainData.registerFrame.dispose();
					 //Data.MainData.registerFrame.setVisible(false);
					 Data.MainData.loginFrame.setVisible(true);
				 }
			 }
		 }
	}
	

	/*------------------ 取消注册button事件监听 ---------------*/
	class registerCancelActionListener implements ActionListener{
		 public void actionPerformed(ActionEvent ev){
			 Data.MainData.registerFrame.dispose();
			 Data.MainData.loginFrame.setVisible(true);
		 }
	}
}
