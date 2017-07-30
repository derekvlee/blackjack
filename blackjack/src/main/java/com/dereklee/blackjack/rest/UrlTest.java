package com.dereklee.blackjack.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class UrlTest {
	
    private static final int port = 9876;
    private static final String uri = "/resourcesBank/balance/511";
    private static final String url = "http://localhost:" + port + uri;
    
	public static void main(String[] args) {
		
		UrlTest ut = new UrlTest();
		String response = ut.request(url);
		System.out.println("response: " + response);
	}

	private String request(String urlStr) {
		String response = "";
		try {
			URL url = new URL(urlStr);
			//HttpURLConnection conn = new HttpURLConnection(url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String chunk = null;
			while((chunk = br.readLine())!=null) {
				response += chunk;			
			}
			br.close();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
}
