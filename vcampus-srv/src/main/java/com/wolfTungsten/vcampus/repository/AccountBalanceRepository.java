package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.ORM;
import com.wolfTungsten.vcampus.entity.AccountBalance;


public class AccountBalanceRepository	extends CurdRepository<AccountBalance>
{
	protected ORM orm=ORM.getInstance();
	public AccountBalanceRepository(ConnectionSource conn)throws SQLException
	{
		super(conn, AccountBalance.class);
		// TODO Auto-generated constructor stub
	}
	public Boolean check(String userid,String secretPassword)throws SQLException{
		List<AccountBalance> accountBalanceList = dao.query((PreparedQuery<AccountBalance>)dao.queryBuilder()
				.where().eq(AccountBalance.USER_ID, userid).and()
				.eq(AccountBalance.SECRETPASSWORD, secretPassword).prepare());
		if(accountBalanceList.size()!=0)
		{
			return true;
		}else {
			return false;
		}
	}
	public double getBalance(String userid,String secretPassword) throws SQLException {
		if(check(userid,secretPassword))
		{
			return orm.tradingRecordRepository.calculateTo(userid)-orm.tradingRecordRepository.calculateFrom(userid);
		}else {
			throw new SQLException("用户名或支付密码错误！");
		}
	}
}
