package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Data.MainData;



/* -------------  翻译区  ----------------*/
@SuppressWarnings("unused")
public class Translation_Panel {
	public static ImageIcon notLike;
	public static ImageIcon doLike;
	public static boolean bingClicked;
	public static boolean youdaoClicked;
	public static boolean jinshanClicked;
	

	/* -------------  翻译区界面控件初始化  ----------------*/
	public Translation_Panel(){
		
		Data.MainData.translationPanel = new JPanel();
		Data.MainData.BingTransPanel = new JPanel();
		Data.MainData.JinshanTransPanel = new JPanel();
		Data.MainData.YoudaoTransPanel = new JPanel();
		
		Data.MainData.BingEnglishTransLabel = new JLabel();
		Data.MainData.BingChineseTransLabel = new JLabel();
		Data.MainData.BingPropTransLabel = new JLabel();
		
		Data.MainData.JinshanEnglishTransLabel = new JLabel();
		Data.MainData.JinshanChineseTransLabel = new JLabel();
		Data.MainData.JinshanPropTransLabel = new JLabel();
		
		Data.MainData.YoudaoEnglishTransLabel = new JLabel();
		Data.MainData.YoudaoChineseTransLabel = new JLabel();
		
		Data.MainData.BingTransText = new JTextArea();
		Data.MainData.YoudaoTransText = new JTextArea();
		Data.MainData.JinshanTransText = new JTextArea();
		
		Data.MainData.LikeLabel1 = new JLabel();
		Data.MainData.LikeLabel2 = new JLabel();
		Data.MainData.LikeLabel3 = new JLabel();
		
		//font
		Font transTextFont= new Font("Yahei", Font.PLAIN, 13);
		Data.MainData.BingTransText.setFont(transTextFont);
		Data.MainData.YoudaoTransText.setFont(transTextFont);
		Data.MainData.JinshanTransText.setFont(transTextFont);
		
		// icon
		notLike = new ImageIcon("/Users/liyuchao/Downloads/notLike.png");
		doLike = new ImageIcon("/Users/liyuchao/Downloads/doLike.png");
		
		notLike.setImage(notLike.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
		doLike.setImage(doLike.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
		
		Data.MainData.LikeLabel1.setIcon(notLike);
		Data.MainData.LikeLabel2.setIcon(notLike);
		Data.MainData.LikeLabel3.setIcon(notLike);
		
		
		Data.MainData.translationPanel.setLayout(null);
		Data.MainData.translationPanel.setBorder(new TitledBorder("翻译区"));
		
		Data.MainData.translationPanel.setBounds(155, 70, 540, 425);
	
		// Bing 
		Data.MainData.BingTransPanel.setBounds(10, 20 + Data.MainData.BingPos_Y, 500, 133);
		Data.MainData.BingTransPanel.setBorder(new TitledBorder("必应"));
	
		Data.MainData.BingTransText.setPreferredSize(new Dimension(400, 100));
		Data.MainData.BingTransText.setMaximumSize(new Dimension(400,100));
		Data.MainData.BingTransText.setMinimumSize(new Dimension(400,100));
		Data.MainData.BingTransText.setOpaque(false);
		Data.MainData.BingTransText.setLineWrap(true);
		
		Data.MainData.BingTransPanel.add(Data.MainData.BingTransText);
	
		
		// Youdao
		Data.MainData.YoudaoTransPanel.setBounds(10, 20 + Data.MainData.YoudaoPos_Y, 500, 133);
		Data.MainData.YoudaoTransPanel.setBorder(new TitledBorder("有道"));
		Data.MainData.YoudaoTransText.setPreferredSize(new Dimension(400, 100));
		Data.MainData.YoudaoTransText.setMaximumSize(new Dimension(400,100));
		Data.MainData.YoudaoTransText.setMinimumSize(new Dimension(400,100));
		Data.MainData.YoudaoTransText.setOpaque(false);
		Data.MainData.YoudaoTransText.setLineWrap(true);
		
		Data.MainData.YoudaoTransPanel.add(Data.MainData.YoudaoTransText);
		
		
		// Jinshan 
		Data.MainData.JinshanTransPanel.setBounds(10, 20 + Data.MainData.JinshanPos_Y, 500, 133);
		Data.MainData.JinshanTransPanel.setBorder(new TitledBorder("金山"));
		Data.MainData.JinshanTransText.setPreferredSize(new Dimension(400, 100));
		Data.MainData.JinshanTransText.setMaximumSize(new Dimension(400,100));
		Data.MainData.JinshanTransText.setMinimumSize(new Dimension(400,100));
		Data.MainData.JinshanTransText.setOpaque(false);
		Data.MainData.JinshanTransText.setLineWrap(true);	
		Data.MainData.JinshanTransPanel.add(Data.MainData.JinshanTransText);
		
		Data.MainData.translationPanel.add(Data.MainData.BingTransPanel);
		Data.MainData.translationPanel.add(Data.MainData.YoudaoTransPanel);
		Data.MainData.translationPanel.add(Data.MainData.JinshanTransPanel);
		
		Data.MainData.LikeLabel1.setBounds(510, 63, 20, 20);		
		Data.MainData.LikeLabel2.setBounds(510, 199 , 20, 20);		
		Data.MainData.LikeLabel3.setBounds(510, 329, 20, 20);
		
		Data.MainData.translationPanel.add(Data.MainData.LikeLabel1);
		Data.MainData.translationPanel.add(Data.MainData.LikeLabel2);
		Data.MainData.translationPanel.add(Data.MainData.LikeLabel3);
		Data.MainData.LikeLabel1.addMouseListener(new LikeMouseListener1());
		Data.MainData.LikeLabel2.addMouseListener(new LikeMouseListener2());
		Data.MainData.LikeLabel3.addMouseListener(new LikeMouseListener3());
		
		float HBS3[] = new float[3];
		Color.RGBtoHSB(243,239,235, HBS3);
		Data.MainData.BingTransText.setBackground(Color.getHSBColor(HBS3[0], HBS3[1], HBS3[2]));
		Data.MainData.translationPanel.setBackground(Color.getHSBColor(HBS3[0], HBS3[1], HBS3[2]));
		Data.MainData.BingTransPanel.setBackground(Color.getHSBColor(HBS3[0], HBS3[1], HBS3[2]));
		Data.MainData.YoudaoTransPanel.setBackground(Color.getHSBColor(HBS3[0], HBS3[1], HBS3[2]));
		Data.MainData.JinshanTransPanel.setBackground(Color.getHSBColor(HBS3[0], HBS3[1], HBS3[2]));
		
	}
	

	/* -------------  点赞图标1的鼠标监听  ----------------*/
	class LikeMouseListener1 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(Data.MainData.BingPos_Y == 0){
				if(bingClicked == false){
					bingClicked = true;
					Data.MainData.LikeLabel1.setIcon(doLike);
					Client.Client.addWordLikes(Data.MainData.InputText.getText(), "Bing");
				}
				else{
					bingClicked = false;
					Data.MainData.LikeLabel2.setIcon(notLike);
					Client.Client.deleteWordLikes(Data.MainData.InputText.getText(), "Bing");
				}
			}
			else if(Data.MainData.YoudaoPos_Y == 0){
				if(youdaoClicked == false){
					youdaoClicked = true;
					Data.MainData.LikeLabel1.setIcon(doLike);
					Client.Client.addWordLikes(Data.MainData.InputText.getText(), "Youdao");
				}
				else{
					youdaoClicked = false;
					Data.MainData.LikeLabel1.setIcon(notLike);
					Client.Client.deleteWordLikes(Data.MainData.InputText.getText(), "Youdao");
				}
			}
			else if(Data.MainData.JinshanPos_Y == 0){
				if(jinshanClicked == false){
					jinshanClicked = true;
					Data.MainData.LikeLabel1.setIcon(doLike);
					Client.Client.addWordLikes(Data.MainData.InputText.getText(), "Jinshan");
				}
				else{
					youdaoClicked = false;
					Data.MainData.LikeLabel1.setIcon(notLike);
					Client.Client.deleteWordLikes(Data.MainData.InputText.getText(), "Jinshan");
				}
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
	
	/* -------------  点赞图标2的鼠标监听  ----------------*/
	class LikeMouseListener2 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(Data.MainData.BingPos_Y == 133){
				if(bingClicked == false){
					bingClicked = true;
					Data.MainData.LikeLabel2.setIcon(doLike);
					Client.Client.addWordLikes(Data.MainData.InputText.getText(), "Bing");
				}
				else{
					bingClicked = false;
					Data.MainData.LikeLabel2.setIcon(notLike);
					Client.Client.deleteWordLikes(Data.MainData.InputText.getText(), "Bing");
				}
			}
			else if(Data.MainData.YoudaoPos_Y == 133){
				if(youdaoClicked == false){
					youdaoClicked = true;
					Data.MainData.LikeLabel2.setIcon(doLike);
					Client.Client.addWordLikes(Data.MainData.InputText.getText(), "Youdao");
				}
				else{
					youdaoClicked = false;
					Data.MainData.LikeLabel2.setIcon(notLike);
					Client.Client.deleteWordLikes(Data.MainData.InputText.getText(), "Youdao");
				}
			}
			else if(Data.MainData.JinshanPos_Y == 133){
				if(jinshanClicked == false){
					jinshanClicked = true;
					Data.MainData.LikeLabel2.setIcon(doLike);
					Client.Client.addWordLikes(Data.MainData.InputText.getText(), "Jinshan");
				}
				else{
					youdaoClicked = false;
					Data.MainData.LikeLabel2.setIcon(notLike);
					Client.Client.deleteWordLikes(Data.MainData.InputText.getText(), "Jinshan");
				}
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
	
	/* -------------  点赞图标3的鼠标监听  ----------------*/
	class LikeMouseListener3 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(Data.MainData.BingPos_Y == 266){
				if(bingClicked == false){
					bingClicked = true;
					Data.MainData.LikeLabel3.setIcon(doLike);
					Client.Client.addWordLikes(Data.MainData.InputText.getText(), "Bing");
				}
				else{
					bingClicked = false;
					Data.MainData.LikeLabel3.setIcon(notLike);
					Client.Client.deleteWordLikes(Data.MainData.InputText.getText(), "Bing");
				}
			}
			else if(Data.MainData.YoudaoPos_Y == 266){
				if(youdaoClicked == false){
					youdaoClicked = true;
					Data.MainData.LikeLabel3.setIcon(doLike);
					Client.Client.addWordLikes(Data.MainData.InputText.getText(), "Youdao");
				}
				else{
					youdaoClicked = false;
					Data.MainData.LikeLabel3.setIcon(notLike);
					Client.Client.deleteWordLikes(Data.MainData.InputText.getText(), "Youdao");
				}
			}
			else if(Data.MainData.JinshanPos_Y == 266){
				if(jinshanClicked == false){
					jinshanClicked = true;
					Data.MainData.LikeLabel3.setIcon(doLike);
					Client.Client.addWordLikes(Data.MainData.InputText.getText(), "Jinshan");
				}
				else{
					youdaoClicked = false;
					Data.MainData.LikeLabel3.setIcon(notLike);
					Client.Client.deleteWordLikes(Data.MainData.InputText.getText(), "Jinshan");
				}
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
	
}
