package com.emcat.session;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

public interface Session {
	
	public Object getAttribute(String arg0);

	public Enumeration<String> getAttributeNames();

	public long getCreationTime();

	public String getId();

	public long getLastAccessedTime();

	public int getMaxInactiveInterval();

	public ServletContext getServletContext();

	public HttpSessionContext getSessionContext();

	public Object getValue(String arg0) ;

	public String[] getValueNames();

	public void invalidate();

	public boolean isNew();

	public void putValue(String arg0, Object arg1);

	public void removeAttribute(String arg0);

	public void removeValue(String arg0) ;
		
	public void setAttribute(String arg0, Object arg1) ;

	public void setMaxInactiveInterval(int arg0) ;
	
	//different from the  httpsession interface
	public void setId(String id);
	public Manager getManager();
	public void setManager(Manager manager);
	public void setNew(boolean isNew);
	public HttpSession getSession();
	public void setValid(boolean isValid);
	public void access();
	public void expire();
	
}
