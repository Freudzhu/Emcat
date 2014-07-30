package com.emcat.startup;

import com.emcat.connector.HttpConnector;

public class BootStrap {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpConnector connector = new HttpConnector();
		connector.start();
	}
	

}
