package UI;
import java.awt.Robot;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import Data.MainData;
import Data.WordCardInfo;

@SuppressWarnings({ "serial", "unused" })
public class theFrame extends JFrame{
	
	public static Robot robot;
	public static Translation_Panel tPanel;
	public static Choose_Panel vPanel;
	public static Input_Panel iPanel;
	
	@SuppressWarnings("rawtypes")
	public theFrame(){
		
		Data.MainData.isLogin = false;
		Data.MainData.emailList = new JList();
		Data.MainData.wordCardList = new ArrayList<WordCardInfo>();
		Data.MainData.friendList = new JList();
		
		Data.MainData.BingPos_Y = 0;
		Data.MainData.YoudaoPos_Y = 133;
		Data.MainData.JinshanPos_Y = 266;
		
		Data.MainData.bingTranslation = new ArrayList<String>();
		Data.MainData.youdaoTranslation = new ArrayList<String>();
		Data.MainData.jinshanTranslation = new ArrayList<String>();
		
		iPanel = new Input_Panel();
		tPanel = new Translation_Panel();
		vPanel = new Choose_Panel();
		
		Data.MainData.frame = new JFrame("Online-Dictionary");
		Data.MainData.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Data.MainData.frame.setLayout(null);
		
		//color
		float HBS[] = new float[3];
		float HBS2[] = new float[3];
		Color.RGBtoHSB(69,98,147, HBS);
		Color.RGBtoHSB(234,234,234, HBS2);
		float HBS3[] = new float[3];
		Color.RGBtoHSB(243,239,235, HBS3);
		Data.MainData.frame.setBackground(Color.getHSBColor(HBS3[0], HBS3[1], HBS3[2]));
		
		Data.MainData.InputPanel.setBackground(Color.getHSBColor(HBS[0], HBS[1], HBS[2]));
		
		Data.MainData.choosePanel.setBackground(Color.getHSBColor(HBS3[0], HBS3[1], HBS3[2]));
		
		Data.MainData.frame.add(Data.MainData.InputPanel);
		Data.MainData.frame.add(Data.MainData.choosePanel);
		Data.MainData.frame.add(Data.MainData.translationPanel);
	
		Data.MainData.frame.setSize(695,515);
		Data.MainData.frame.setLocationRelativeTo(null);
		Data.MainData.frame.addWindowListener(new Monitor());
		Data.MainData.frame.setVisible(true);
		
		
	}
	
	class Monitor extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			
			if(Data.MainData.currentUser != "")
				Client.Client.setUserOffline(Data.MainData.currentUser);
			
			Data.MainData.frame.dispose();
		}
		
	}
	
	public static void sort(int Bingnum, int Youdaonum, int Jinshannum){
		
		if(Bingnum < Youdaonum){
			if(Bingnum < Jinshannum){
				Data.MainData.BingPos_Y = 266;
				if(Youdaonum < Jinshannum){
					Data.MainData.YoudaoPos_Y = 133;
					Data.MainData.JinshanPos_Y = 0;
				}
				else{
					Data.MainData.JinshanPos_Y = 133;
					Data.MainData.YoudaoPos_Y = 0;
				}
			}
			else{
				Data.MainData.JinshanPos_Y = 266;
				Data.MainData.BingPos_Y = 133;
				Data.MainData.YoudaoPos_Y = 0;
			}
		}
		else{
			if(Youdaonum < Jinshannum){
				Data.MainData.YoudaoPos_Y = 266;
				if(Bingnum < Jinshannum){
					Data.MainData.BingPos_Y = 133;
					Data.MainData.JinshanPos_Y = 0;
				}
				else{
					Data.MainData.JinshanPos_Y = 133;
					Data.MainData.BingPos_Y = 0;
				}
			}
			else{
				Data.MainData.JinshanPos_Y = 266;
				Data.MainData.YoudaoPos_Y = 133;
				Data.MainData.BingPos_Y = 0;
			}
		}
	}
	
	public static void printFrame(int Bingnum, int Youdaonum, int Jinshannum){
		sort(Bingnum, Youdaonum, Jinshannum);
		tPanel = new Translation_Panel();
		Data.MainData.frame.add(Data.MainData.translationPanel);
		
		try {
			Data.MainData.crawler.Crawler_Bing(Data.MainData.searchWord);
			Data.MainData.crawler.Crawler_Youdao(Data.MainData.searchWord);
			Data.MainData.crawler.Crawler_Jinshan(Data.MainData.searchWord);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		UI.theFrame.printWebCrawler();
	}
	
	public static void printWebCrawler(){
		Data.MainData.BingTransText.setText("");
		Data.MainData.YoudaoTransText.setText("");
		Data.MainData.JinshanTransText.setText("");
		
		if(Data.MainData.checkBox1.isSelected()){
			for(int i = 0; i < Data.MainData.bingTranslation.size(); i++){
				
				Data.MainData.BingTransText.append(Data.MainData.bingTranslation.get(i));
			}
		}
		if(Data.MainData.checkBox2.isSelected()){
			for(int i = 0; i < Data.MainData.youdaoTranslation.size(); i++){
				
				Data.MainData.YoudaoTransText.append(Data.MainData.youdaoTranslation.get(i));
			}
		}
		if(Data.MainData.checkBox3.isSelected()){
			for(int i = 0; i < Data.MainData.jinshanTranslation.size(); i++){
				
				Data.MainData.JinshanTransText.append(Data.MainData.jinshanTranslation.get(i));
			}
		}
	}
	
	public static byte[] getWordCard() throws Exception{
		robot = new Robot();
		Rectangle rect = new Rectangle();
		rect.x = Data.MainData.translationPanel.getLocationOnScreen().x;
		rect.y = Data.MainData.translationPanel.getLocationOnScreen().y;
		rect.width = Data.MainData.translationPanel.getWidth();
		rect.height = Data.MainData.translationPanel.getHeight();
	
		BufferedImage image = robot.createScreenCapture(rect);
		
		//File file = new File ("/Users/liyuchao/Desktop/test.png");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String format = "PNG";
		ImageIO.write(image,format,out);
		
		return out.toByteArray();
	}
	
}
