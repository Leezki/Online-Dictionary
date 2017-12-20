package UI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

import Client.Client;
import Data.MainData;
import WebCrawler.WebCrawler;
import UI.theFrame;
import UI.theEmail;
import UI.theFriend;
import UI.Translation_Panel;


/* -------- 输入区/功能选择区  -------*/
@SuppressWarnings({ "unused", "serial" })
public class Input_Panel extends JPanel {
	
	public static ImageIcon user;
	public static ImageIcon friend;
	public static ImageIcon email;
	

/* -------- 输入区/功能选择区界面生成  -------*/
	public Input_Panel(){
		
		 Font InputTextFont = new Font("TimesRoman", Font.PLAIN, 16);
		
		 Data.MainData.emailLabel = new JLabel();
		 Data.MainData.friendLabel = new JLabel();
		 Data.MainData.userLoginLabel = new JLabel();
		 Data.MainData.InputPanel = new JPanel();
		 Data.MainData.InputText = new TextField(30);
		 Data.MainData.clickbutton = new JButton("Search");
		
		 user = new ImageIcon("/Users/liyuchao/Downloads/user.png");
		 user.setImage(user.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT));
		 Data.MainData.userLoginLabel.setIcon(user);
		 friend = new ImageIcon("/Users/liyuchao/Downloads/friend.png");
		 friend.setImage(friend.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT));
		 Data.MainData.friendLabel.setIcon(friend);
		 email = new ImageIcon("/Users/liyuchao/Downloads/email.png");
		 email.setImage(email.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT));
		 Data.MainData.emailLabel.setIcon(email);
		 
		 Data.MainData.InputText.setFont(InputTextFont);
		 Data.MainData.InputText.addTextListener(new InputTextListener());
		 Data.MainData.InputText.addActionListener(new InputTextActionListener());
		 Data.MainData.clickbutton.addActionListener(new ButtonClickListener());
	
		 Data.MainData.InputPanel.setLayout(null);
		 Data.MainData.InputPanel.setBounds(0, 0, 693, 70);
		 Data.MainData.InputText.setBounds(120, 20, 390, 28);
		 Data.MainData.clickbutton.setBounds(520, 22, 80, 25);
		 Data.MainData.userLoginLabel.setBounds(620, 47, 16, 16);
		 Data.MainData.friendLabel.setBounds(644, 47, 16, 16);
		 Data.MainData.emailLabel.setBounds(668, 47, 16, 16);
		 
		 Data.MainData.userLoginLabel.addMouseListener(new loginMouseListener());
		 Data.MainData.friendLabel.addMouseListener(new friendMouseListener());
		 Data.MainData.emailLabel.addMouseListener(new  emailMouseListener());
			
		 Data.MainData.InputPanel.add(Data.MainData.InputText);
		 Data.MainData.InputPanel.add(Data.MainData.clickbutton);
		 Data.MainData.InputPanel.add(Data.MainData.userLoginLabel);
		 Data.MainData.InputPanel.add(Data.MainData.friendLabel);
		 Data.MainData.InputPanel.add(Data.MainData.emailLabel);
	}

	/* -------- 朋友系统图标鼠标监听  -------*/
	class friendMouseListener implements MouseListener{

		@SuppressWarnings("unchecked")
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(Data.MainData.isLogin){
				try {
					Data.MainData.snap = theFrame.getWordCard();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				theFriend f = new theFriend();
				Client.searchOnlineUsers();
			    Data.MainData.friendList.setListData(Data.MainData.onlineUsers);
				Data.MainData.friendList.setSelectedIndex(0);
			}
			else{
				 JOptionPane.showMessageDialog(null, "尚未登录，无法进入好友系统", "提示", JOptionPane.ERROR_MESSAGE);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
	}

	/* -------- 收件箱图标鼠标监听  -------*/
	class emailMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(Data.MainData.isLogin){
				 theEmail email = new theEmail();
				 Data.MainData.emailList.setSelectedIndex(0);
			}
			else{
				 JOptionPane.showMessageDialog(null, "尚未登录，无法进入收件箱", "提示", JOptionPane.ERROR_MESSAGE);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
	}
	class loginMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(Data.MainData.isLogin){
				JOptionPane.showMessageDialog(null, "账户状态：在线  (如需切换账号，请退出后重新登陆)", "提示", JOptionPane.ERROR_MESSAGE);
			}
			else{
				theLogin log = new theLogin();
			}		
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	
	class InputTextActionListener implements ActionListener {
		 public void actionPerformed(ActionEvent ev){
		 }
	 }

	/* -------- 单词查询事件监听  -------*/
	 class ButtonClickListener implements ActionListener{
		 public void actionPerformed(ActionEvent ev){
			 if(Data.MainData.InputText.getText() != null){	
				
				 //judge correction
				 int numOfLetter = 0;
				 
				 for(int i = 0; i < Data.MainData.InputText.getText().length(); i++){
					 if(!((Data.MainData.InputText.getText().charAt(i) >= 'a' && Data.MainData.InputText.getText().charAt(i) <='z') ||
					(Data.MainData.InputText.getText().charAt(i) >= 'A' && Data.MainData.InputText.getText().charAt(i) <='Z') ||
					Data.MainData.InputText.getText().charAt(i) == '-' || Data.MainData.InputText.getText().charAt(i) == ' ')){
						 
					 }
						 
					 else{
						 if((Data.MainData.InputText.getText().charAt(i) >= 'a' && Data.MainData.InputText.getText().charAt(i) <='z') ||
							(Data.MainData.InputText.getText().charAt(i) >= 'A' && Data.MainData.InputText.getText().charAt(i) <='Z'))
							 numOfLetter++;
					 }
				 }
				 
				
				 
				 if(numOfLetter == 0){
					 JOptionPane.showMessageDialog(null, "您查询的单词或词组不存在，请输入正确的英文单词或词组", "提示", JOptionPane.ERROR_MESSAGE);
				 }
				 
				 else{
					 System.out.println(Data.MainData.InputText.getText());
					 
					 UI.Translation_Panel.bingClicked = false;
					 UI.Translation_Panel.youdaoClicked = false;
					 UI.Translation_Panel.jinshanClicked = false;
					 
					 String result = Client.wordSearch(Data.MainData.InputText.getText());
				
					
					 int Bingnum = new Integer(result.substring(0, result.indexOf(" ")));
					 result = result.substring(result.indexOf(" ") + 1);
					 int Youdaonum = new Integer(result.substring(0, result.indexOf(" ")));
					 result = result.substring(result.indexOf(" ") + 1);
					 int Jinshannum = new Integer(result);
					
					 UI.theFrame.printFrame(Bingnum, Youdaonum, Jinshannum);
					
				 }
			 }
		}
	 }
	 

	/* -------- 输入框监听  -------*/
	 class InputTextListener implements TextListener{
	        @SuppressWarnings("unchecked")
			public void textValueChanged(TextEvent e){
	           if(Data.MainData.InputText.getText() != null){
	        	   Data.MainData.searchWord =  Data.MainData.InputText.getText();
	           }    
	        }  
	 }
}
