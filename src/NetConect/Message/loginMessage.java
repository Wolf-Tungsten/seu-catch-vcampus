package NetConect.Message;

public class loginMessage
{
	public int id =1;
	public String cardNum = "";
	public String passWorld = "";
	public boolean islogin = false ;
	public boolean isRespone = true; // true 表示来自客户端的消息 false 来自服务端的消息
	
	public loginMessage(int id, String cardnum ,String passWorld, boolean islogin)
	{
		super();
		this.id = id;
		this.passWorld = passWorld;
		this.islogin = islogin;
		this.cardNum = cardnum;
		
	}
	public int getId()
	{
		return id;
	}
	public boolean isRespone()
	{
		return isRespone;
	}
	public void setRespone(boolean isRespone)
	{
		this.isRespone = isRespone;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getPassWorld()
	{
		return passWorld;
	}
	public void setPassWorld(String passWorld)
	{
		this.passWorld = passWorld;
	}
	public boolean getisIslogin()
	{
		return islogin;
	}
	public void setIslogin(boolean islogin)
	{
		this.islogin = islogin;
	}
	
	
	public String getCardNum()
	{
		return cardNum;
	}
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "ID: "+ id+" CardNum: "+cardNum+" islogin: "+(islogin==false? "false":"true");
	}
	public void setCardNum(String cardNum)
	{
		this.cardNum = cardNum;
	}
	
	
	
	
	
	
}
