package com.emcat.process;

import com.emcat.connector.HttpRequest;
import com.emcat.connector.HttpResponse;
import com.emcat.connector.Request;
import com.emcat.connector.Response;

public class StaticResourceProcess implements Process{
	
	private HttpRequest request;
	private HttpResponse response;
	public StaticResourceProcess(HttpRequest request ,HttpResponse response){
		this.request = request;
		this.response =  response;
	}

	@Override
	public void process(HttpRequest requst,HttpResponse response) {
		response.sendStaticResource();
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public HttpResponse getResponse() {
		return response;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}

}
