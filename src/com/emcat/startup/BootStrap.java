package com.emcat.startup;

import java.io.IOException;

import javax.servlet.ServletException;

import com.emcat.connector.HttpConnector;
import com.emcat.core.HeaderLoggerValve;
import com.emcat.core.SimpleContext;
import com.emcat.core.StandrdWrapper;
import com.emcat.einterface.Value;
import com.emcat.lifecycle.LifeCycleException;
import com.emcat.lifecycle.LifecycleListener;
import com.emcat.lifecycle.SimpleListener;
import com.emcat.logger.Logger;
import com.emcat.logger.SystemoutLogger;

public class BootStrap {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpConnector connector = new HttpConnector();
		
		StandrdWrapper wrapper = new StandrdWrapper();
		wrapper.setServletName("MoreServlet");
		wrapper.setServletClasString("MoreServlet");
		
		
		Value value = new HeaderLoggerValve();
		wrapper.addValve(value);
		
		
		StandrdWrapper wrapper1 = new StandrdWrapper();
		wrapper1.setServletName("PrimitServlet");
		wrapper1.setServletClasString("PrimitServlet");
		
		
		SimpleContext context = new SimpleContext();
		
		LifecycleListener listener = new SimpleListener();
		
		Logger logger = new SystemoutLogger();
		context.setLogger(logger);
		
		try {
			context.addLifeCycleListener(listener);
		} catch (LifeCycleException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			context.addChild(wrapper);
			context.addChild(wrapper1);
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		context.addServletMaping("/moreservlet", "MoreServlet");
		context.addServletMaping("/primitServlet", "PrimitServlet");
		wrapper.setParent(context);
		wrapper1.setParent(context);
		
		connector.setContainer(context);
		try {
			connector.start();
		} catch (LifeCycleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
