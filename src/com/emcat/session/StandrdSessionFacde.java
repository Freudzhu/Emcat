package com.emcat.session;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

public class StandrdSessionFacde implements HttpSession{
	
	StandrdSession session;
	
	public StandrdSessionFacde(StandrdSession session){
		this.session = session;
	}

	@Override
	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return session.getAttribute(arg0);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		// TODO Auto-generated method stub
		return session.getAttributeNames();
	}

	@Override
	public long getCreationTime() {
		// TODO Auto-generated method stub
		return session.getCreationTime();
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return session.getId();
	}

	@Override
	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return session.getLastAccessedTime();
	}

	@Override
	public int getMaxInactiveInterval() {
		// TODO Auto-generated method stub
		return session.getMaxInactiveInterval();
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return session.getServletContext();
	}

	@Override
	public HttpSessionContext getSessionContext() {
		// TODO Auto-generated method stub
		return session.getSessionContext();
	}

	@Override
	public Object getValue(String arg0) {
		// TODO Auto-generated method stub
		return session.getValue(arg0);
	}

	@Override
	public String[] getValueNames() {
		// TODO Auto-generated method stub
		return session.getValueNames();
	}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		session.invalidate();
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		session.putValue(arg0, arg1);
	}

	@Override
	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub
		session.putValue(arg0, arg0);
	}

	@Override
	public void removeValue(String arg0) {
		// TODO Auto-generated method stub
		session.removeValue(arg0);
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		session.setAttribute(arg0, arg1);
	}

	@Override
	public void setMaxInactiveInterval(int arg0) {
		// TODO Auto-generated method stub
		session.setMaxInactiveInterval(arg0);
	}

}
