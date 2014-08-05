package com.emcat.core;

import java.util.ArrayList;
import java.util.Arrays;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.PipleLine;
import com.emcat.einterface.Value;

public class SimplePipleLine implements PipleLine{

	ArrayList<Value> values;
	Value basicValue;
	
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

}
