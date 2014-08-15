package com.emcat.einterface;


import javax.servlet.ServletException;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.logger.Logger;

public interface Container {
	public void invoke(HttpRequest req,HttpResponse res);
	public void addChild(Container child) throws ServletException;
	public void removeChild(Container child) throws ServletException;
	public Container findChild(String name) throws ServletException;
	public Container[] findChildren() throws ServletException;
	public Loader getLoader();
	public void setLoader(Loader loader);
	public String getName();
	public Logger getLogger();
	public void setLogger(Logger logger) ;
	public Container getParent();
	public void setParent(Container parent);
}
