package com.wolfTungsten.vcampus.controller;

import java.sql.SQLException;

import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

public class UserController extends BaseController{

	public UserController() {
		super();
		this.addHandle("addUser", addUserHandle);
	}
	
	private BaseController.BaseHandle addUserHandle = new BaseController.BaseHandle() {
		
		@Override
		public Response work(Request request) {
			Response response = new Response();
			String username = (String) request.getParams().get("username");
			String cardnum = (String) request.getParams().get("cardnum");
			try {
				orm.userRepository.AddUser(username, cardnum);
				response.setSuccess(true);
				System.out.println(String.format("用户名：%s - 一卡通号：%s", username, cardnum));
				return response;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "数据库读写出错");
				return response;
			}
			
		}
		
	};

	
}