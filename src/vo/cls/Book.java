package vo.cls;
import java.sql.ResultSet;
import java.util.ArrayList;

import orm.Utils.JdbcUtils;
import orm.annotation.*;

//图书实体
@Table(name = "tb_Book")
public class Book
{
	@ID(name = "_id")
	private int _id; //数据库中的id
	@Column(name = "Name")
	private String name;  //名字
	@Column(name = "Amount")
	private String amount; //数量
	@Column(name = "Isbn")
	private String isbn; //isbn码
	@Column(name = "CreateTime")
	private int createTime; // 创建时间戳
	@Column(name = "UpdateTime")
	private int updateTime; //更新时间戳
	
	//getter and setter
	
	
	public Book(int _id, String name, String amount, String isbn, int createTime, int updateTime)
	{
		super();
		this._id = _id;
		this.name = name;
		this.amount = amount;
		this.isbn = isbn;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public Book()
	{
		
	}
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
	public String getAmount()
	{
		return amount;
	}
	public void setAmount(String amount)
	{
		this.amount = amount;
	}
	public String getIsbn()
	{
		return isbn;
	}
	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}
	public int getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(int createTime)
	{
		this.createTime = createTime;
	}
	public int getUpdateTime()
	{
		return updateTime;
	}
	public void setUpdateTime(int updateTime)

	{
		this.updateTime = updateTime;
	}
	//--
	
	public  int add(Book book)
	{
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(book);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new Book(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public  ResultSet selectbyId(int id , Book b) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, b);
		  
	}
	public  ArrayList<Book> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new Book());
		
	}
	public <E> ArrayList<Book> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new Book(), columnName, element);
		
	}
	public boolean update(Book b) {
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		int result = jdbcUtils.update(b);
		return result<0 ?false:true;
		
	}
	
	
	
}
