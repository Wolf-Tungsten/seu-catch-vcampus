package vo.relation;

import java.sql.ResultSet;
import java.util.ArrayList;

import orm.Utils.JdbcUtils;
import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;

//<用户,商品>-> 购买记录
@Table(name = "tb_UserXMerchandise")
public class UserXMerchandise
{
	@ID(name = "_id")
	private int _id;
	@Column(name = "User_id")
	private String user_id;
	@Column(name = "Merchandise_id")
	private String merchandise_id;
	public UserXMerchandise()
	{
		
	}
	
	public UserXMerchandise(int _id, String user_id, String merchandise_id)
	{
		super();
		this._id = _id;
		this.user_id = user_id;
		this.merchandise_id = merchandise_id;
	}
	public int get_id()
	{
		return _id;
	}
	public void set_id(int _id)
	{
		this._id = _id;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	public String getMerchandise_id()
	{
		return merchandise_id;
	}
	public void setMerchandise_id(String merchandise_id)
	{
		this.merchandise_id = merchandise_id;
	}
	
	public int add(UserXMerchandise u)
	{	
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(u);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new UserXMerchandise(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public ResultSet selectbyId(int id , UserXMerchandise u) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, u);
		  
	}
	public ArrayList<UserXMerchandise> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new UserXMerchandise());		
	}
	public <E> ArrayList<UserXMerchandise> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new UserXMerchandise(), columnName, element);
		
	}
	
}
