package com.wolfTungsten.vcampus.utils;

import java.util.HashMap;

public class Request {
	
	private String path;
	private String token;
	private HashMap<String, Object> params;
	
	public Request() {
		params = new HashMap<String, Object>();
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public HashMap<String, Object> getParams() {
		return params;
	}
	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}
	
	
	
}
