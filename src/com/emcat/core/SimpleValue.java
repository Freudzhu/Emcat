package com.emcat.core;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.Contained;
import com.emcat.einterface.Container;
import com.emcat.einterface.Loader;
import com.emcat.einterface.Value;

public class SimpleValue implements Value,Contained{
	
	Loader loader;
	Container container;

	@Override
	public void invoke(HttpRequest req, HttpResponse res) {
		
		StandrdWrapper wrapper = (StandrdWrapper) getContainer();
		Servlet servlet = null;
		servlet = wrapper.getLoader().loadServlet(null, req, res);
		try {
			servlet.service(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
