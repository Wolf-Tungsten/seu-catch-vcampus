package vo.relation;

import java.sql.ResultSet;
import java.util.ArrayList;

import vo.cls.TradingRecord;
import vo.cls.User;
import vo.relation.*;
import orm.Utils.JdbcUtils;
import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;

//<余额账户,消费记录>  ->消费流水
@Table(name = "tb_ABalanceXTRecord")
public class ABalanceXTRecord
{
	@ID(name = "_id")
	private int _id;
	@Column(name = "AccountBalance_id")
	private String AccountBalance_id;
	@Column(name = "Record_id")
	private String Record_id;
	
	public ABalanceXTRecord() {
		
	}

	public ABalanceXTRecord(int _id, String accountBalance_id, String record_id)
	{
		super();
		this._id = _id;
		AccountBalance_id = accountBalance_id;
		Record_id = record_id;
	}
	public int get_id()
	{
		return _id;
	}
	public void set_id(int _id)
	{
		this._id = _id;
	}
	public String getAccountBalance_id()
	{
		return AccountBalance_id;
	}
	public void setAccountBalance_id(String accountBalance_id)
	{
		AccountBalance_id = accountBalance_id;
	}
	public String getRecord_id()
	{
		return Record_id;
	}
	public void setRecord_id(String record_id)
	{
		Record_id = record_id;
	}
	public void add()
	{
		
	}
	public void delete()
	{
		
	}
	public void update()
	{
		
	}
	public boolean check(){
		return false;
	}
	public  int add(ABalanceXTRecord u)
	{	
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(u);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new ABalanceXTRecord(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public  ResultSet selectbyId(int id , ABalanceXTRecord u) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, u);
		  
	}
	public  ArrayList<ABalanceXTRecord> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new ABalanceXTRecord());
		
	}
	public <E> ArrayList<ABalanceXTRecord> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new ABalanceXTRecord(), columnName, element);
		
	}
	
	
}
