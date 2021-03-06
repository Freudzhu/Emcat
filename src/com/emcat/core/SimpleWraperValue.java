package com.emcat.core;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.Contained;
import com.emcat.einterface.Container;
import com.emcat.einterface.Value;
import com.emcat.lifecycle.LifeCycle;
import com.emcat.lifecycle.LifeCycleException;
import com.emcat.lifecycle.LifeCycleSupport;
import com.emcat.lifecycle.LifecycleListener;
import com.emcat.loader.Loader;
import com.emcat.logger.Logger;

public class SimpleWraperValue implements Value,Contained,LifeCycle{
	
	Container container;
	LifeCycleSupport lifeCycleSupport;
	Logger logger;

	@Override
	public void invoke(HttpRequest req, HttpResponse res) {
		
		StandrdWrapper wrapper = (StandrdWrapper) getContainer();
		Servlet servlet = null;
		servlet = wrapper.allocte();
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

	@Override
	public void start() throws LifeCycleException {
		// TODO Auto-generated method stub
		logger = getLogger();
		logger.log("Value start Loading");
	}

	@Override
	public void stop() throws LifeCycleException {
		// TODO Auto-generated method stub
		
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
	public Logger getLogger() {
		if(logger!=null)
			return logger;
		Logger logger = getContainer().getLogger();
		if(logger!=null){
			return logger;
		}
		return null;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
