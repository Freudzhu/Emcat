package com.emcat.einterface;

import javax.servlet.Servlet;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;

public interface Loader {
	public Servlet loadServlet(String servletName,HttpRequest req, HttpResponse res);
}
