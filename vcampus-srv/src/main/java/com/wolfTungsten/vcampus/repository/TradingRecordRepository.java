package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.TradingRecord;
import com.wolfTungsten.vcampus.entity.User;

public class TradingRecordRepository extends CurdRepository<TradingRecord>
{
	public TradingRecordRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, TradingRecord.class);	
	}
	
	public void addTradingRecord(String from, String to,double value,long createTime )throws SQLException {
		TradingRecord tradingRecord=new TradingRecord();
		tradingRecord.setFrom(from);
		tradingRecord.setTo(to);
		tradingRecord.setValue(value);
		tradingRecord.setCreateTime(createTime);
		dao.create(tradingRecord);
	}//增加一条记录
	
	//计算某账户支出总额
	public double calculateFrom(String from)throws SQLException{
		double fromSum=0;
		List<TradingRecord> tradingRecordList = 
				dao.query((PreparedQuery<TradingRecord>) dao.queryBuilder().where().eq(TradingRecord.FROM, from).prepare());
		for(int i=0;i<tradingRecordList.size();i++)
		{
			fromSum+=tradingRecordList.get(i).getValue();
		}
		return fromSum;
	}
	
	//计算某账户收入总额
	public double calculateTo(String to)throws SQLException{
		double toSum=0;
		List<TradingRecord> tradingRecordList = 
				dao.query((PreparedQuery<TradingRecord>) dao.queryBuilder().where().eq(TradingRecord.TO, to).prepare());
		for(int i=0;i<tradingRecordList.size();i++)
		{
			toSum+=tradingRecordList.get(i).getValue();
		}
		return toSum;
	}
	
	
	public ArrayList<TradingRecord> getBill(String userid)throws SQLException{
		ArrayList<TradingRecord> tradingRecordList = (ArrayList<TradingRecord>)
				dao.query((PreparedQuery<TradingRecord>) dao.queryBuilder().where().eq(TradingRecord.TO, userid)
						.or().eq(TradingRecord.FROM, userid).prepare());
		return tradingRecordList;
	}//总账单

	//查询账单（支出账单/收入账单）
	public ArrayList<HashMap<String,Object>> getBill(String flag, Object value) throws SQLException {
		ArrayList<TradingRecord> bill = new ArrayList<>();
		ArrayList<HashMap<String,Object>> tradingRecordList = new ArrayList<>();
		bill = (ArrayList<TradingRecord>)dao.queryForEq(flag, value);
		for(TradingRecord b:bill) {
			HashMap<String,Object>record = new HashMap<>();
			record.put(TradingRecord.UUID, b.getUuid().toString());
			record.put(TradingRecord.FROM, b.getFrom());
			record.put(TradingRecord.TO,b.getTo());
			record.put(TradingRecord.CREATETIME,b.getCreateTime());
			tradingRecordList.add(record);
				
		}
		return tradingRecordList;

	}
}
