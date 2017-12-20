import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import WordCard.WordCard;

//服务器类
public class Server{
	// member data
	private dictionaryDB dicDataBaseOperation;
	private int port;
	private ServerSocket mySocket;
	private Socket sk;
	private BufferedReader in;
	private PrintWriter out;
	private boolean continueLoop;
	private int id_num;
	private int id_card;
	
	//初始化，数据库连接
	public void init(){
		dicDataBaseOperation = new dictionaryDB();
		dicDataBaseOperation.Init();
		try{
			continueLoop = true;
			port = 8888;
			mySocket = new ServerSocket(port);
			//System.out.println("1.1");
			sk = mySocket.accept();
			//System.out.println("1.2");
			in = new BufferedReader (new InputStreamReader(sk.getInputStream ()));
			out = new PrintWriter (new BufferedWriter(new OutputStreamWriter(
				sk.getOutputStream ())), true);
			id_num = dicDataBaseOperation.searchUserID();
			id_card = dicDataBaseOperation.searchCardID();
			//System.out.println(id_card);
			
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	//主循环函数
	public void mainLoop(){
		String ss = new String();
		try{
			while(continueLoop){
				ss = in.readLine();
				//ss = "SEARCH CARD 15850786062";
				System.out.println(ss);
				if(ss.startsWith("SEARCH")){
					ss = ss.substring(7, ss.length());
					//table word
					if(ss.startsWith("WORD")){
						ss = ss.substring(5, ss.length());
						out.println(searchWord(ss));
						//System.out.println(searchWord(ss));
					}
					//table user
					else if(ss.startsWith("USER")){
						ss = ss.substring(5, ss.length());
						if(ss.endsWith("LOGIN")){
							ss = ss.substring(0, ss.length()-6);
							out.println(searchIDLogin(ss));
							//System.out.println(searchIDLogin(ss));
						}
					}
					else if(ss.startsWith("ONLINE")){
						ArrayList<String> result = searchOnlineUsers();
						for(int i = 0;i < result.size(); i++){
							out.println(result.get(i));
							//System.out.println(result.get(i));
						}
						out.println("end");
						//System.out.println("end");
						//continueLoop = false;
					}
					else{
						ss = ss.substring(5);
						ArrayList<WordCard> cards = searchCards(ss);
						for(int i = 0; i < cards.size(); i++){
							out.println(cards.get(i).from_account);
							out.println(cards.get(i).word);
							System.out.println(cards.get(i).word);
							out.println(bytes2Hex(cards.get(i).picture));
						}
						out.println("*");
					}
				}
				else if(ss.startsWith("ADD")){
					ss = ss.substring(4, ss.length());
					//table word
					if(ss.startsWith("WORD")){
						ss = ss.substring(5, ss.length());
						addWordLikes(ss);
						//out.println("finished");
					}
					//table user
					else if(ss.startsWith("USER")){
						ss = ss.substring(5, ss.length());
						String account = ss.substring(0, ss.indexOf(" "));
						String password = ss.substring(ss.indexOf(" ") + 1);
						out.println(addUser(account, password));
						System.out.println(addUser(account, password));
					}
					else if(ss.startsWith("CARD")){
						WordCard new_card = new WordCard();
						new_card.from_account = in.readLine();
						System.out.println(new_card.from_account);
						new_card.to_account = in.readLine();
						System.out.println(new_card.to_account);
						new_card.word = in.readLine();
						System.out.println(new_card.word);
						new_card.picture = hex2Bytes(in.readLine());
						addNewCard(new_card);
					}
				}
				else if(ss.startsWith("DEC")){
					ss = ss.substring(4, ss.length());
					//table word
					if(ss.startsWith("WORD")){
						ss = ss.substring(5, ss.length());
						decWordLikes(ss);
						//out.println("finished");
					}
				}
				else if(ss.startsWith("CLOSE"))
					continueLoop = false;
				else if(ss.startsWith("SET")){
					ss = ss.substring(4, ss.length());
					if(ss.endsWith("ONLINE")){
						ss = ss.substring(0, ss.length() - 7);
						setUserOnline(ss);
						//continueLoop = false;
					}
					else{
						ss = ss.substring(0, ss.length() - 8);
						//System.out.println(ss);
						setUserOffline(ss);
						//continueLoop = false;
					}
				}
			}
			//sk.close();
		}catch (Exception e){
			System.out.println(e);
		}
	}
	
	//让某用户上线
	public void setUserOnline(String account){
		dicDataBaseOperation.setUserOnline(account);
	}
	
	//让某用户下线
	public void setUserOffline(String account){
		System.out.println(account);
		dicDataBaseOperation.setUserOffline(account);
	}
	
	//找到现在在线用户
	public ArrayList<String> searchOnlineUsers(){
		return dicDataBaseOperation.SearchOnlineUsers();
	}
	
	//查询某账户的密码
	public String searchIDLogin(String account){
		String temp = dicDataBaseOperation.searchPassword(account);
		StringBuilder resultBuilder = new StringBuilder();
		if(temp.charAt(0) == '*'){
			resultBuilder.append("N");
		}
		else{
			resultBuilder.append("Y ");
			resultBuilder.append(temp);
		}
		String result = new String(resultBuilder);
		return result;
	}
	
	//查询某个单词的点赞书
	public String searchWord(String word){
		StringBuilder resultBuilder = new StringBuilder();
		ArrayList<Integer> numOfLikes = dicDataBaseOperation.searchNumberOfLikes(word);
		
		resultBuilder.append(numOfLikes.get(0).toString());
		resultBuilder.append(" ");
		resultBuilder.append(numOfLikes.get(1).toString());
		resultBuilder.append(" ");
		resultBuilder.append(numOfLikes.get(2).toString());
		
		String result = new String(resultBuilder);
		return result;
	}
	
	//在数据库添加账户
	public String addUser(String account, String password){
		id_num++;
		if(dicDataBaseOperation.addUser(id_num, account, password).equals("N")){
			id_num--;
			return "N";
		}
		else
			return "Y";
	}
	
	//添加新的单词卡
	public String addNewCard(WordCard new_card){
		id_card++;
		if(dicDataBaseOperation.addCard(id_card, new_card).equals("N")){
			id_card--;
			return "N";
		}
		else
			return "Y";
	}
	
	//找到所有发给某用户的单词卡
	public ArrayList<WordCard> searchCards(String to_account){		
		ArrayList<WordCard> cards = dicDataBaseOperation.searchCards(to_account);
		for(int i = 0; i < cards.size(); i++){
			System.out.println(cards.get(i).word);
		}
		
		return dicDataBaseOperation.searchCards(to_account);
		
	}
	
	//为某单词添加一个赞
	public void addWordLikes(String type){
		int i, mode = 0;
		
		if(type.endsWith("Bing")){
			type = type.substring(0, type.length() - 5);
			i = 1;
		}
		else if(type.endsWith("Youdao")){
			type = type.substring(0, type.length() - 7);
			i = 2;
		}
		else{
			type = type.substring(0, type.length() - 8);
			i = 3;
		}
		
		System.out.println(type + i);
		
		dicDataBaseOperation.changeNumberOfLikes(type, i, mode);	
		
		System.out.println("finished");
	}
	
	//为某单词取消一个赞
	public void decWordLikes(String type){
		int i, mode = 1;
		
		if(type.endsWith("Bing")){
			type = type.substring(0, type.length() - 5);
			i = 1;
		}
		else if(type.endsWith("Youdao")){
			type = type.substring(0, type.length() - 7);
			i = 2;
		}
		else{
			type = type.substring(0, type.length() - 8);
			i = 3;
		}
		
		dicDataBaseOperation.changeNumberOfLikes(type, i, mode);
	}
	
	//从bytes到string的转换
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
	
	//从string到bytes的转换
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
	
	public static void main(String[] arges){
		Server dicServer = new Server();	
		dicServer.init();
		dicServer.mainLoop();
	}
}
