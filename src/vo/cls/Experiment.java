package vo.cls;

import java.sql.ResultSet;
import java.util.ArrayList;

import orm.Utils.JdbcUtils;
import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;

//实验
@Table(name = "tb_Experiment")
public class Experiment
{
	@ID(name = "_id")
	private int _id;
	@Column(name = "Name")
	private String name;
	@Column(name = "StartTime")
	private int startTime;
	@Column(name = "Duration")
	private int duration;
	@Column(name = "Location")
	private String location;
	@Column(name = "CreateTime")
	private int createTime;
	@Column(name = "UpdatTime")
	private int updateTime;
	
	public Experiment()
	{
		
	}
	
	
	public Experiment(int _id, String name, int startTime, int duration, String location, int createTime,
			int updateTime)
	{
		super();
		this._id = _id;
		this.name = name;
		this.startTime = startTime;
		this.duration = duration;
		this.location = location;
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
	public int getStartTime()
	{
		return startTime;
	}
	public void setStartTime(int startTime)
	{
		this.startTime = startTime;
	}
	public int getDuration()
	{
		return duration;
	}
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	public String getLocation()
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location = location;
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
	public  int add(Experiment e)
	{	
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(e);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new Experiment(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public  ResultSet selectbyId(int id , Experiment e) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, e);
		  
	}
	public ArrayList<Experiment> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new Experiment());
		
	}
	public <E> ArrayList<Experiment> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new Experiment(), columnName, element);
		
	}
	
}
