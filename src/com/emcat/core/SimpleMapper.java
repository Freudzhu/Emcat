package com.emcat.core;

import javax.servlet.ServletException;

import com.emcat.connector.HttpRequest;
import com.emcat.einterface.Contained;
import com.emcat.einterface.Container;
import com.emcat.einterface.Mapper;
import com.emcat.einterface.Wrapper;
import com.emcat.lifecycle.LifeCycle;
import com.emcat.lifecycle.LifeCycleException;
import com.emcat.lifecycle.LifeCycleSupport;
import com.emcat.lifecycle.LifecycleListener;

public class SimpleMapper implements Mapper,Contained,LifeCycle{
	
	Container container ;
	LifeCycleSupport lifeCycleSupport; 
	
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

	@Override
	public void start() throws LifeCycleException {
		// TODO Auto-generated method stub
		System.out.println("Mapper start Loading");
	}

	@Override
	public void stop() throws LifeCycleException {
		// TODO Auto-generated method stub
		System.out.println("Mapper stop Loading");
	}

	@Override
	public void addLifeCycleListener(LifecycleListener listener)
			throws LifeCycleException {
		// TODO Auto-generated method stub
		lifeCycleSupport.addLifeCycleListener(listener);
	}

	@Override
	public void removeLifeCycleListener(LifecycleListener listener)
			throws LifeCycleException {
		// TODO Auto-generated method stub
		lifeCycleSupport.removeLifeCycleListener(listener);
	}

}
