package com.emcat.process;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.connector.Request;
import com.emcat.connector.Response;

public interface Process {
	 public void process(HttpRequest request, HttpResponse response);
}
