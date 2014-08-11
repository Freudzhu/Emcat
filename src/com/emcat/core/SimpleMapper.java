package com.emcat.core;

import javax.servlet.ServletException;

import com.emcat.connector.HttpRequest;
import com.emcat.einterface.Contained;
import com.emcat.einterface.Container;
import com.emcat.einterface.Mapper;
import com.emcat.einterface.Wrapper;

public class SimpleMapper implements Mapper,Contained{
	
	Container container ;
	
	public SimpleMapper(Container c){
		this.container = c;
		
	}
	
	@Override
	public Wrapper mapper(HttpRequest req) {
		// TODO Auto-generated method stub
		String servletUrl = req.getUri();
		Wrapper wrapper = null;
		if(servletUrl!=null){
			String servletName = ((SimpleContext)container).getServletMap().get(servletUrl);
			try {
				wrapper  = (Wrapper) ((SimpleContext)container).findChild(servletName);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return wrapper;
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
