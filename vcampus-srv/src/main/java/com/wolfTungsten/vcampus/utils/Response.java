package com.wolfTungsten.vcampus.utils;

import java.util.HashMap;

public class Response {
	
	private Boolean success;
	private HashMap<String, Object> body;
	
	public Response() {
		body = new HashMap<String, Object>();
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public HashMap<String, Object> getBody() {
		return body;
	}
	public void setBody(HashMap<String, Object> body) {
		this.body = body;
	}
	
	
}
