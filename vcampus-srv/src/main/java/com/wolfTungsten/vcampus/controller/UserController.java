package com.wolfTungsten.vcampus.controller;

import java.sql.SQLException;
import java.util.UUID;

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
	
	private BaseController.BaseHandle loginHandle = new BaseController.BaseHandle() 
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String cardnum = (String) request.getParams().get("cardnum");
			String password = (String) request.getParams().get("password");
			try {
			
			UUID Useruuid = orm.userRepository.login(cardnum, password);
			//不存在该用户，-- 这样抛感觉不好
			if(Useruuid == null) 
				throw new Exception("不存在该用户信息");
			//获取request的token
			String token = request.getToken();
			//获取此用户上一次操作的时间戳
			long timestamp = orm.tokenRepository
					.checkToken(token, Useruuid.toString());
			if(timestamp==0)
				throw new Exception("非法请求!");
			//当前时间戳
			long nowtimeStamp = System.currentTimeMillis();
			//两次登陆间隔3天
			long duration =3*24*3600*1000;
			if(timestamp + duration <= nowtimeStamp)
				throw new Exception("已过时，请重新登陆");
			
			
			//登陆成功
			response.setSuccess(true);
			
			return response;
			
			
			}catch(SQLException e) {
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "登陆失败!");
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
			// TODO Auto-generated method stub
			return null;
		}
	};
	
}