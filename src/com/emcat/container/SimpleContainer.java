package com.emcat.container;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.emcat.commom.Constants;
import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.Container;

public class SimpleContainer implements Container{

	@Override
	public void invoke(HttpRequest req, HttpResponse res) {

		String url = req.getUri();
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
			servlet.service(req, res);
			
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
	

}
