package com.wolfTungsten.vcampus.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;
import com.wolfTungsten.vcampus.entity.TradingRecord;
import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.repository.AccountBalanceRepository;
import com.j256.ormlite.stmt.PreparedQuery;
import com.wolfTungsten.vcampus.entity.AccountBalance;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.Token;

public class BankController extends BaseController{
	public BankController() {
		super();
		this.addHandle("register", registerHandle);//注册银行账户（设置支付密码）
		this.addHandle("trade", tradeHandle);//存款,取款，转账
		this.addHandle("balance", balanceHandle);//查询余额
		this.addHandle("bill", billHandle);//查询总账单
		this.addHandle("fromBill", fromBillHandle);//查询支出账单
		this.addHandle("toBill", toBillHandle);//查询收入账单
		this.addHandle("secretPassword", secretPasswordHandle);//修改支付密码
	}

	private BaseController.BaseHandle registerHandle = new BaseController.BaseHandle() {
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String cardnum=(String)request.getParams().get(AccountBalance.CARDNUM);
			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			String idCardNum=(String)request.getParams().get(AccountBalance.IDCARDNUM);
			try
			{
				if(orm.userRepository.checkExist(cardnum, idCardNum))//检测是否存在该卡号
				{
					orm.accountBalanceRepository.addAccountBalance(cardnum, idCardNum, secretPassword);
				}
				response.setSuccess(true);		
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "注册失败,"+e.getMessage());
				return response;
			
			}
		}
	};
<<<<<<< HEAD
	
=======
>>>>>>> 3250d2baade4a7f891dd780198a0f6d750227c53

	private BaseController.BaseHandle tradeHandle = new BaseController.BaseHandle() {
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String userid=(String)request.getParams().get(AccountBalance.USER_ID);
			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			String from=(String)request.getParams().get(TradingRecord.FROM);
			String to=(String)request.getParams().get(TradingRecord.TO);
			long createTime = System.currentTimeMillis() / 1000;
			//String token = (String)request.getParams().get(Token.TOKEN);
			double value=(double)request.getParams().get(TradingRecord.VALUE);
			try
			{
				orm.accountBalanceRepository.check(userid, secretPassword);
				orm.tradingRecordRepository.addTradingRecord(from,to,value,createTime);
				response.setSuccess(true);		
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "交易失败,"+e.getMessage());
				return response;
			
			}
		}
		
	};
	
	private BaseController.BaseHandle balanceHandle = new BaseController.BaseHandle() {
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		String userid=(String)request.getParams().get(AccountBalance.USER_ID);
    		String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
    		double balance=0;
			try
			{
				orm.accountBalanceRepository.check(userid, secretPassword);
				balance=orm.tradingRecordRepository.calculateTo(userid)-orm.tradingRecordRepository.calculateFrom(userid);
				response.getBody().put("balance", balance);
				response.setSuccess(true);		
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "数据库读写出错,"+e.getMessage());
				return response;
			
			}
		}
};
	private BaseController.BaseHandle toBillHandle = new BaseController.BaseHandle() {
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		String userid=(String)request.getParams().get(AccountBalance.USER_ID);
    		String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
    		ArrayList<HashMap<String, Object>> toBill = new ArrayList<>();
    		try
			{
    			orm.accountBalanceRepository.check(userid,secretPassword);
    			orm.tradingRecordRepository.getBill(TradingRecord.TO, userid);
    			response.getBody().put("billMap", toBill);
				response.setSuccess(true);		
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "数据库读写出错,"+e.getMessage());
				return response;
			
			}
		}
	};
	
	private BaseController.BaseHandle fromBillHandle = new BaseController.BaseHandle() {
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		String userid=(String)request.getParams().get(AccountBalance.USER_ID);
    		String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
    		ArrayList<HashMap<String, Object>> fromBill = new ArrayList<>();
    		try
			{
    			orm.accountBalanceRepository.check(userid,secretPassword);
    			orm.tradingRecordRepository.getBill(TradingRecord.FROM, userid);
    			response.getBody().put("billMap", fromBill);
				response.setSuccess(true);		
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "数据库读写出错,"+e.getMessage());
				return response;
			
			}
		}
	};
	
	private BaseController.BaseHandle billHandle = new BaseController.BaseHandle() {
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		String userid=(String)request.getParams().get(AccountBalance.USER_ID);
    		String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
    		ArrayList<TradingRecord> bill=new ArrayList<>();
    		try
			{
    			orm.accountBalanceRepository.check(userid, secretPassword);
    			bill=orm.tradingRecordRepository.getBill(userid);
    			response.getBody().put("bill", bill);
				response.setSuccess(true);		
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "数据库读写出错,"+e.getMessage());
				return response;
			
			}
		}
	};
	
	private BaseController.BaseHandle secretPasswordHandle = new BaseController.BaseHandle() {
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		String userid=(String)request.getParams().get(AccountBalance.USER_ID);
			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			try
			{
    			orm.accountBalanceRepository.changeSecretPassword(userid, secretPassword, secretPassword);
				response.setSuccess(true);		
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "数据库读写出错,"+e.getMessage());
				return response;
			
			}
		}
	};
}
