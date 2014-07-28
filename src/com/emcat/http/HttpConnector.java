package com.emcat.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.emcat.process.ServletProcess;
import com.emcat.process.StaticResourceProcess;

public class HttpConnector implements Runnable{
	
	private int LISTEN_PORT = 8089;
	private int BLACK_LOG = 1;
	
	private boolean shutDown = true;
	
	private String scheme = "http";

	public String getScheme() {
	    return scheme;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ServerSocket ssk = null;
		try {
			ssk = new ServerSocket(LISTEN_PORT,BLACK_LOG,InetAddress.getByName("127.0.0.1"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(shutDown){
			
			Socket sk = null;
			OutputStream output = null;
			InputStream input = null;
			
			try {
				sk = ssk.accept();
				
				//handle the socket to processor
				HttpProcessor processor = new HttpProcessor(this);
				processor.process(sk);
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		System.out.println("exit");
		
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}

}
