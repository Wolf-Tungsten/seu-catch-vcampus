package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.AccountBalance;

public class AccountBalanceRepository	extends CurdRepository<AccountBalance>
{

	public AccountBalanceRepository(ConnectionSource conn)throws SQLException
	{
		super(conn, AccountBalance.class);
		// TODO Auto-generated constructor stub
	}
	
}
