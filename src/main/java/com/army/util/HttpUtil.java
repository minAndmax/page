package com.army.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

import scala.util.Random;


@Component
public class HttpUtil extends Thread{
	private static String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506; customie8)";
	
	  @Override
	    public void run() {
	    	try {
	    		while(true) {
	    			
	    			Thread.sleep(new Random().nextInt(10000)+500);
	    			sendPost(url,"utf-8");
	    		}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	  
	    private String url;
	    
	    public HttpUtil(String url) {
	    	this.url = url;
	    }
	    
	public HttpUtil() {}

	// HTTP GET request
	public static String sendGet(String url, String charset) throws Exception {
		URL realurl = new URL(url);
		HttpURLConnection con = (HttpURLConnection) realurl.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
		String inputLine;
		StringBuffer result = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			result.append(inputLine);
			result.append("\r\n");
		}
		in.close();
		con.disconnect();
		return result.toString();
	}
	
	 public static String sendPost(String url,String charset) throws Exception {
			
	        URL realurl = new URL(url);
	        
	        HttpURLConnection con = (HttpURLConnection) realurl.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("User-Agent", USER_AGENT);
//	        StringBuffer buffer = new StringBuffer();
	        con.setDoOutput(true);
	        con.setReadTimeout(50000);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//	        wr.writeBytes(buffer.toString());
	        wr.flush();
	        wr.close();
	// 
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
	        String inputLine;
	        StringBuffer result = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	            result.append(inputLine);
	            result.append("\r\n");
	        }
	        in.close();
	        con.disconnect();
	        System.gc();
	        return null;
	    }
}