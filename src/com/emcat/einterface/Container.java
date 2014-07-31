package com.emcat.einterface;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;

public interface Container {
	public void invoke(HttpRequest req,HttpResponse res);
}
