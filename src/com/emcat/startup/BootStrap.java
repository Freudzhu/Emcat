package com.emcat.startup;

import java.io.IOException;

import com.emcat.connector.HttpConnector;
import com.emcat.core.HeaderLoggerValve;
import com.emcat.core.StandrdWrapper;
import com.emcat.einterface.Value;

public class BootStrap {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpConnector connector = new HttpConnector();
		StandrdWrapper wrapper = new StandrdWrapper();
		wrapper.setServletName("MoreServlet");
		wrapper.setServletClasString("");
		
		Value value = new HeaderLoggerValve();
		wrapper.addValve(value);
		
		connector.setContainer(wrapper);
		connector.start();
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
