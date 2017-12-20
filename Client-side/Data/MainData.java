package Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import WebCrawler.WebCrawler;
import Data.WordCardInfo;

public class MainData {
	
	/* --------------------- UI ----------------------*/
	/* login frame data */
	public static JFrame loginFrame;
	public static JLabel ImageLabel;
	public static JPanel userNamePanel;
	public static JPanel userPasswordPanel;
	public static JLabel userNameLabel;
	public static JLabel userPasswordLabel;
	public static JButton confirmButton;
	public static JButton registerButton;
	public static TextField userNameText;
	public static TextField userPasswordText;
	public static boolean isLogin;
	public static String currentUser;
	
	/* friend frame data */
	public static JFrame friendFrame;
	@SuppressWarnings("rawtypes")
	public static JList friendList;
	public static JPanel friendPanel;
	public static JButton sendButton;
	public static JLabel friendSysLabel;
	
	/* email frame data */
	public static JFrame emailFrame;
	@SuppressWarnings("rawtypes")
	public static JList emailList;
	public static JPanel emailPanel;
	public static JPanel emailContentPanel;
	public static JLabel wordNameLabel;
	public static JLabel wordCardLabel;
	public static JLabel emailSysLabel;
	public static JLabel emailRefreshLabel;
	
	
	/* register frame data */
	public static JButton registerCancelButton;
	public static JButton registerSubmitButton;
	public static JFrame registerFrame;
	public static JPanel registerNamePanel;
	public static JPanel registerPasswordPanel;
	public static JPanel registerPasswordConfirmPanel;
	public static JLabel registerNameLabel;
	public static JLabel registerPasswordLabel;
	public static JLabel registerPasswordPromptLabel;
	public static JLabel registerPasswordConfirmLabel;
	public static JLabel registerPasswordConfirmPromptLabel;
	public static JLabel registerImageLabel;
	public static TextField registerNameText;
	public static TextField registerPasswordText;
	public static TextField registerPasswordConfirmText;
	
	/* main frame data */
	public static JFrame frame;
	public static JPanel choosePanel;
	public static JPanel translationPanel;
	public static JPanel InputPanel;
	public static JPanel BingTransPanel;
	public static JPanel JinshanTransPanel;
	public static JPanel YoudaoTransPanel;
	
	public static JLabel LikeLabel1;
	public static JLabel LikeLabel2;
	public static JLabel LikeLabel3;
	public static JLabel userLoginLabel;
	public static JLabel friendLabel;
	public static JLabel emailLabel;
	
	public static JLabel BingLabel;
	public static JLabel JinshanLabel;
	public static JLabel YoudaoLabel;
	public static JLabel BingEnglishTransLabel;
	public static JLabel BingChineseTransLabel;
	public static JLabel BingPropTransLabel;
	public static JLabel YoudaoEnglishTransLabel;
	public static JLabel YoudaoChineseTransLabel;
	public static JLabel JinshanEnglishTransLabel;
	public static JLabel JinshanChineseTransLabel;
	public static JLabel JinshanPropTransLabel;	
	public static JTextArea BingTransText;
	public static JTextArea YoudaoTransText;
	public static JTextArea JinshanTransText;
	
	public static JCheckBox checkBox1;
	public static JCheckBox checkBox2;
	public static JCheckBox checkBox3;

	public static JButton clickbutton;	
	public static TextField InputText;
	
	public static String []names;
	public static int BingPos_Y;
	public static int JinshanPos_Y;
	public static int YoudaoPos_Y;
	
	/* --------------- WebCrawler DATA-STRUCTURE --------------*/
	
	public static WebCrawler crawler;
	public static ArrayList<String>  youdaoTranslation;
	public static ArrayList<String>  bingTranslation;
	public static ArrayList<String>  jinshanTranslation;
	
	/* --------------- SEARCH DATA-STRUCTURE --------------*/
	
	public static ArrayList<WordCardInfo> wordCardList;
	public static byte[] snap;
	public static Vector<String> onlineUsers;
    public static String searchWord;
//    public static String searchUserIDtoServer;
//    public static String searchWordtoServer;
//    public static String addUsertoServer;
//    public static String addSearchWebtoServer;
//    public static String delSearchWebtoServer;
}
