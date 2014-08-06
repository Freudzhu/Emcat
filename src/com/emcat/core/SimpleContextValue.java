package com.emcat.core;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.einterface.Contained;
import com.emcat.einterface.Container;
import com.emcat.einterface.Value;
import com.emcat.einterface.Wrapper;

public class SimpleContextValue implements Value,Contained{

	Container container;
	@Override
	public void invoke(HttpRequest req, HttpResponse res) {
		// TODO Auto-generated method stub
		Wrapper wrapper = ((SimpleContext)container).getMapper().mapper(req);
		if(wrapper!=null){
			wrapper.invoke(req, res);
		}
	}

	@Override
	public Container getContainer() {
		// TODO Auto-generated method stub
		return container;
	}

	@Override
	public void setContainer(Container container) {
		// TODO Auto-generated method stub
		this.container = container;
	}

}
