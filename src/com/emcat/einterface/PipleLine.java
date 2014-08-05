package com.emcat.einterface;
import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;

public interface PipleLine {
	public void invoke(HttpRequest req,HttpResponse res);
	public Value getBasic();   
	public void setBasic(Value valve);  
	public void addValve(Value valve);  
	public Value[] getValves();
}
