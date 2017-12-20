package Data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/* ------------ 单词卡类及set/get方法  ----------*/
public class WordCardInfo {
	 byte[] image;
	 String word;
	 String sendID;
	 
	 public void setImage(byte[] img){
		 image = img;
	 }
	 
	 public byte[] getImage(){
		 return image;
	 }
	 
	 public void setWord(String str){
		 word = str;
	 }
	
	 public String getWord(){
		 return word;
	 }
	 
	 public void setSendID(String ID){
		 sendID = ID;
	 }
	 
	 public String getSendID(){
		 return sendID;
	 }
	 
	 
}
