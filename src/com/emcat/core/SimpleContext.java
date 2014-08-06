package com.emcat.core;

import java.util.Arrays;
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

public class SimpleContext implements Context{
	
	Loader loader;
	PipleLine pipleLine;
	Servlet servlet;
	Container parent = null;
	String servletName;
	String servletClasString;
	Servlet instance;
	Container[]  childrens = new Container[0];
	Map<String,String> servletMap = new Hashtable<String, String>();
	Mapper mapper = null;
	
	public SimpleContext(){
		pipleLine = new SimplePipleLine();
		loader = new SimpleLoader();
		mapper = new SimpleMapper();
		SimpleContextValue simpleValue  = new SimpleContextValue();
		simpleValue.setContainer(this);
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
		Container[] result = new Container[childrens.length+1];
		result = Arrays.copyOf(childrens, result.length);
		result[result.length-1] = child;
		childrens = result;
	}

	@Override
	public void removeChild(Container child) throws ServletException {
		// TODO Auto-generated method stub
		Container[] result = new Container[childrens.length-1];
		result = Arrays.copyOf(childrens, result.length);
		result[result.length-1] = child;
		childrens = result;
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
		return childrens;
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

	


}
