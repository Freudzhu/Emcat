package com.emcat.lifecycle;

import java.util.EventObject;


public class ListenerEvent extends EventObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String type;

	public ListenerEvent(Object source,String type) {
		super(source);
		this.type = type;
	}
	
	public String getType(){
		return type;
	}



}
