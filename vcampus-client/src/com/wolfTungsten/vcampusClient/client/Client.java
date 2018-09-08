package com.wolfTungsten.vcampusClient.client;
import java.net.Socket;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.google.gson.Gson;

import java.io.*;
public class Client {
	public static String host = "223.3.99.173";
	public static int port = 20006;
	public static class Request {
		
		private String path;
		private String token;
		private HashMap<String, Object> params;
		
		
		public Request()
		{
			params = new HashMap<String,Object>();
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		
		public HashMap<String, Object> getParams() {
			return params;
		}
		public void setParams(HashMap<String, Object> params) {
			this.params = params;
		}
		public String getToken()
		{
			return token;
		}
		public void setToken(String token)
		{
			this.token = token;
		}
		
		
		
	}

	public  static class Response {
		
		private Boolean success;
		private HashMap<String, Object> body;
		public Response() {
			
		}
		public Response(Boolean success, HashMap<String, Object> body)
		{
			super();
			this.success = success;
			this.body = body;
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
	public static String getMD5(String info) {
		return getMd5(info);
	}
	private static String getMd5(String info)
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
			int temp = 0xff & md5Array[i];// TODO:此处为什么添加 0xff & ？
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
	public static Response fetch(Request request) {
		try {
		Socket socket = new Socket(host,port);
	    OutputStream os = socket.getOutputStream();
		Gson gson = new Gson();
        String jsonstr = gson.toJson(request,Request.class);
		PrintWriter pw = new PrintWriter(os);
		
		pw.write(jsonstr);
		pw.flush();
		socket.shutdownOutput();
		//获取输入流
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String responseStr ="";
		boolean flag = true;
		 while(flag){  
             //接收从客户端发送过来的数据  
             String str = br.readLine();  
             if(str == null || "".equals(str)){  
                 flag = false;  
             }else{  
                 if("bye".equals(str)){  
                     flag = false;  
                 }else{  
                	 responseStr += str;
                 }  
             }  
         }
		Response response = gson.fromJson(responseStr, Response.class);
		System.out.println(responseStr);
		//
		br.close();
		is.close();
		pw.close();
		os.close();
		socket.close();		
		return response ;
		}catch(Exception e) {
			e.printStackTrace();
		}
		//
		return null ;
		
	}
	
	public static void main(String[] args) {
				
	}
	
}
