package com.emcat.einterface;

import javax.servlet.Servlet;

public interface Wrapper extends Container{

	public Container getParent();
	public void setParent(Container parent);
	public Servlet allocte();
	
}
