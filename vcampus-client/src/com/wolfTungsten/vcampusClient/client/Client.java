package com.wolfTungsten.vcampusClient.client;
import java.net.Socket;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.google.gson.Gson;

import java.io.*;
public class Client {



	public static String host = "223.3.113.147";





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
			// 鑾峰彇 MessageDigest 瀵硅薄锛屽弬鏁颁负 MD5 瀛楃涓诧紝琛ㄧず杩欐槸涓�涓� MD5 绠楁硶锛堝叾浠栬繕鏈� SHA1 绠楁硶绛夛級锛�
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// update(byte[])鏂规硶锛岃緭鍏ュ師鏁版嵁
			// 绫讳技StringBuilder瀵硅薄鐨刟ppend()鏂规硶锛岃拷鍔犳ā寮忥紝灞炰簬涓�涓疮璁℃洿鏀圭殑杩囩▼
			md5.update(info.getBytes("UTF-8"));
			// digest()琚皟鐢ㄥ悗,MessageDigest瀵硅薄灏辫閲嶇疆锛屽嵆涓嶈兘杩炵画鍐嶆璋冪敤璇ユ柟娉曡绠楀師鏁版嵁鐨凪D5鍊笺�傚彲浠ユ墜鍔ㄨ皟鐢╮eset()鏂规硶閲嶇疆杈撳叆婧愩��
			// digest()杩斿洖鍊�16浣嶉暱搴︾殑鍝堝笇鍊硷紝鐢眀yte[]鎵挎帴
			byte[] md5Array = md5.digest();
			// byte[]杞寲涓哄崄鍏繘鍒剁殑32浣嶉暱搴︾殑瀛楃涓�
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
			int temp = 0xff & md5Array[i];// TODO:姝ゅ涓轰粈涔堟坊鍔� 0xff & 锛�
			String hexString = Integer.toHexString(temp);
			if (hexString.length() == 1)
			{// 濡傛灉鏄崄鍏繘鍒剁殑0f锛岄粯璁ゅ彧鏄剧ずf锛屾鏃惰琛ヤ笂0
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
		//鑾峰彇杈撳叆娴�
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String responseStr ="";
		boolean flag = true;
		 while(flag){  
             //鎺ユ敹浠庡鎴风鍙戦�佽繃鏉ョ殑鏁版嵁  
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
