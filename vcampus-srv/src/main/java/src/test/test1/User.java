package src.test.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;

public class User
{
	@Expose
	private String firstName;

	@Expose(serialize = true)
	private String lastName;

	@Expose(serialize = true)
	private Map<String,String> map ;

	@Expose(deserialize = false)
	private String emailAddress;
	@Expose(deserialize = true)
	private String password;
	public User(String firstName, String lastName, String emailAddress, String password)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.map = new HashMap();
		
	}
	public User()
	{
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString()
	{
		
		return ("firstName:" + firstName + " lastName:"+ 
		lastName + " emailAddress:"+ emailAddress + " password:" + password+"\n"+map.toString());
	}
	public void setHash(String key, String value) {
		map.put(key, value);
	}
	
	
}
