package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.ABalanceXTRecord;


public class ABalanceXTRecordRepository extends CurdRepository<ABalanceXTRecord>
{

	public ABalanceXTRecordRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, ABalanceXTRecord.class);
		
	}

}
