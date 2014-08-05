package com.emcat.einterface;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;

public interface Container {
	public void invoke(HttpRequest req,HttpResponse res);
	public void addChild(Container child);
	public void removeChild(Container child);
	public Container findChild(String name);
	public Container[] findChildren();
	public Loader getLoader();
	public void setLoader(Loader loader);
}
