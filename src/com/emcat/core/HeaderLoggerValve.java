package com.emcat.core;

import java.util.Enumeration;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.Value;
import com.emcat.lifecycle.LifeCycle;
import com.emcat.lifecycle.LifeCycleException;
import com.emcat.lifecycle.LifeCycleSupport;
import com.emcat.lifecycle.LifecycleListener;

public class HeaderLoggerValve implements Value,LifeCycle {
	
	LifeCycleSupport lifeCycleSupport;

	@Override
	public void invoke(HttpRequest req, HttpResponse res) {
		// TODO Auto-generated method stub
		System.out.println("Header Logger Valve");
	    if (req instanceof HttpRequest) {
	      Enumeration headerNames = req.getHeaderNames();
	      while (headerNames.hasMoreElements()) {
	        String headerName = headerNames.nextElement().toString();
	        String headerValue = req.getHeader(headerName);
	        System.out.println(headerName + ":" + headerValue);
	      }

	    }
	    else
	      System.out.println("Not an HTTP Request");

	    System.out.println("------------------------------------");
	}

	@Override
	public void start() throws LifeCycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() throws LifeCycleException {
		// TODO Auto-generated method stub
		
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
