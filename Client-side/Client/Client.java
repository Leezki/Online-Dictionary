package Client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.stream.FileImageOutputStream;

import Data.MainData;
import WebCrawler.WebCrawler;
import UI.theFrame;
import UI.theLogin;
import UI.theRegister;

/*------ Client类似于一个controller 调用生成所有的UI与交互方法 ------*/

public class Client {
	public theFrame frame;	
	public static InetAddress addr;
	public static Socket sk;
	public static PrintWriter out;
	public static BufferedReader in;
	public static InputStreamReader str;	
	
	Client() throws Exception{
		
		frame = new theFrame();
		Data.MainData.crawler = new WebCrawler();
		
	}
	public void init(){
		try {
			addr = InetAddress.getByName("172.26.51.16");
			sk = new Socket(addr, 8888);	
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sk.getOutputStream())), true);
			in = new BufferedReader(new InputStreamReader(sk.getInputStream()));	
			str = new InputStreamReader(System.in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
	public static String loginSearch(String id){
		String result = new String();
        try {
	        String searchUserID = "SEARCH USER " + id +" LOGIN";
	        out.println(searchUserID);
	        result = in.readLine();
        }
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return result;
	}
	
	public static String wordSearch(String word){
		String result = new String();
		 try {
            String searchWord = "SEARCH WORD "+ word;
            out.println(searchWord);
            result = in.readLine();
        }
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return result;
	}
	
	public static String userAdd(String id, String password){
		String result = new String();
		String addUser = "ADD USER "+ id + " " + password;
		try {
			out.println(addUser);
			result = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void addWordLikes(String searchWord, String searchWeb){
		
          String addSearchWeb = "ADD WORD "+ searchWord+ " " + searchWeb;
		  out.println(addSearchWeb);
		  
	}
	
	public static void deleteWordLikes(String searchWord, String searchWeb){ 
            String delSearchWeb = "DEC WORD "+ searchWord+ " " + searchWeb;
            out.println(delSearchWeb);    
	}
	
	public static void  setUserOnline(String id){
			String setOnline = "SET " + id + " ONLINE";
			out.println(setOnline);
	}
	
	public static void searchOnlineUsers(){
			String searchCommand = "SEARCH ONLINE USERS";
			out.println(searchCommand);
			try {
				Data.MainData.onlineUsers = new Vector<String>();
				String temp = in.readLine();
				
				while(!temp.equals("end")){
					Data.MainData.onlineUsers.add(temp);
					temp = in.readLine();
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void  setUserOffline(String id){
		String setOnline = "SET " + id + " OFFLINE";
		out.println(setOnline);
	}
	
	public static void addCard(String SendID, String RecID, String word, byte[] img){
		String addCardCommand = "ADD CARD";
		out.println(addCardCommand);
		out.println(SendID);
		out.println(RecID);
		out.println(word);
		out.println(bytes2Hex(img));
	}
	
	public static void getCard(String id){
		String searchCardCommand = "SEARCH CARD " + id;
		out.println(searchCardCommand);
		try {
			String temp = new String();
			while(!(temp = in.readLine()).equals("*")){
				Data.WordCardInfo newCard = new Data.WordCardInfo();
				newCard.setSendID(temp);
				newCard.setWord(in.readLine());
				byte[] imgbyte = hex2Bytes(in.readLine());
				newCard.setImage(imgbyte);
				
				Data.MainData.wordCardList.add(newCard);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String bytes2Hex(byte[] src) {
        if (src == null || src.length <= 0) {   
            return null;   
        } 

        char[] res = new char[src.length * 2]; // 每个byte对应两个字符
        final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[src[i] >> 4 & 0x0f]; // 先存byte的高4位
            res[j++] = hexDigits[src[i] & 0x0f]; // 再存byte的低4位
        }

        return new String(res);
    }
	
	public static byte[] hex2Bytes(String hexString) {   
        if (hexString == null || hexString.equals("")) {   
            return null;   
        }   

        int length = hexString.length() / 2;   
        char[] hexChars = hexString.toCharArray();   
        byte[] bytes = new byte[length];   
        String hexDigits = "0123456789abcdef";
        for (int i = 0; i < length; i++) {   
            int pos = i * 2; // 两个字符对应一个byte
            int h = hexDigits.indexOf(hexChars[pos]) << 4; // 注1
            int l = hexDigits.indexOf(hexChars[pos + 1]); // 注2
            if(h == -1 || l == -1) { // 非16进制字符
                return null;
            }
            bytes[i] = (byte) (h | l);   
        }   
        return bytes;   
    }
	
	
	
	public static void main(String[] arges) throws Exception {
		try {
			Client client = new Client();
			client.init();   

        } catch (Exception e) {
        	 System.out.println(e);
        }
	}
}
