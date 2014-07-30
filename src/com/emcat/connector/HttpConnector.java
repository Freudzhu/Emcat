package com.emcat.connector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

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
		System.out.println("enter the connector");
		while(shutDown){
			
			Socket sk = null;
			OutputStream output = null;
			InputStream input = null;
			
			try {
				sk = ssk.accept();
				
				//handle the socket to processor
				HttpProcessor processor = createProcess();
				if(processor == null){
					System.out.println("the processor is full");
					sk.close();
					continue;
				}
				processor.assign(sk);
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("exit");
		
	}
	public void recycle(HttpProcessor hp){
		processors.push(hp);
	}
	
	private HttpProcessor createProcess() {
		// TODO Auto-generated method stub
		if(processors.size() > 0){
			HttpProcessor hp = processors.pop();
			return hp;
		}
		else if(MAX_PROCESSORS > 0 && curProcessors < MAX_PROCESSORS){
			HttpProcessor hp = newHttpProcessor();
			return hp;
		}else {
			//MAX_PROCESSORS be a negative represent always produce the httpprocessor
			if(MAX_PROCESSORS < 0)
				return newHttpProcessor();
			else {
				return null;
			}
		}
		
	}

	public void start(){
		while(curProcessors < MIN_PROCESSORS){
			HttpProcessor hp = newHttpProcessor();
			recycle(hp);
		}
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	//hold the httpprocessor for reuse
	Stack<HttpProcessor> processors = new Stack<HttpProcessor>();
	private int MIN_PROCESSORS = 3;
	private int MAX_PROCESSORS = 4;
	private int curProcessors = 0;
	
	private HttpProcessor newHttpProcessor(){
		HttpProcessor processor = new HttpProcessor(this,curProcessors);
		curProcessors++;
		processor.start();
		return processor;
	}

}
