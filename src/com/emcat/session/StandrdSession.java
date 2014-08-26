package com.emcat.session;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.catalina.util.Enumerator;

public class StandrdSession implements Session{
	
	private String id;
	private HashMap<String, Object> attributes = new HashMap<String, Object>();
	private int DEFAULT_INTER_TIME = 60;
	private long creatTime;
	private long lastAccessedTime;
	private long thisAccessedTime;
	private int maxInactiveInterval = DEFAULT_INTER_TIME;
	private Manager manager;
	private boolean isNew = false;
	private boolean isValid = false;
	
	public StandrdSession(Manager manager){
		this.manager = manager;
		creatTime = System.currentTimeMillis();
	}
	
	@Override
	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		Object object  = null;
		synchronized (attributes) {
			object = attributes.get(arg0);
		}
		return object;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		// TODO Auto-generated method stub
		synchronized (attributes) {
			return new Enumerator(attributes.keySet());
		}
	}

	@Override
	public long getCreationTime() {
		// TODO Auto-generated method stub
		return creatTime;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return lastAccessedTime;
	}

	@Override
	public int getMaxInactiveInterval() {
		// TODO Auto-generated method stub
		return maxInactiveInterval;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSessionContext getSessionContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getValueNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return isNew;
	}

	@Override
	public void putValue(String arg0, Object arg1){
		// TODO Auto-generated method stub
	}

	@Override
	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub
	
		synchronized (attributes) {
			attributes.remove(arg0);
		}
	}

	@Override
	public void removeValue(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		synchronized (attributes) {
			attributes.put(arg0, arg1);
		}
	}

	@Override
	public void setMaxInactiveInterval(int arg0) {
		// TODO Auto-generated method stub
		this.maxInactiveInterval = arg0;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public Manager getManager() {
		// TODO Auto-generated method stub
		return manager;
	}

	@Override
	public void setManager(Manager manager) {
		// TODO Auto-generated method stub
		this.manager = manager;
	}

	@Override
	public void setNew(boolean isNew) {
		// TODO Auto-generated method stub
		this.isNew = isNew;
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return new StandrdSessionFacde(this);
	}

	@Override
	public void setValid(boolean isValid) {
		// TODO Auto-generated method stub
		this.isValid = isValid;
	}

	@Override
	public void access() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void expire() {
		// TODO Auto-generated method stub
		
	}

}
