package com.emcat.core;

import javax.servlet.Servlet;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.Container;
import com.emcat.einterface.Loader;
import com.emcat.einterface.PipleLine;
import com.emcat.einterface.Value;
import com.emcat.einterface.Wrapper;

public class StandrdWrapper implements Wrapper,PipleLine{
	
	Loader loader;
	PipleLine pipleLine;
	Servlet servlet;
	Container parent = null;
	String servletName;
	String servletClasString;
	
	public StandrdWrapper(){
		pipleLine = new SimplePipleLine();
		loader = new SimpleLoader();
		SimpleValue simpleValue  = new SimpleValue();
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
	public void addChild(Container child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeChild(Container child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Container findChild(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Container[] findChildren() {
		// TODO Auto-generated method stub
		return null;
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

}
