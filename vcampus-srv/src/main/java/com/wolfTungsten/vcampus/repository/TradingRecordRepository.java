package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
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
	
	public List<TradingRecord> getFromBill(String from)throws SQLException{
		List<TradingRecord> tradingRecordList = 
				dao.query((PreparedQuery<TradingRecord>) dao.queryBuilder().where().eq(TradingRecord.FROM, from).prepare());
		return tradingRecordList;
	}//支出账单
	
	public List<TradingRecord> getToBill(String to)throws SQLException{
		List<TradingRecord> tradingRecordList = 
				dao.query((PreparedQuery<TradingRecord>) dao.queryBuilder().where().eq(TradingRecord.TO, to).prepare());
		return tradingRecordList;
	}//收入账单
	
	public List<TradingRecord> getBill(String userid)throws SQLException{
		List<TradingRecord> tradingRecordList = 
				dao.query((PreparedQuery<TradingRecord>) dao.queryBuilder().where().eq(TradingRecord.TO, userid)
						.or().eq(TradingRecord.FROM, userid).prepare());
		return tradingRecordList;
	}//总账单

}
