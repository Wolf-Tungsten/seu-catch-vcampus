package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
	

	public void deposit(String from,String to,double value,long createTime)throws SQLException {
		TradingRecord tradingRecord=new TradingRecord();
	    tradingRecord.setFrom(from);
	    tradingRecord.setTo(to);
		tradingRecord.setValue(value);
		tradingRecord.setCreateTime(createTime);
		dao.create(tradingRecord);
	}
	
	public void addTradingRecord(String from, String to,double value,long createTime )throws SQLException {
		
			if(calculateBalance(from)>=value)
			{
				TradingRecord tradingRecord=new TradingRecord();
			    tradingRecord.setFrom(from);
			    tradingRecord.setTo(to);
				tradingRecord.setValue(value);
				tradingRecord.setCreateTime(createTime);
				dao.create(tradingRecord);
			}else
			{
				throw new SQLException("该账户余额不足");
			}
	}//增加一条记录
	
	//计算某账户余额
	public double calculateBalance(String userid)throws SQLException{
		double fromSum=0;
		double toSum=0;
		double balance=0;
		List<TradingRecord> fromList = 
				dao.query((PreparedQuery<TradingRecord>) dao.queryBuilder().where().eq(TradingRecord.FROM, userid).prepare());
		for(int i=0;i<fromList.size();i++)
		{
			fromSum+=fromList.get(i).getValue();
		}
		List<TradingRecord> toList = 
				dao.query((PreparedQuery<TradingRecord>) dao.queryBuilder().where().eq(TradingRecord.TO, userid).prepare());
		for(int i=0;i<toList.size();i++)
		{
			toSum+=toList.get(i).getValue();
		}
		balance=toSum-fromSum;
		return balance/100;
	}
	
	
	
	public ArrayList<TradingRecord> getBill(String userid,long time)throws SQLException{
		ArrayList<TradingRecord> bill = (ArrayList<TradingRecord>)
				dao.query((PreparedQuery<TradingRecord>) dao.queryBuilder().where().eq(TradingRecord.TO, userid)
						.or().eq(TradingRecord.FROM, userid).and().ge(TradingRecord.CREATETIME, time).prepare());
		return bill;
	}//总账单


	public ArrayList<TradingRecord> getToBill(String userid,long time) throws SQLException {
		ArrayList<TradingRecord> bill = (ArrayList<TradingRecord>)
				dao.query((PreparedQuery<TradingRecord>) dao.queryBuilder().where().eq(TradingRecord.TO, userid)
						.and().ge(TradingRecord.CREATETIME, time).prepare());
		return bill;

	}
	
	public ArrayList<TradingRecord> getFromBill(String userid,long time) throws SQLException {
		ArrayList<TradingRecord> bill = (ArrayList<TradingRecord>)
				dao.query((PreparedQuery<TradingRecord>) dao.queryBuilder().where().eq(TradingRecord.FROM, userid)
						.and().ge(TradingRecord.CREATETIME, time).prepare());;

		return bill;
	}
	
	
}
