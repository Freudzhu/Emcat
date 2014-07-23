package com.emcat.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.commom.Constants;

public class Response {
	
	private Request request;
	
	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response(){
		
	}
	private OutputStream outputStream;

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	public void sendStaticResource(){
		String url = request.getUri();
		File file = new File(Constants.WEB_ROOT,url);
		if(file.exists()){
			//file exist,then write the http header into the Stream
			PrintWriter pw = new PrintWriter(outputStream,true);
			pw.println("HTTP/1.1 200 OK");
			pw.println("Content-Type: text/html;charset=ISO-8859-1");
			pw.println();
	
			try {
				FileReader bf = new FileReader(file);
				char[] tmp = new char[1024];
				while (bf.read(tmp)!=-1) {
					pw.write(tmp, 0, tmp.length);
					
				}
				pw.flush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(outputStream!=null){
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else {
			StringBuffer errMsg = new StringBuffer();
			errMsg.append("HTTP/1.1 404 Not Found\r\n");
			errMsg.append("Content-Type: text/html;charset=ISO-8859-1");
			try {
				outputStream.write(errMsg.toString().getBytes());
				outputStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if(outputStream!=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
