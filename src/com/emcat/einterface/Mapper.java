package com.emcat.einterface;

import com.emcat.connector.HttpRequest;

public interface Mapper {
	public Wrapper mapper(HttpRequest req);
}
