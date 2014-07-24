package com.emcat.process;

import com.emcat.http.Request;
import com.emcat.http.Response;

public class StaticResourceProcess implements Process{
	
	private Request request;
	private Response response;
	public StaticResourceProcess(Request request ,Response response){
		this.request = request;
		this.response =  response;
	}

	@Override
	public void process(Request requst, Response response) {
		response.sendStaticResource();
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

}
