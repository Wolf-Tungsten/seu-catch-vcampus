package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.ORM;
import com.wolfTungsten.vcampus.entity.AccountBalance;
import com.wolfTungsten.vcampus.entity.TradingRecord;
import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.entity.UserXBook;;

public class AccountBalanceRepository	extends CurdRepository<AccountBalance>
{
	protected ORM orm=ORM.getInstance();
	public AccountBalanceRepository(ConnectionSource conn)throws SQLException
	{
		super(conn, AccountBalance.class);
		// TODO Auto-generated constructor stub
	}
	
	public void addAccountBalance(String userid,String cardnum,String idCardNum,String secretPassword) throws SQLException {
		
		List<AccountBalance> accountBalanceList = dao.query((PreparedQuery<AccountBalance>)dao.queryBuilder()
				.where().eq(AccountBalance.CARDNUM, cardnum).and().eq(AccountBalance.IDCARDNUM, idCardNum).prepare());
		if(accountBalanceList.size()==0) {
		AccountBalance accountBalance=new AccountBalance();
		accountBalance.setUserid(userid);
		accountBalance.setCardnum(cardnum);
		accountBalance.setIdcardNum(idCardNum);
		accountBalance.setSecretPassword(secretPassword);
		dao.create(accountBalance);
		}else
		{
			throw new SQLException("卡号或者身份证号错误");
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
	
	public void changeSecretPassword(String userid,String newPassword)throws SQLException{
		dao.update((PreparedUpdate<AccountBalance>)dao.updateBuilder()
				.updateColumnValue(AccountBalance.SECRETPASSWORD,newPassword)
				.where().eq(AccountBalance.USER_ID,userid).prepare());
	}
	
	public Boolean checkBankUser(String userid) throws SQLException{
		List<AccountBalance> userList=dao.query((PreparedQuery<AccountBalance>)dao.queryBuilder()
				.where().eq(AccountBalance.USER_ID, userid).prepare());
		if(userList.size()==0)
		{return true;}
		else {return false;}
	}
	
	public Boolean checkTrade(String toCardNum) throws SQLException {
		List<AccountBalance> userList = 
				dao.query((PreparedQuery<AccountBalance>) dao.queryBuilder().where().eq(AccountBalance.CARDNUM, toCardNum).prepare());
		if(userList.size()!=0) {
			return true;
		}
		else	
			throw new SQLException("交易方不存在");	 	
	}
	
	public AccountBalance findUserid(String toCardNum)throws SQLException {
		List<AccountBalance> userlist = dao.queryForEq(AccountBalance.CARDNUM, toCardNum);
		return userlist.get(0);
	}
	

}
