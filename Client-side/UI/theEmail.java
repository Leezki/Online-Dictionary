package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Data.MainData;
import Data.WordCardInfo;
import UI.Input_Panel.emailMouseListener;
import UI.theFriend.friendListListener;
import UI.theFriend.sendButtonActionListener;

/* -------------------- the Email frame -------------------------*/
public class theEmail {
	
	public static ImageIcon e;
	public static ImageIcon re;
	

/* -------------------- 收件箱系统界面生成 -----------------------*/
	@SuppressWarnings("rawtypes")
	public theEmail(){
		Data.MainData.emailFrame = new JFrame("EmailBox");
		Data.MainData.emailFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Data.MainData.emailFrame.setLayout(null);
		Data.MainData.emailPanel = new JPanel();
		Data.MainData.emailContentPanel = new JPanel();
		Data.MainData.emailSysLabel = new JLabel();
		Data.MainData.wordNameLabel = new JLabel();
		Data.MainData.wordCardLabel = new JLabel();
		Data.MainData.emailRefreshLabel = new JLabel();
	
		e = new ImageIcon("/Users/liyuchao/Downloads/email2.png");
		e.setImage(e.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT));
		Data.MainData.emailSysLabel.setIcon(e);
		
		re = new ImageIcon("/Users/liyuchao/Downloads/refresh.png");
		re.setImage(re.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT));
		Data.MainData.emailRefreshLabel.setIcon(re);
		
		JScrollPane eScroller = new JScrollPane(Data.MainData.emailList);
		eScroller.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		eScroller.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		Font emailListFont= new Font("calibri", Font.ROMAN_BASELINE, 16);
		Data.MainData.emailList.setFont(emailListFont);
		
		Data.MainData.emailPanel.setLayout(null);
		Data.MainData.emailPanel.setBounds(0, 0, 210, 470);
		eScroller.setBounds(5, 30, 190, 410);
		Data.MainData.emailSysLabel.setBounds(10, 10, 16, 16);
		Data.MainData.emailRefreshLabel.setBounds(30, 10, 16, 16);
		
		
		Data.MainData.emailContentPanel.setLayout(null);
		Data.MainData.emailContentPanel.setBorder(new TitledBorder("邮件内容"));
		Data.MainData.emailContentPanel.setBounds(210, 0, 470, 450);
		Data.MainData.wordCardLabel.setBounds(10, 30, 450, 400);
		
		Data.MainData.emailRefreshLabel.addMouseListener(new refreshMouseListener());
		Data.MainData.emailList.addListSelectionListener(new emailListListener());
		Data.MainData.emailPanel.add(Data.MainData.emailRefreshLabel);
		Data.MainData.emailPanel.add(Data.MainData.emailSysLabel);
		Data.MainData.emailPanel.add(eScroller);
		Data.MainData.emailContentPanel.add(Data.MainData.wordNameLabel);
		Data.MainData.emailContentPanel.add(Data.MainData.wordCardLabel);

		Data.MainData.emailFrame.add(Data.MainData.emailPanel);
		Data.MainData.emailFrame.add(Data.MainData.emailContentPanel);
		
		float HBS1[] = new float[3];
		Color.RGBtoHSB(243,239,235, HBS1);
		Data.MainData.emailContentPanel.setBackground(Color.getHSBColor(HBS1[0], HBS1[1], HBS1[2]));
		Data.MainData.emailPanel.setBackground(Color.getHSBColor(HBS1[0], HBS1[1], HBS1[2]));
		Data.MainData.emailFrame.setBackground(Color.getHSBColor(HBS1[0], HBS1[1], HBS1[2]));
		Data.MainData.emailFrame.setSize(680,470);
		Data.MainData.emailFrame.setLocationRelativeTo(null);
		Data.MainData.emailFrame.setVisible(true);
		
	}
	
/* ----------------- 刷新收件箱button鼠标监听 --------------------*/
	class refreshMouseListener implements MouseListener{

		@SuppressWarnings("unchecked")
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub  
   			 Data.MainData.emailList.removeAll();
   			 Data.MainData.wordCardList.clear();
   			
   			 Client.Client.getCard(Data.MainData.currentUser);
			 Vector<String> emailsInBox = new Vector<String>();
			 for(int i = 0; i <  Data.MainData.wordCardList.size(); i++){
				 emailsInBox.add(Data.MainData.wordCardList.get(i).getWord() + " " + "From: " + Data.MainData.wordCardList.get(i).getSendID());
			}
			 Data.MainData.emailList.setListData(emailsInBox);
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
	

/* ----------------- 刷新收件箱list选择监听 --------------------*/
	class emailListListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			 if (Data.MainData.emailList.getSelectedValue()!=null){
				 ImageIcon icon = new ImageIcon();
				 byte[] imgByte = new byte[1024];
				 String select = Data.MainData.emailList.getSelectedValue().toString();
				 for(int i = 0; i < Data.MainData.wordCardList.size();i++){		 
					if(Data.MainData.wordCardList.get(i).getWord().equals(select.substring(0, select.indexOf(" ")))){
						imgByte = Data.MainData.wordCardList.get(i).getImage();
					}
				 }
				 try {	 
					 ByteArrayInputStream in = new ByteArrayInputStream(imgByte); 
					 BufferedImage image;
					 image = ImageIO.read(in);
					 int newWidth = image.getWidth()*9/10;
					 int newHeight = image.getHeight()*9/10;
					 BufferedImage sImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);  
					 Graphics graphics = sImage.createGraphics();  
					 graphics.drawImage(image, 0, 0, newWidth, newHeight, null);  
					 
					 icon.setImage(sImage);
				 } catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	 
				 
				 Data.MainData.wordCardLabel.setIcon(icon);
			 }
		}
	}
	
}
