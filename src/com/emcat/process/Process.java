package com.emcat.process;

import com.emcat.http.Request;
import com.emcat.http.Response;

public interface Process {
	public void process(Request requst,Response response);
}
