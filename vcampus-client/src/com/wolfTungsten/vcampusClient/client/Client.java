package com.wolfTungsten.vcampusClient.client;
import java.net.Socket;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.google.gson.Gson;

import java.io.*;
public class Client {

	public static String host = "223.3.106.227";

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
			// 閼惧嘲褰� MessageDigest 鐎电钖勯敍灞藉棘閺侀璐� MD5 鐎涙顑佹稉璇х礉鐞涖劎銇氭潻娆愭Ц娑擄拷娑擄拷 MD5 缁犳纭堕敍鍫濆従娴犳牞绻曢張锟� SHA1 缁犳纭剁粵澶涚礆閿涳拷
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// update(byte[])閺傝纭堕敍宀冪翻閸忋儱甯弫鐗堝祦
			// 缁鎶�StringBuilder鐎电钖勯惃鍒焢pend()閺傝纭堕敍宀冩嫹閸旂姵膩瀵骏绱濈仦鐐扮艾娑擄拷娑擃亞鐤拋鈩冩纯閺�鍦畱鏉╁洨鈻�
			md5.update(info.getBytes("UTF-8"));
			// digest()鐞氼偉鐨熼悽銊ユ倵,MessageDigest鐎电钖勭亸杈潶闁插秶鐤嗛敍灞藉祮娑撳秷鍏樻潻鐐电敾閸愬秵顐肩拫鍐暏鐠囥儲鏌熷▔鏇☆吀缁犳甯弫鐗堝祦閻ㄥ嚜D5閸婄锟藉倸褰叉禒銉﹀閸斻劏鐨熼悽鈺甧set()閺傝纭堕柌宥囩枂鏉堟挸鍙嗗┃鎰╋拷锟�
			// digest()鏉╂柨娲栭崐锟�16娴ｅ秹鏆辨惔锔炬畱閸濆牆绗囬崐纭风礉閻㈢渶yte[]閹垫寧甯�
			byte[] md5Array = md5.digest();
			// byte[]鏉烆剙瀵叉稉鍝勫磩閸忣叀绻橀崚鍓佹畱32娴ｅ秹鏆辨惔锔炬畱鐎涙顑佹稉锟�
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
			int temp = 0xff & md5Array[i];// TODO:濮濄倕顦╂稉杞扮矆娑斿牊鍧婇崝锟� 0xff & 閿涳拷
			String hexString = Integer.toHexString(temp);
			if (hexString.length() == 1)
			{// 婵″倹鐏夐弰顖氬磩閸忣叀绻橀崚鍓佹畱0f閿涘矂绮拋銈呭涧閺勫墽銇歠閿涘本顒濋弮鎯邦洣鐞涖儰绗�0
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
		//閼惧嘲褰囨潏鎾冲弳濞达拷
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String responseStr ="";
		boolean flag = true;
		 while(flag){  
             //閹恒儲鏁规禒搴☆吂閹撮顏崣鎴︼拷浣界箖閺夈儳娈戦弫鐗堝祦  
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
