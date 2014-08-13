package com.emcat.core;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.Container;
import com.emcat.einterface.Loader;
import com.emcat.einterface.PipleLine;
import com.emcat.einterface.Value;
import com.emcat.einterface.Wrapper;
import com.emcat.lifecycle.LifeCycle;
import com.emcat.lifecycle.LifeCycleException;
import com.emcat.lifecycle.LifeCycleSupport;
import com.emcat.lifecycle.LifecycleListener;

public class StandrdWrapper implements Wrapper,PipleLine,LifeCycle{
	
	Loader loader;
	PipleLine pipleLine;
	Servlet servlet;
	Container parent = null;
	String servletName;
	String servletClasString;
	Servlet instance;
	LifeCycleSupport lifeCycleSupport;
	
	public StandrdWrapper(){
		pipleLine = new SimplePipleLine();
		loader = new SimpleLoader();
		SimpleWraperValue simpleValue  = new SimpleWraperValue();
		simpleValue.setContainer(this);
		pipleLine.setBasic(simpleValue);
	}
	
	@Override
	public void invoke(HttpRequest req, HttpResponse res) {
		if(pipleLine!=null){
			pipleLine.invoke(req, res);
		}
	}

	@Override
	public void setLoader(Loader loader) {
		this.loader = loader; 
	}

	@Override
	public Loader getLoader() {
		 if (loader != null)      
			 return (loader);    
		 if (parent != null)       
			 return (parent.getLoader());    
		 return (null);
	}

	@Override
	public void addChild(Container child) throws ServletException {
		// TODO Auto-generated method stub
		throw new ServletException("wrap has not chiledren");
	}

	@Override
	public void removeChild(Container child) throws ServletException {
		// TODO Auto-generated method stub
		throw new ServletException("wrap does not support");
	}

	@Override
	public Container findChild(String name) throws ServletException {
		// TODO Auto-generated method stub
		throw new ServletException("wrap does not support");
	}

	@Override
	public Container[] findChildren() throws ServletException {
		// TODO Auto-generated method stub
		throw new ServletException("wrap does not support");
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

	@Override
	public Value getBasic() {
		// TODO Auto-generated method stub
		return pipleLine.getBasic();
	}

	@Override
	public void setBasic(Value valve) {
		// TODO Auto-generated method stub
		pipleLine.setBasic(valve);
	}

	@Override
	public void addValve(Value valve) {
		// TODO Auto-generated method stub
		pipleLine.addValve(valve);
	}

	@Override
	public Value[] getValves() {
		// TODO Auto-generated method stub
		return pipleLine.getValves();
	}

	public String getServletName() {
		return servletName;
	}

	public void setServletName(String servletName) {
		this.servletName = servletName;
	}

	public String getServletClasString() {
		return servletClasString;
	}

	public void setServletClasString(String servletClasString) {
		this.servletClasString = servletClasString;
	}
	private Servlet loadServlet() throws ServletException {
		
		if (instance!=null)
		      return instance;
		if(servletClasString==null){
			 throw new ServletException("servlet class has not been specified");
		}
		Loader loader = getLoader();
		if(loader == null){
			throw new ServletException("No loader.");
		}
		ClassLoader classLoader = loader.getClassLoader();
		Class  class1 = null;
		if(classLoader!=null){
			try {
				class1 = classLoader.loadClass(servletClasString);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				servlet = (Servlet) class1.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			servlet.init(null);
		}
		return servlet;
		
	}

	@Override
	public Servlet allocte() {
		// TODO Auto-generated method stub
		if(instance==null){
			try {
				instance = loadServlet();
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return instance;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return servletName;
	}

	@Override
	public void start() throws LifeCycleException {
		// TODO Auto-generated method stub
		System.out.println(getName()+" Wrapper start Loading");
	}

	@Override
	public void stop() throws LifeCycleException {
		// TODO Auto-generated method stub
		System.out.println("Wrapper stop Loading");
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
