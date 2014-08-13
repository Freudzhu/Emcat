package com.emcat.core;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.Contained;
import com.emcat.einterface.Container;
import com.emcat.einterface.Value;
import com.emcat.einterface.Wrapper;
import com.emcat.lifecycle.LifeCycle;
import com.emcat.lifecycle.LifeCycleException;
import com.emcat.lifecycle.LifeCycleSupport;
import com.emcat.lifecycle.LifecycleListener;

public class SimpleContextValue implements Value,Contained,LifeCycle{
	
	Container container;
	LifeCycleSupport lifeCycleSupport; 
	
	public SimpleContextValue(Container container){
		this.container = container;
	}

	@Override
	public void invoke(HttpRequest req, HttpResponse res) {
		// TODO Auto-generated method stub
		Wrapper wrapper = ((SimpleContext)container).getMapper().mapper(req);
		if(wrapper!=null){
			wrapper.invoke(req, res);
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

}
