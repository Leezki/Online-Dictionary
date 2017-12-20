package UI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import Data.MainData;
import UI.theFrame;


/* ------------ 词典选择区  ----------*/
@SuppressWarnings({ "serial", "unused" })
public class Choose_Panel extends JPanel{
	
	/* ------------ 词典选择区界面生成  ----------*/
	@SuppressWarnings("rawtypes")
	public Choose_Panel(){
		
		Data.MainData.choosePanel = new JPanel();
		Data.MainData.BingLabel = new JLabel("  必应词典  ");
		Data.MainData.YoudaoLabel = new JLabel("  有道词典  ");
		Data.MainData.JinshanLabel = new JLabel("  金山词典  ");
		
        Data.MainData.checkBox1 = new JCheckBox("  必应词典");// 创建复选按钮
    	Data.MainData.checkBox1.setSelected(true);
    	Data.MainData.checkBox1.addActionListener(new CheckBox1ActionListener());
   		Data.MainData.checkBox2 = new JCheckBox("  有道词典");// 创建复选按钮
   		Data.MainData.checkBox2.setSelected(true);
   		Data.MainData.checkBox2.addActionListener(new CheckBox2ActionListener());
   		Data.MainData.checkBox3 = new JCheckBox("  金山词典");// 创建复选按钮
   		Data.MainData.checkBox3.setSelected(true);
   		Data.MainData.checkBox3.addActionListener(new CheckBox3ActionListener());
		Data.MainData.choosePanel.setLayout(null);
		Data.MainData.choosePanel.setBounds(0, 60, 157, 435);
		Data.MainData.checkBox1.setBounds(25, 95, 130, 50);
		Data.MainData.checkBox2.setBounds(25, 157, 130, 50);
		Data.MainData.checkBox3.setBounds(25, 219, 130, 50);
		
		Data.MainData.choosePanel.add(Data.MainData.checkBox1);
    	Data.MainData.choosePanel.add(Data.MainData.checkBox2);
   		Data.MainData.choosePanel.add(Data.MainData.checkBox3);
        	
	}
	
	/* ------------ 选择框1事件监听  ----------*/
	class CheckBox1ActionListener implements ActionListener {
		 public void actionPerformed(ActionEvent ev){
			 UI.theFrame.printWebCrawler();
				
		 }
	 }
	
	/* ------------ 选择框2事件监听  ----------*/
	class CheckBox2ActionListener implements ActionListener {
		 public void actionPerformed(ActionEvent ev){
			UI.theFrame.printWebCrawler();
					
		 }
	 }
	
	/* ------------ 选择框3事件监听  ----------*/
	class CheckBox3ActionListener implements ActionListener {
		 public void actionPerformed(ActionEvent ev){
			 UI.theFrame.printWebCrawler();
				
		 } 
	 }
	
	
	
	
}
