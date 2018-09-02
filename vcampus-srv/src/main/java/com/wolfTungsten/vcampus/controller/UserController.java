package com.wolfTungsten.vcampus.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

public class UserController extends BaseController{

	public UserController() {
		super();
		this.addHandle("addUser", addUserHandle);
		this.addHandle("login", loginHandle);
		this.addHandle("register", registerHandle);
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
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "数据库读写出错");
				return response;
			}
			
		}
		
	};
	private BaseController.BaseHandle registerHandle = new BaseController.BaseHandle()
	{//注册
				
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			
			String username =(String)request.getParams().get(User.USERNAME);
			String cardnum = (String)request.getParams().get(User.CARDNUM);
			String hash_password = (String)request.getParams().get(User.PASSWORD);
			String photo = (String)request.getParams().get(User.PHOTO);
			int identity = (int)(double)request.getParams().get(User.IDENTITY);
			int privilege = (int)(double)request.getParams().get(User.PRIVILEGE);
			long birthdate = (long)(double)request.getParams().get(User.BIRTHDATE);
			String address = (String)request.getParams().get(User.ADDRESS);
			String idcardNum = (String)request.getParams().get(User.IDCARDNUM);
			try
			{
				orm.userRepository.addUser(username, cardnum, hash_password, identity, privilege
						, photo,idcardNum,birthdate,address);
				response.setSuccess(true);		
				//System.out.println(String.format("用户名：%s - 一卡通号：%s", username, cardnum));
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "数据库读写出错,"+e.getMessage());
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
			String password = (String) request.getParams().get("hash_password");
			try {
			
			UUID Useruuid = orm.userRepository.login(cardnum, password);
			//不存在该用户，-- 这样抛感觉不好
			if(Useruuid==null) {
				
				throw new Exception("不存在此用户");
			}
			//登陆成功
			long timestamp =  System.currentTimeMillis()/1000;
			System.out.println(timestamp);
			//生成token 存表
			String token = getMD5(Useruuid.toString()+Long.toString(timestamp));
			orm.tokenRepository.addToken(token, Useruuid.toString(), timestamp);//***空指针异常
			response.setSuccess(true);
			response.getBody().put("token", token);
			return response;
					
			}catch(SQLException e) {
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "登陆失败!");
				return response;
			}
			catch(Exception e) {
				System.out.println("原因:"+e.getMessage());
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				return response;
			}
			
		}
	};
	
	private static String getMD5(String info)
	{
		try
		{
			// 获取 MessageDigest 对象，参数为 MD5 字符串，表示这是一个 MD5 算法（其他还有 SHA1 算法等）：
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// update(byte[])方法，输入原数据
			// 类似StringBuilder对象的append()方法，追加模式，属于一个累计更改的过程
			md5.update(info.getBytes("UTF-8"));
			// digest()被调用后,MessageDigest对象就被重置，即不能连续再次调用该方法计算原数据的MD5值。可以手动调用reset()方法重置输入源。
			// digest()返回值16位长度的哈希值，由byte[]承接
			byte[] md5Array = md5.digest();
			// byte[]转化为十六进制的32位长度的字符串
			return bytesToHex1(md5Array);
		} catch (NoSuchAlgorithmException e)
		{
			return "";
		} catch (UnsupportedEncodingException e)
		{
			return "";
		}
	}
	
	private static String bytesToHex1(byte[] md5Array)
	{
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < md5Array.length; i++)
		{
			int temp = 0xff & md5Array[i];
			String hexString = Integer.toHexString(temp);
			if (hexString.length() == 1)
			{// 如果是十六进制的0f，默认只显示f，此时要补上0
				strBuilder.append("0").append(hexString);
			} else
			{
				strBuilder.append(hexString);
			}
		}
		return strBuilder.toString();
	}
	public static void main(String[] args) {
		String password = getMD5("yhd598250751");
		System.out.println(password);
		
	}
	
}