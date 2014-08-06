package com.emcat.connector;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import org.apache.catalina.util.RequestUtil;

import com.emcat.einterface.Container;
import com.emcat.process.StaticResourceProcess;

public class HttpProcessor implements Runnable{
	
	HttpConnector connector;
	HttpRequestLine requestLine = new HttpRequestLine();
	HttpRequest request = null;
	HttpResponse response = null;
	HttpHeader header = null;
	int id;//httpprocessor id;

	
	public HttpProcessor(HttpConnector connector,int id){
		this.connector = connector;
		this.id = id;
	}
	
	private boolean ok = true;//indicate that there is no error duringthe process
	private boolean finishResponse=true;//indicate that the finishResponsemethod of the Response interface should be called.
	private boolean keepAlive;//indicates that the connection is persistent
	
	private void process(Socket socket){
		
		SocketInputStream sis = null;
		OutputStream outputStream = null;
		try {
			sis = new SocketInputStream(socket.getInputStream(),2048);
			outputStream = socket.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			ok = false;
		}
		
		keepAlive = true;
		finishResponse = true;
		while(ok&&keepAlive&&!stop&&finishResponse){
			
			request = new HttpRequest();
			response = new HttpResponse(outputStream);
			response.setRequest(request);
			
			
			//parse header and requestUri
			try {
				parseRequestLine(sis);
				parseHeaders(sis);
				if (http11) {
					ackRequest(outputStream);
				}
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				ok = false;
				e1.printStackTrace();
			} catch (EOFException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ok = false;
			}
			
			 
			if(ok){
				if(request.getUri().startsWith("/static")){
					StaticResourceProcess srp = new StaticResourceProcess(request, response);
					srp.process(request, response);
					finishResponse = false;
				
				}else{
					Container container = connector.getContainer();
					container.invoke(request, response);
					finishResponse = false;
				}
				
			}
			if ( "close".equals(response.getHeader("Connection")) ) {      
				keepAlive = false;   
			}
		
		}
		if(socket != null){
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void ackRequest(OutputStream outputStream) {
		// TODO Auto-generated method stub
		if(sendAck){
			try {
				outputStream.write(ack);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void parseRequestLine(SocketInputStream sis) throws ServletException, EOFException {
		// TODO Auto-generated method stub
		try {
			sis.readRequestLine(requestLine);
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new EOFException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String method = null;
		if(requestLine.methodEnd > 0){
			method = new String(requestLine.method,0,requestLine.methodEnd);
		}else{
			throw new ServletException("Missing HTTP request method");
		}
		
		String protocol = null;
		if(requestLine.protocolEnd > 0){
			protocol = new String(requestLine.protocol,0,requestLine.protocolEnd);
		}else{
			throw new ServletException("Missing HTTP request protocol");
		}
		if(protocol.length()==0){
		    protocol = "HTTP/0.9";
		}
		if(protocol.equals("HTTP/1.1")){
			http11 = true;
            sendAck = false;
		}else{
			http11 = false;
            sendAck = false;
            // For HTTP/1.0, connection are not persistent by default,
            // unless specified with a Connection: Keep-Alive header.
            keepAlive = false;
		}
		
		String url = null;
		int question = requestLine.indexOf("?");
		if(question != -1){
			url = new String(requestLine.uri,0,question);
			request.setQueryString(new String(requestLine.uri,question+1,requestLine.uriEnd-question-1));
		}else {
			request.setQueryString(null);
			url = new String(requestLine.uri, 0, requestLine.uriEnd);
		}
		
		
		 // Parse any requested session ID out of the request URI
	    String match = ";jsessionid=";
	    int semicolon = url.indexOf(match);
	    if (semicolon >= 0) {
	      String rest = url.substring(semicolon + match.length());
	      int semicolon2 = rest.indexOf(';');
	      if (semicolon2 >= 0) {
	        request.setRequestedSessionId(rest.substring(0, semicolon2));
	        rest = rest.substring(semicolon2);
	      }
	      else {
	        request.setRequestedSessionId(rest);
	        rest = "";
	      }
	      request.setRequestedSessionURL(true);
	      url = url.substring(0, semicolon) + rest;
	    }
	    else {
	      request.setRequestedSessionId(null);
	      request.setRequestedSessionURL(false);
	    }
		
	    
	    request.setMethod(method);
	    request.setProtocol(protocol);
	    request.setUri(url);
		
		
	}
	 private void parseHeaders(SocketInputStream input) throws ServletException{
		 
		 while (true) {
			 
			 header = new HttpHeader();
			 try{
				 input.readHeader(header);
				} catch (IOException e) {
					e.printStackTrace();
				}
				 if(header.nameEnd == 0){
					 if(header.valueEnd == 0){
						 return ;
					 }else{
						 throw new ServletException("parse head error");
					 }
				 }
				 String name = new String(header.name,0,header.nameEnd);
				 String value = new String(header.value,0,header.valueEnd);
			 
				 request.addHeader(name, value);
			 
			 
				 if(name.equals("cookie")){
					 Cookie[] cookies = RequestUtil.parseCookieHeader(value);
					 for (int i = 0; i < cookies.length; i++) {
				          if (cookies[i].getName().equals("jsessionid")) {
				            // Override anything requested in the URL
				            if (!request.isRequestedSessionIdFromCookie()) {
				              // Accept only the first session id cookie
				              request.setRequestedSessionId(cookies[i].getValue());
				              request.setRequestedSessionCookie(true);
				              request.setRequestedSessionURL(false);
				            }
				         }
				          request.addCookie(cookies[i]);
					 }
				 }else if(name.equals("content-length")){
					 int n = -1;
					 n = Integer.parseInt(value);
					 request.setContentLength(n);
				 }else if (name.equals("content-type")) {
				     request.setContentType(value);
			     }else if(name.equals("expect")){
			    	 if(value.equals("100-continue")){
			    		 sendAck = true;
			    	 }
			     }else if(name.equals("connection")){
			    	 if(value.equals("close")){
			    		 keepAlive = false;
			    		 response.setHeader("Connection", "close");
			    	 }else if(value.equals("keep-alive")){
			    		 keepAlive = true;
			    	 }
			     }
		 }
	 }
	 protected void shutdownInput(InputStream input) {
	        try {
	            int available = input.available();
	            // skip any unread (bogus) bytes
	            if (available > 0) {
	                input.skip(available);
	            }
	        } catch (Throwable e) {
	            ;
	        }
	    }
	 /*
	  * implment the process as a thread,support mutiple acess.
	  */
	 private boolean stop = false;
	 private boolean avaliable = false;
	 private Socket socket ;
	 private boolean http11 = false;
	 
	 /**
	     * True if the client has asked to recieve a request acknoledgement. If so
	     * the server will send a preliminary 100 Continue response just after it
	     * has successfully parsed the request headers, and before starting
	     * reading the request entity body.
	     */
	private boolean sendAck = false;
	
	/**
     * Ack string when pipelining HTTP requests.
     */
    private static final byte[] ack =
        (new String("HTTP/1.1 100 Continue\r\n\r\n")).getBytes();
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!stop){
			Socket socket = await();
			process(socket); 
			connector.recycle(this);
		}
		
	}

	private synchronized Socket await() {
		// TODO Auto-generated method stub
		while(!avaliable){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Socket socket = this.socket;
		avaliable = false;
		notify();
		return socket;
	}
	public synchronized void assign(Socket socket) {
		while(avaliable){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 // Store the newly available Socket and notify our thread
		avaliable = true;
		notify();
		this.socket = socket;
	}
	public void start(){
		Thread thread =  new Thread(this,String.valueOf(id));
		thread.setDaemon(true);
		thread.start();
	}

}