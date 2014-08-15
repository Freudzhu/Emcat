package com.emcat.core;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.Container;
import com.emcat.einterface.Context;
import com.emcat.einterface.Loader;
import com.emcat.einterface.Mapper;
import com.emcat.einterface.PipleLine;
import com.emcat.lifecycle.LifeCycle;
import com.emcat.lifecycle.LifeCycleException;
import com.emcat.lifecycle.LifeCycleSupport;
import com.emcat.lifecycle.LifecycleListener;
import com.emcat.logger.Logger;

public class SimpleContext implements Context,LifeCycle{
	
	Loader loader;
	PipleLine pipleLine;
	Servlet servlet;
	Container parent = null;
	String servletName;
	String servletClasString;
	Servlet instance;
	ArrayList<Container>  childrens = new ArrayList<Container>();
	Map<String,String> servletMap = new Hashtable<String, String>();
	Mapper mapper = null;
	LifeCycleSupport lifeCycleSupport; 
	Boolean started = false;
	Logger logger;
	
	public SimpleContext(){
		pipleLine = new SimplePipleLine(this);
		loader = new SimpleLoader(this);
		mapper = new SimpleMapper(this);
		SimpleContextValue simpleValue  = new SimpleContextValue(this);
		lifeCycleSupport = new LifeCycleSupport();
		pipleLine.setBasic(simpleValue);
		
	}

	@Override
	public void invoke(HttpRequest req, HttpResponse res) {
		// TODO Auto-generated method stub
		if(pipleLine!=null){
			pipleLine.invoke(req, res);
		}
		
	}

	@Override
	public void addChild(Container child) throws ServletException {
		// TODO Auto-generated method stub
		childrens.add(child);
	}

	@Override
	public void removeChild(Container child) throws ServletException {
		// TODO Auto-generated method stub
		childrens.remove(child);
	}

	@Override
	public Container findChild(String name) throws ServletException {
		// TODO Auto-generated method stub
		for(Container container : childrens){
			if(container.getName()!=null && container.getName().equals(name)){
				return container;
			}
		}
		return null;
	}

	@Override
	public Container[] findChildren() throws ServletException {
		// TODO Auto-generated method stub
		Container[] containers = new Container[childrens.size()];
		for(int i=0;i<childrens.size();i++){
			containers[i] = (Container)childrens.get(i);
		}
		return containers;
	}

	@Override
	public Loader getLoader() {
		// TODO Auto-generated method stub
		return loader;
	}

	@Override
	public void setLoader(Loader loader) {
		// TODO Auto-generated method stub
		this.loader = loader;
	}

	@Override
	public void addServletMaping(String path, String servlet) {
		// TODO Auto-generated method stub
		servletMap.put(path, servlet);
	}

	@Override
	public Mapper getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public void setMapper(Mapper mapper) {
		// TODO Auto-generated method stub
		this.mapper = mapper;
	}

	public Map<String, String> getServletMap() {
		return servletMap;
	}

	public void setServletMap(Map<String, String> servletMap) {
		this.servletMap = servletMap;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return servletName;
	}

	@Override
	public void start() throws LifeCycleException {
		// TODO Auto-generated method stub
		if(started)
			throw new LifeCycleException("SimpleContext has already started");
		Container[] containers = null;
		try {
			containers = findChildren();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lifeCycleSupport.fireLifeListenner(LifeCycle.BEFORE_START_EVENT);
		if(containers!=null){
			for(Container container : containers){
				((LifeCycle)container).start();
			}
		}
		if(pipleLine!=null){
			((LifeCycle)pipleLine).start();
		}
		if(loader!=null){
			((LifeCycle)loader).start();
		}
		if(mapper!=null){
			((LifeCycle)mapper).start();
		}
		started = true;
		lifeCycleSupport.fireLifeListenner(LifeCycle.AFTER_START_EVENT);
	}

	@Override
	public void stop() throws LifeCycleException {
		// TODO Auto-generated method stub
		logger = getLogger();
		logger.log("Context is stoping");
		if(!started)
			throw new LifeCycleException("SimpleContext has already stop");
		Container[] containers = null;
		try {
			containers = findChildren();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lifeCycleSupport.fireLifeListenner(LifeCycle.BEFORE_STOP_EVENT);
		if(containers!=null){
			for(Container container : containers){
				((LifeCycle)container).stop();
			}
		}
		if(pipleLine!=null){
			((LifeCycle)pipleLine).stop();
		}
		if(loader!=null){
			((LifeCycle)loader).stop();
		}
		if(mapper!=null){
			((LifeCycle)mapper).stop();
		}
		started = false;
		lifeCycleSupport.fireLifeListenner(LifeCycle.AFTER_STOP_EVENT);
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
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	@Override
	public Container getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public void setParent(Container parent) {
		// TODO Auto-generated method stub
		this.parent = parent;
	}

	


}
