package com.emcat.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;

import com.emcat.commom.Constants;
import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.Contained;
import com.emcat.einterface.Container;
import com.emcat.einterface.Loader;

public class SimpleLoader implements Loader,Contained{

	Container container;
	public Servlet loadServlet(String servletName, HttpRequest req,
			HttpResponse res) {
		// TODO Auto-generated method stub
		String url = req.getUri();
		servletName = url.substring(url.indexOf("/",1)+1,url.length());
		File file = new File(Constants.WEB_ROOT);
		URLClassLoader loader = null;
		URL[] urls = new URL[1];  
        URLStreamHandler streamHandler = null;  
        String respository;
        Servlet servlet = null;
		try {
			respository = file.getCanonicalPath();
			urls[0] = file.toURL();
			loader = new URLClassLoader(urls);
			Class cls = loader.loadClass(servletName);
			Object obj = cls.newInstance();
			servlet  = (Servlet)obj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return servlet;
	}

	@Override
	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		File file = new File(Constants.WEB_ROOT);
		URLClassLoader loader = null;
		URL[] urls = new URL[1];  
        URLStreamHandler streamHandler = null;  
        try {
			urls[0] = file.toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loader = new URLClassLoader(urls);
		return loader;
	}

	@Override
	public Container getContainer() {
		// TODO Auto-generated method stub
		return container;
	}

	@Override
	public void setContainer(Container container) {
		// TODO Auto-generated method stub
		this.container = container;
	}


}
