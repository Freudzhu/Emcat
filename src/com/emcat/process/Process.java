package com.emcat.process;

import com.emcat.http.HttpRequest;
import com.emcat.http.HttpResponse;
import com.emcat.http.Request;
import com.emcat.http.Response;

public interface Process {
	 public void process(HttpRequest request, HttpResponse response);
}
