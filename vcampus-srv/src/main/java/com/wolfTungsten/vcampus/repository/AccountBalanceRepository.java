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
	
	public Boolean changeSecretPassword(String userid,String oldPassword,String newPassword)throws SQLException{
		if(check(userid,oldPassword))
		{
			new AccountBalance().setSecretPassword(newPassword);
			return true;
		}else {
			throw new SQLException("用户名或支付密码错误！");
		}
	}
	
/*
	//查询余额
	public double getBalance(String userid,String secretPassword) throws SQLException {
		if(check(userid,secretPassword))
		{
			return orm.tradingRecordRepository.calculateTo(userid)-orm.tradingRecordRepository.calculateFrom(userid);
		}else {
			throw new SQLException("用户名或支付密码错误！");
		}
	}
	
	//查询支出账单
	public List<TradingRecord> getFromBill(String userid,String secretPassword) throws SQLException {
		if(check(userid,secretPassword))
		{
			return orm.tradingRecordRepository.getFromBill(userid);
		}else {
			throw new SQLException("用户名或支付密码错误！");
		}
	}
	
	//查询收入账单
	public List<TradingRecord> getToBill(String userid,String secretPassword) throws SQLException {
		if(check(userid,secretPassword))
		{
			return orm.tradingRecordRepository.getToBill(userid);
		}else {
			throw new SQLException("用户名或支付密码错误！");
		}
	}
	
	public List<TradingRecord> getBill(String userid,String secretPassword) throws SQLException {
		if(check(userid,secretPassword))
		{
			return orm.tradingRecordRepository.getBill(userid);
		}else {
			throw new SQLException("用户名或支付密码错误！");
		}
	}
	*/
}
