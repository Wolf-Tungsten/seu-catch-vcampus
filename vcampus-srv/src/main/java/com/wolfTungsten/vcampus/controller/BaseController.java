package com.wolfTungsten.vcampus.controller;

import java.util.HashMap;

import com.wolfTungsten.vcampus.ORM;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

public abstract class BaseController {
	
	protected ORM orm;
	protected HashMap<String, BaseHandle> pathMap;
	
	public BaseController() {
		this.orm = ORM.getInstance();
		this.pathMap = new HashMap<String, BaseHandle>();	
	}
	
	public void addHandle(String path, BaseHandle handle) {
		this.pathMap.put(path, handle);
	}
	
	public Response handle(Request request) {
		for (String path:pathMap.keySet()) {
			if (request.getPath().split("/")[1].startsWith(path)) {
				// 路由命中
				return pathMap.get(path).work(request);
			}
		}
		// 路由未命中
		Response failResponse = new Response();
		failResponse.setSuccess(false);
		failResponse.getBody().put("msg", "未知的路由");
		return failResponse;
	};
	
	public abstract class BaseHandle {
		public abstract Response work(Request request);
	}
	
}
