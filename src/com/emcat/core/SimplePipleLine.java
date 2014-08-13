package com.emcat.core;

import java.util.ArrayList;
import java.util.Arrays;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.PipleLine;
import com.emcat.einterface.Value;
import com.emcat.lifecycle.LifeCycle;
import com.emcat.lifecycle.LifeCycleException;
import com.emcat.lifecycle.LifeCycleSupport;
import com.emcat.lifecycle.LifecycleListener;

public class SimplePipleLine implements PipleLine,LifeCycle{

	ArrayList<Value> values;
	Value basicValue;
	LifeCycleSupport lifeCycleSupport; 
	
	public  SimplePipleLine(){
		values = new ArrayList<Value>();
	}
	@Override
	public void invoke(HttpRequest req, HttpResponse res) {
		
		for(int i = 0;i<values.size();i++){
			values.get(i).invoke(req, res);
		}
		if(basicValue!=null){
			basicValue.invoke(req, res);
		}
		
	}
	public Value[] getValues() {
		return (Value[]) values.toArray();
	}
	public void setValues(Value[] values) {
		this.values = (ArrayList<Value>) Arrays.asList(values) ;
	}
	public Value getBasicValue() {
		return basicValue;
	}
	public void setBasicValue(Value basicValue) {
		this.basicValue = basicValue;
	}
	@Override
	public Value getBasic() {
		// TODO Auto-generated method stub
		return basicValue;
	}
	@Override
	public void setBasic(Value valve) {
		// TODO Auto-generated method stub
		this.basicValue = valve;
	}
	@Override
	public void addValve(Value valve) {
		// TODO Auto-generated method stub
		values.add(valve);
	}
	@Override
	public Value[] getValves() {
		// TODO Auto-generated method stub
		return (Value[]) values.toArray();
	}
	@Override
	public void start() throws LifeCycleException {
		// TODO Auto-generated method stub
		System.out.println("Piple start Loading");
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
		lifeCycleSupport.addLifeCycleListener(listener);
		
	}

}
