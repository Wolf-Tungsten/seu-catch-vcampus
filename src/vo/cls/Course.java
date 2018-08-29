package vo.cls;

import java.sql.ResultSet;
import java.util.ArrayList;

import orm.Utils.JdbcUtils;
import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;

//课程实体
@Table(name = "tb_Course")
public class Course
{
	@ID(name = "_id")
	private int _id; //数据库中的id
	@Column(name = "Name")
	private String name;  //名字
	@Column(name = "Capacity")
	private int capacity; //
	@Column(name = "CreateTime")
	private int createTime; //创建时间戳
	@Column(name = "UpdateTime")
	private int updateTime; //更新时间戳
	
	
	public Course()
	{
		
	}
	
	public Course(int _id, String name, int capacity, int createTime, int updateTime)
	{
		super();
		this._id = _id;
		this.name = name;
		this.capacity = capacity;
		this.createTime = createTime;
		this.updateTime = updateTime;
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
	public int getCapacity()
	{
		return capacity;
	}
	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
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
	
	
	public  int add(Course course)
	{	
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(course);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new Course(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public  ResultSet selectbyId(int id , Course c) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, c);
		  
	}
	public  ArrayList<Course> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new Course());
		
	}
	public <E> ArrayList<Course> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new Course(), columnName, element);
		
	}
	
	

}
