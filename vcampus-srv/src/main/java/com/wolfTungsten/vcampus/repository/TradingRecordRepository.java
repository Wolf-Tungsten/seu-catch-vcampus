package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.TradingRecord;

public class TradingRecordRepository extends CurdRepository<TradingRecord>
{

	public TradingRecordRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, TradingRecord.class);
		
	}

}
