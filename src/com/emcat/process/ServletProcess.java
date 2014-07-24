package com.emcat.process;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.emcat.commom.Constants;
import com.emcat.http.Request;
import com.emcat.http.Response;

public class ServletProcess implements Process{
	private Request request;
	private Response response;

	public ServletProcess(Request request,Response response){
		this.request = request;
		this.response = response;
	}
	@Override
	public void process(Request request, Response response) {
		// TODO Auto-generated method stub
		String url = request.getUri();
		String servletName = url.substring(url.indexOf("/",1)+1,url.length());
		
		File file = new File(Constants.WEB_ROOT);
		
		URLClassLoader loader = null;
		
		URL[] urls = new URL[1];  
        URLStreamHandler streamHandler = null;  
        String respository;
		try {
			respository = file.getCanonicalPath();
			urls[0] = file.toURL();
			loader = new URLClassLoader(urls);
			Class cls = loader.loadClass(servletName);
			Object obj = cls.newInstance();
			Servlet servlet = (Servlet)obj;
			servlet.service(request, response);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
      
		
      
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	

}
