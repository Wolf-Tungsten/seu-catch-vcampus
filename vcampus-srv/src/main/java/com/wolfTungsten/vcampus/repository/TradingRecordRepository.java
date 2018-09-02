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
	
	public void addTradingRecord(String from, String to,String value,long createTime )throws SQLException {
		TradingRecord tradingRecord=new TradingRecord();
		tradingRecord.setFrom(from);
		tradingRecord.setTo(to);
		tradingRecord.setValue(value);
		tradingRecord.setCreateTime(createTime);
		dao.create(tradingRecord);
	}//增加一条记录
	

}
