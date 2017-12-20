package WebCrawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler{
	
	public void Crawler_Bing(String word) throws IOException {
		
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
    
		word = word.replaceAll(" ","+");

		//根据查找单词构造查找地址
		HttpGet getWordMean = new HttpGet("http://cn.bing.com/dict/search?q=" + word);
		//取得返回的网页源码
		CloseableHttpResponse response = httpClient.execute(getWordMean);

		String result = EntityUtils.toString(response.getEntity());//获得应答报文
		response.close();
		
		//注意(?s)，意思是让'.'匹配换行符，默认情况下不匹配
		Pattern searchMeanPattern = Pattern.compile("(?s)<div class=\"qdef\">.*?<ul>(.*?)</ul>");
		Matcher m1 = searchMeanPattern.matcher(result); //m1是获取包含翻译的整个<div>的
	
	     if (m1.find()) {
	         String means = m1.group(1);//所有解释，包含网页标签
	         Pattern getProp = Pattern.compile("(?m)<span class=\"pos\">(.*?)</span>");
	         Pattern getTrans = Pattern.compile("(?m)<span>(.*?)</span>");

         Matcher m2 = getTrans.matcher(means);
         Matcher m3 = getProp.matcher(means);
         
         Data.MainData.BingTransText.setText("");
         while (m2.find()&&m3.find()) {
             //在Java中(.*?)是第1组，所以用group(1)
        	 
        	 Data.MainData.bingTranslation.add(m3.group(1) + "\t");
        	 Data.MainData.bingTranslation.add(m2.group(1));
        	 Data.MainData.bingTranslation.add("\n");
//        	 Data.MainData.BingTransText.append(m3.group(1) + "\t");
//        	 Data.MainData.BingTransText.append(m2.group(1));
//         	 Data.MainData.BingTransText.append("\n");
        	
         }
     } else {
    	 Data.MainData.bingTranslation.add("未查找到释义");
    	 //Data.MainData.BingTransText.setText("未查找到释义");
     }
 }
	 
   public void Crawler_Youdao(String word) throws IOException {
	   
	   
	   	CloseableHttpClient httpClient = HttpClients.createDefault();

        word = word.replaceAll(" ", "+");

        //根据查找单词构造查找地址
        HttpGet getWordMean = new HttpGet("http://dict.youdao.com/w/eng/" + word + "/#keyfrom=dict2.index");
       
        //取得返回的网页源码
        CloseableHttpResponse response = httpClient.execute(getWordMean);
        String result = EntityUtils.toString(response.getEntity());
        response.close();
        
        //注意(?s)，意思是让'.'匹配换行符，默认情况下不匹配
        Pattern searchMeanPattern = Pattern.compile("(?s)<div class=\"trans-container\">.*?<ul>.*?</div>");
        Matcher m1 = searchMeanPattern.matcher(result); //m1是获取包含翻译的整个<div>的

        if (m1.find()) {
            String means = m1.group();//所有解释，包含网页标签
            Pattern getTrans = Pattern.compile("(?m)<li>(.*?)</li>"); //(?m)代表按行匹配
            Matcher m2 = getTrans.matcher(means);

            //Data.MainData.YoudaoTransText.setText("");
            while (m2.find()) {
                
            	String str = m2.group(1);
            	String meaning = str.split("\\.")[0] + ".\t" + str.split("\\.")[1];
            	 Data.MainData.youdaoTranslation.add(meaning + "\n");
            	//Data.MainData.YoudaoTransText.append(meaning + "\n");
            }
        } else {
        	//Data.MainData.YoudaoTransText.setText("未查找到释义");
        	 Data.MainData.youdaoTranslation.add("未查找到释义");
        }
    }
   
   public void Crawler_Jinshan(String word) throws IOException {
	   Data.MainData.jinshanTranslation = new ArrayList<String>();
	   
       CloseableHttpClient httpClient = HttpClients.createDefault();

       word = word.replaceAll(" ","+");

       //根据查找单词构造查找地址
       HttpGet getWordMean = new HttpGet("http://www.iciba.com/" + word);
      
       //取得返回的网页源码
       CloseableHttpResponse response = httpClient.execute(getWordMean);

       String result = EntityUtils.toString(response.getEntity());//获得应答报文
       response.close();
       
       //注意(?s)，意思是让'.'匹配换行符，默认情况下不匹配
       Pattern searchMeanPattern = Pattern.compile("(?s)<ul class=\"base-list switch_part\" class=\"\">(.*?)</ul>");
       Matcher m1 = searchMeanPattern.matcher(result); //m1是获取包含翻译的整个<ul>的

       if (m1.find()) {
       	
       	// imp: 将 \n 换为空格 否则读不出来
       	String means = m1.group(1).trim().replace('\n', ' ');
       	
       	//提一个词性以及其解释的块
       	Pattern getTrans = Pattern.compile("<li class=\"clearfix\">\\s*<span class=\"prop\">(.*?)</span>\\s*<p>(.*?)</p>\\s*</li>");
       	Matcher m2 = getTrans.matcher(means);
       	
       	Data.MainData.JinshanTransText.setText("");
       	while(m2.find()) {
       		String prop = m2.group(1);
       		String meaningList = m2.group(2);
       		
       		Pattern extractMeaning = Pattern.compile("<span>(.*?)</span>");
       		Matcher m3 = extractMeaning.matcher(meaningList);
      
       	   Data.MainData.jinshanTranslation.add(prop + "\t");
       		//Data.MainData.JinshanTransText.append(prop + "\t");
       		String trans = new String("");
       		while(m3.find()) {
       			trans = trans + m3.group(1);
       		
       		}
       		
       		Data.MainData.jinshanTranslation.add(trans + "\n");
       		//Data.MainData.JinshanTransText.append(trans + "\n");
       	}	
       
       }else {
    	   //Data.MainData.JinshanTransText.setText("未查找到释义");
    	   Data.MainData.jinshanTranslation.add("未查找到释义");
       }
    }
}
