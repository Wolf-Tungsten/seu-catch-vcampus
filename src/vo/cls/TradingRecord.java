package vo.cls;

import java.sql.ResultSet;
import java.util.ArrayList;

import orm.Utils.JdbcUtils;
import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;

//交易记录实体 // 消费&充值记录
@Table(name = "tb_TradingRecord")
public class TradingRecord
{
	@ID(name = "_id")
	private int _id;
	@Column(name = "From")
	private String from;
	@Column(name = "To")
	private String to;
	@Column(name = "CreateTime")
	private int createTime;
	

	public TradingRecord()
	{
		
	}
	
	public TradingRecord(int _id, String from, String to, int createTime)
	{
		super();
		this._id = _id;
		this.from = from;
		this.to = to;
		this.createTime = createTime;
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
	public String getFrom()
	{
		return from;
	}
	public void setFrom(String from)
	{
		this.from = from;
	}
	public String getTo()
	{
		return to;
	}
	public void setTo(String to)
	{
		this.to = to;
	}
	public int getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(int createTime)
	{
		this.createTime = createTime;
	}
	public int add(TradingRecord u)
	{	
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(u);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new TradingRecord(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public ResultSet selectbyId(int id , TradingRecord u) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, u);
		  
	}
	public  ArrayList<TradingRecord> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new TradingRecord());
		
	}
	public <E> ArrayList<TradingRecord> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new TradingRecord(), columnName, element);
		
	}
}
