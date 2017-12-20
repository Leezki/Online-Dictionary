package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Data.MainData;
import Data.WordCardInfo;


/* -------------  the FriendSystem Frame  ----------------*/
public class theFriend extends JFrame{
	
	public static ImageIcon f;
	
	/* ------------------ 好友系统界面生成 -------------------*/
	@SuppressWarnings("rawtypes")
	public theFriend(){
	
		Data.MainData.friendFrame = new JFrame("Friend-System");
		Data.MainData.friendFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Data.MainData.friendFrame.setLayout(null);
		Data.MainData.friendPanel = new JPanel();
		Data.MainData.sendButton = new JButton("发送单词卡");
		Data.MainData.friendSysLabel = new JLabel();
		
		f = new ImageIcon("/Users/liyuchao/Downloads/friend2.png");
		f.setImage(f.getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT));
		Data.MainData.friendSysLabel.setIcon(f);
		
		
		JScrollPane fScroller = new JScrollPane(Data.MainData.friendList);
		fScroller.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		fScroller.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		Font friendListFont= new Font("calibri", Font.ROMAN_BASELINE, 16);
		Data.MainData.friendList.setFont(friendListFont);
		
		Data.MainData.friendPanel.setLayout(null);
		Data.MainData.friendPanel.setBounds(0, 0, 300, 450);
		Data.MainData.friendSysLabel.setBounds(130, 10, 32, 32);
		//Data.MainData.friendList.setBounds(20, 50, 260, 300);
		fScroller.setBounds(20, 50, 260, 340);
		Data.MainData.sendButton.setBounds(90, 394, 120, 25);
		
		Data.MainData.friendList.addListSelectionListener(new friendListListener());
		Data.MainData.sendButton.addActionListener(new sendButtonActionListener());
		Data.MainData.friendPanel.add(Data.MainData.friendSysLabel);
		Data.MainData.friendPanel.add(Data.MainData.sendButton);
		Data.MainData.friendPanel.add(fScroller);
		Data.MainData.friendFrame.add(Data.MainData.friendPanel);
		
		float HBS1[] = new float[3];
		Color.RGBtoHSB(243,239,235, HBS1);
		Data.MainData.friendPanel.setBackground(Color.getHSBColor(HBS1[0], HBS1[1], HBS1[2]));
		Data.MainData.friendFrame.setBackground(Color.getHSBColor(HBS1[0], HBS1[1], HBS1[2]));
		Data.MainData.friendFrame.setSize(300,450);
		Data.MainData.friendFrame.setLocationRelativeTo(null);
		Data.MainData.friendFrame.setVisible(true);
		
	}
	
	class friendListListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	/* ------------------ 发送单词卡button事件监听 ---------------------*/
	class sendButtonActionListener implements ActionListener{
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent ev){
			 if (Data.MainData.friendList.getSelectedValue()!=null){
				 String info = Data.MainData.friendList.getSelectedValue().toString();
				 String id = info.substring(info.indexOf("(") + 1, info.indexOf(")"));
				
				 Client.Client.addCard(Data.MainData.currentUser, id, Data.MainData.InputText.getText(), Data.MainData.snap);
				
			 }
		}
	}
}


