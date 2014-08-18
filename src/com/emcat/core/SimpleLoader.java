package com.emcat.core;

import com.emcat.einterface.Contained;
import com.emcat.einterface.Container;
import com.emcat.lifecycle.LifeCycle;
import com.emcat.lifecycle.LifeCycleException;
import com.emcat.lifecycle.LifeCycleSupport;
import com.emcat.lifecycle.LifecycleListener;
import com.emcat.loader.Loader;
import com.emcat.loader.WebAppClassLoader;
import com.emcat.logger.Logger;

public class SimpleLoader implements Loader,Contained,LifeCycle{

	Container container;
	LifeCycleSupport lifeCycleSupport; 
	Logger logger;
	WebAppClassLoader webAppClassLoader;
	
	public SimpleLoader(Container container){
		this.container = container;
		webAppClassLoader = new WebAppClassLoader(null);
		webAppClassLoader.addRepository("/web-inf/classes");
	}
	
	@Override
	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		return webAppClassLoader;
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
		logger.log("Load start Loading");
		webAppClassLoader.start();
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
		// TODO Auto-generated method stub
		if(logger!=null)
			return logger;
		Logger logger = getContainer().getLogger();
		if(logger!=null){
			return logger;
		}
		return null;
	}

	public void setLogger(Logger logger) {
		// TODO Auto-generated method stub
		this.logger = logger;
	}


}
