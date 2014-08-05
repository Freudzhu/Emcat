package com.emcat.core;

import java.util.Enumeration;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.Value;

public class HeaderLoggerValve implements Value {

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

}
