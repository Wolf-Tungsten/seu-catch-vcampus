package vo.relation;

import java.sql.ResultSet;
import java.util.ArrayList;

import orm.Utils.JdbcUtils;
import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;
import vo.cls.TradingRecord;

//<用户,考试>->借阅记录
@Table(name = "tb_UserXBook")
public class UserXBook
{
	@ID(name = "_id")
	private int _id;
	@Column(name = "User_id")
	private String user_id;
	@Column(name = "Book_id")
	private String book_id;
	@Column(name = "IsReturn")
	private boolean isReturn;
	public UserXBook()
	{
		
	}
	
	public UserXBook(int _id, String user_id, String book_id, boolean isReturn)
	{
		super();
		this._id = _id;
		this.user_id = user_id;
		this.book_id = book_id;
		this.isReturn = isReturn;
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
	public String getBook_id()
	{
		return book_id;
	}
	public void setBook_id(String book_id)
	{
		this.book_id = book_id;
	}
	public boolean isReturn()
	{
		return isReturn;
	}
	public void setReturn(boolean isReturn)
	{
		this.isReturn = isReturn;
	}
	public int add(UserXBook u)
	{	
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(u);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new UserXBook(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public ResultSet selectbyId(int id , UserXBook u) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, u);
		  
	}
	public ArrayList<UserXBook> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new UserXBook());
		
	}
	public <E> ArrayList<UserXBook> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new UserXBook(), columnName, element);
		
	}
	
}
