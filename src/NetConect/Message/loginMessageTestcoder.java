package NetConect.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class loginMessageTestcoder implements MessageCoder
{
	public String ID = "ID";
	public String CardNum = "cardNum";
	public String passWord = "Pwd";
	public String login ="isLogin";
	public String DELIMSTR = " ";
	public String CHARSETNAME = "US-ASCII";
	
	
	
	
	@Override
	public byte[] toWire(loginMessage msg) throws IOException
	{
		String msgString = ID+DELIMSTR+msg.getId() + DELIMSTR + CardNum+DELIMSTR+
				msg.getCardNum()+DELIMSTR+passWord + DELIMSTR +
				(msg.getPassWorld())+DELIMSTR+ login + DELIMSTR+
				(msg.getisIslogin()?"true":"false")+DELIMSTR;
		
		byte data[] = msgString.getBytes(CHARSETNAME);
		
		
		return data ;
	
	}
	
	
	
	@Override
	public loginMessage fromWirte(byte[] message) throws IOException
	{
		 ByteArrayInputStream msgStream = new ByteArrayInputStream(message);
		 Scanner s  = new Scanner(new InputStreamReader(msgStream,CHARSETNAME));
		 boolean islogin ;
		 String cardNum ;
		 String password;
		 int Id ; 
		 
		 String token;
		 try {
			 token=s.next();
			
			 if(!token.equals(ID)) {
				 
				 throw new IOException("bad id string: "+ token);
			 }else {
				 token=s.next();
				 Id = Integer.parseInt(token);
			 }
			 token = s.next();
			 if(!token.equals(CardNum))
			 {
				 throw new IOException("bad CardNum string:"+token);
				 
			 }else
			 {
				 token = s.next();
				 cardNum = token;
			 }
			 token = s.next();
			 if(!token.equals(passWord)) {
				  throw new IOException("bad passWord string:"+token);
			 }else
			 {
				 token = s.next();
				 password = token;
				
			 }
			 token = s.next();
			 if(!token.equals(login))
			 {
				 throw new IOException("bad login string "+ token);
			 }else
			 {
				 token = s.next();
				 islogin = token.equals("true")?true:false;
			 }
			 return new loginMessage(Id, cardNum, password, islogin);
			 
		 }catch(IOException e) {
			 throw new IOException("Parse error ...");
		 }
	}	
	public static void main(String[] args) {
		loginMessage msg = new loginMessage(1, "213160326", "yhd598250751", false);
		System.out.println(msg.toString());
		loginMessageTestcoder msgcode = new loginMessageTestcoder();
		try
		{
			System.out.println(msgcode.fromWirte(msgcode.toWire(msg)).toString());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
	
	
	


