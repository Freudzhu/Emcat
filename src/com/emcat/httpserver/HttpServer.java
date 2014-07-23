package com.emcat.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.emcat.http.Request;
import com.emcat.http.Response;

public class HttpServer {
	
	private int LISTEN_PORT = 8089;
	private int BLACK_LOG = 1;
	private String SHUTDOWN = "SHUTDOWN";
	
	private boolean shutDown = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpServer httpServer = new HttpServer();
		httpServer.await();
	}
	
	public void await(){
		
		ServerSocket ssk = null;
		try {
			System.out.println("Server init");
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
				input = sk.getInputStream();
				output = sk.getOutputStream();
				Request request = new Request();
				request.setInputStream(input);
				request.parse();
				Response response = new Response();
				response.setRequest(request);
				response.setOutputStream(output);
				response.sendStaticResource();
				
				if(sk!=null){
					sk.close();
				}
				
				shutDown = !request.getUri().equals(SHUTDOWN);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		System.out.println("exit");
		
	}

}
