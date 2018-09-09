package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.ORM;
import com.wolfTungsten.vcampus.entity.AccountBalance;
import com.wolfTungsten.vcampus.entity.TradingRecord;
import com.wolfTungsten.vcampus.entity.User;;

public class AccountBalanceRepository	extends CurdRepository<AccountBalance>
{
	protected ORM orm=ORM.getInstance();
	public AccountBalanceRepository(ConnectionSource conn)throws SQLException
	{
		super(conn, AccountBalance.class);
		// TODO Auto-generated constructor stub
	}
	
	public void addAccountBalance(String cardnum,String idCardNum,String secretPassword) throws SQLException {
		
		List<AccountBalance> accountBalanceList = dao.query((PreparedQuery<AccountBalance>)dao.queryBuilder()
				.where().eq(AccountBalance.CARDNUM, cardnum).and().eq(AccountBalance.IDCARDNUM, idCardNum).prepare());
		if(accountBalanceList.size()==0) {
		AccountBalance accountBalance=new AccountBalance();
		accountBalance.setCardnum(cardnum);
		accountBalance.setIdcardNum(idCardNum);
		accountBalance.setSecretPassword(secretPassword);
		dao.create(accountBalance);
		}else
		{
			throw new SQLException("该用户已注册");
		}
	}
	//身份核对
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
	
	public void changeSecretPassword(String newPassword)throws SQLException{
			new AccountBalance().setSecretPassword(newPassword);
	}
	

}
