package com.emcat.startup;

import com.emcat.connector.HttpConnector;
import com.emcat.container.SimpleContainer;

public class BootStrap {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpConnector connector = new HttpConnector();
		SimpleContainer container = new SimpleContainer();
		connector.setContainer(container);
		connector.start();
	}
	

}
