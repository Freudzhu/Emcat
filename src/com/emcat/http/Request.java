package com.emcat.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Request {
	
	private InputStream inputStream ;
	
	private String uri;
	
	public Request(InputStream inputStream){
		this.inputStream = inputStream;
	}
	public Request(){
		
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	private String generateUri(){
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String source;
		try {
			source = br.readLine();
			int index1 = source.indexOf(" ");
			if(index1 != -1){
				int index2 = source.indexOf(" ", index1+1);
				if(index2 != -1){
					return source.substring(index1+1,index2);
				}
				return null;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void parse(){
		uri = generateUri();
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
