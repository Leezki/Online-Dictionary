import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.stream.FileImageOutputStream;

import WordCard.WordCard;

//数据库类
public class dictionaryDB {
	private String url = "jdbc:mysql://localhost:3306/online-dictionary?characterEncoding=utf8&useSSL=true";
	private String username = "root";
	private String password = "940623";
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql;
	private Integer card_ID;
	
	//初始化，连接数据库
	public void Init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url , username , password);
			card_ID = 0;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error! Cann't get connected to the database");
			e.printStackTrace();
		}
	}
	
	//查询某单词点赞数
	public ArrayList<Integer> searchNumberOfLikes(String wordToBeLookedUp){		
		ArrayList<Integer> numOfLikes = new ArrayList<Integer>();
		try {
			sql = "Select * from likes where Word = ?";
			pst = conn.prepareStatement(sql);
			pst.setObject(1, wordToBeLookedUp); 
			rs = pst.executeQuery();
		 
			if(!rs.next()){
				sql = "Insert into likes(Word, Bing, Youdao, Jinshan) values(?, 0, 0, 0)" ;
				pst = conn.prepareStatement(sql);
				pst.setObject(1, wordToBeLookedUp);
				pst.execute();
				numOfLikes.add(0);
				numOfLikes.add(0);
				numOfLikes.add(0);
				return numOfLikes;
			}		
			
			numOfLikes.add(rs.getInt(2));
			numOfLikes.add(rs.getInt(3));
			numOfLikes.add(rs.getInt(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numOfLikes;
	}
	
	//增加或减少某单词点赞数
	public void changeNumberOfLikes(String wordToBeLookedUp, int i, int mode){		
		try {
			//increse 1
			if(mode == 0){
				//Baidu
				if(i == 1){
					sql = "UPDATE likes SET Bing = Bing + 1 WHERE Word = ?";
				}
				//Youdao
				else if(i == 2){
					sql = "UPDATE likes SET Youdao = Youdao + 1 WHERE Word = ?";
				}
				//Jinshan
				else{
					sql = "UPDATE likes SET Jinshan = Jinshan + 1 WHERE Word = ?";
				}
			}
			//decrease 1
			else{
				//Baidu
				if(i == 1){
					sql = "UPDATE likes SET Bing = Bing - 1 WHERE Word = ?";
				}
				//Youdao
				else if(i == 2){
					sql = "UPDATE likes SET Youdao = Youdao - 1 WHERE Word = ?";
				}
				//Jinshan
				else{
					sql = "UPDATE likes SET Jinshan = Jinshan - 1 WHERE Word = ?";
				}
			}
			pst = conn.prepareStatement(sql);
			pst.setObject(1, wordToBeLookedUp); 
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//查询某用户密码
	public String searchPassword(String account){
		String result = new String();
		try{
			sql = "Select password from user where account = ?";
			pst = conn.prepareStatement(sql);
			pst.setObject(1, account); 
			rs = pst.executeQuery();
			if(!rs.next()){
				result = "*";
			}
			else{
				result = rs.getString(1);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
		}
		return result;
	}
	
	//查询目前最大的用户ID
	public int searchUserID(){
		int result = 0;
		try {
			sql = "Select max(ID) from user";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//查询目前最大的单词卡ID
	public int searchCardID(){
		int result = 0;
		try {
			sql = "Select max(ID) from wordcard";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			rs.next();		
			result = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//添加用户
	public String addUser(int ID, String account, String password){
		String result = new String();
		try {
			sql = "SELECT * FROM user where account = ?";
			pst = conn.prepareStatement(sql);
			pst.setObject(1, account); 
			rs = pst.executeQuery();
		 
			if(!rs.next()){
				sql = "Insert into user(ID, name, password, account, online) values(?, ?, ?, ?, ?)" ;
				pst = conn.prepareStatement(sql);
				pst.setObject(1, ID);
				pst.setObject(2, account);
				pst.setObject(3, password);
				pst.setObject(4, account);
				pst.setObject(5, 1);
				pst.execute();
				result = "Y";
			}
			else
				result = "N";				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
	}
	
	//查询所有在线用户
	public ArrayList<String> SearchOnlineUsers(){
		ArrayList<String> result = new ArrayList<String>();
		try {
			sql = "SELECT name, account FROM user where online = 1";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
		 
			while(rs.next()){
				String temp = rs.getString(1) + "(" + rs.getString(2) + ")";
				result.add(temp);
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//设置某用户在线
	public void setUserOnline(String account){
		try {
			sql = "UPDATE user SET online = 1 where account = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, account);
			pst.execute();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//设置某用户下线
	public void setUserOffline(String account){
		try {
			sql = "UPDATE user SET online = 0 where account = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, account);
			pst.execute();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// 将图片插入数据库
	public String addCard(int id_card, WordCard new_card ){
        String result = new String();
		try { 
            sql = "Insert into wordcard(ID, from_account, to_account, card, word) values(?, ?, ?, ?, ?)";    
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id_card);   
            pst.setString(2, new_card.from_account);
            pst.setString(3, new_card.to_account);
            pst.setObject(4, new_card.picture);
            pst.setString(5, new_card.word);
           
            boolean success = pst.execute();
            if(success == true)
            	result = "Y";
            else
            	result = "N";
        } catch (Exception e) {
            e.printStackTrace();
        } 
		return result;
    }

    // 读取数据库中图片
    public ArrayList<WordCard> searchCards(String to_id) {
    	ArrayList<WordCard> cards = new ArrayList<WordCard>();
        try {
            String sql = "select * from wordcard where to_account = ?";
            pst = conn.prepareStatement(sql);
            pst.setObject(1, to_id);
            rs = pst.executeQuery();
            WordCard card = new WordCard();
            while (rs.next()) {
            	card = new WordCard();
            	InputStream in = rs.getBinaryStream("card");
                ByteArrayOutputStream output = new ByteArrayOutputStream();  
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = in.read(buf)) != -1) {
                    output.write(buf, 0, len);
                }
                card.picture = output.toByteArray();	
                card.from_account = rs.getString(2);
                card.to_account = to_id;
                card.word = rs.getString(5);
                //System.out.println(card.word);
                cards.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        for(int i = 0; i < cards.size(); i++){
			System.out.println(cards.get(i).word);
		}
        return cards;
    }
	
	/*public static void main(String args[]){
		dictionaryDB db = new dictionaryDB();
		db.Init();
		//db.readImage2DB();
		//db.readDB2Image("13770570809");
	}*/
}
