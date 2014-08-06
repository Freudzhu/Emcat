package com.emcat.startup;

import java.io.IOException;

import javax.servlet.ServletException;

import com.emcat.connector.HttpConnector;
import com.emcat.core.HeaderLoggerValve;
import com.emcat.core.SimpleContext;
import com.emcat.core.StandrdWrapper;
import com.emcat.einterface.Value;

public class BootStrap {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpConnector connector = new HttpConnector();
		
		StandrdWrapper wrapper = new StandrdWrapper();
		wrapper.setServletName("MoreServlet");
		wrapper.setServletClasString("MoreServlet");
		
		Value value = new HeaderLoggerValve();
		wrapper.addValve(value);
		
		SimpleContext context = new SimpleContext();
		try {
			context.addChild(wrapper);
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		context.addServletMaping("/moreservlet", "MoreServlet");
		
		connector.setContainer(context);
		connector.start();
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
