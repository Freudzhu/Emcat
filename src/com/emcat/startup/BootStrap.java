package com.emcat.startup;

import com.emcat.http.HttpConnector;

public class BootStrap {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpConnector connector = new HttpConnector();
		connector.start();
	}
	

}
