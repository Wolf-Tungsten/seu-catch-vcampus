package vo.cls;

import java.sql.ResultSet;
import java.util.ArrayList;

import orm.Utils.JdbcUtils;
import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;

//用户实体
@Table(name = "tb_User")
public class User
{
	@ID(name = "_id")
	private int _id; //数据库中的id
	@Column(name = "Name")
	private String name;  //名字
	@Column(name = "CardNum")
	private String cardNum; //一卡通号
	@Column(name = "Hasn_password")
	private String hasn_password; //哈希密码
	@Column(name = "Identity")
	private int identity;   //身份
	@Column(name = "Privilege")
	private int privilege;  //权限
	@Column(name = "Photo")
	private String photo;  //头像
	
	public User()
	{
		
	}
	
	
	public User(int _id, String name, String cardNum, String hasn_password, int identity, int privilege,
			String photo)
	{
		super();
		this._id = _id;
		this.name = name;
		this.cardNum = cardNum;
		this.hasn_password = hasn_password;
		this.identity = identity;
		this.privilege = privilege;
		this.photo = photo;
	}
	//getter and setter
	public int get_id()
	{
		return _id;
	}
	public void set_id(int _id)
	{
		this._id = _id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getCardNum()
	{
		return cardNum;
	}
	public void setCardNum(String cardNum)
	{
		this.cardNum = cardNum;
	}
	public String getHasn_password()
	{
		return hasn_password;
	}
	public void setHasn_password(String hasn_password)
	{
		this.hasn_password = hasn_password;
	}
	public int getIdentity()
	{
		return identity;
	}
	public void setIdentity(int identity)
	{
		this.identity = identity;
	}
	public int getPrivilege()
	{
		return privilege;
	}
	public void setPrivilege(int privilege)
	{
		this.privilege = privilege;
	}
	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo)
	{
		this.photo = photo;
	}
	//
	public int add(User u)
	{	
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(u);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new User(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public ResultSet selectbyId(int id , User u) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, u);
		  
	}
	public  ArrayList<User> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new User());
		
	}
	public <E> ArrayList<User> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new User(), columnName, element);
		
	}
	
	
}
