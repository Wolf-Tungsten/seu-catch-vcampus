package com.wolfTungsten.vcampus.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;
import java.util.List;

import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;
import com.wolfTungsten.vcampus.entity.TradingRecord;
import com.wolfTungsten.vcampus.repository.AccountBalanceRepository;
import com.wolfTungsten.vcampus.entity.AccountBalance;
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
			String userid=(String)request.getParams().get(AccountBalance.USER_ID);
			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			try
			{
				orm.accountBalanceRepository.check(userid, secretPassword);
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
	
	private BaseController.BaseHandle tradeHandle = new BaseController.BaseHandle() {
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String userid=(String)request.getParams().get(AccountBalance.USER_ID);
			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			String from=(String)request.getParams().get(TradingRecord.FROM);
			String to=(String)request.getParams().get(TradingRecord.TO);
			String token = (String)request.getParams().get(Token.TOKEN);
			double value=(double)request.getParams().get(TradingRecord.VALUE);
			long createTime=(long)request.getParams().get(TradingRecord.CREATETIME);
			try
			{
				orm.accountBalanceRepository.check(userid, secretPassword);
				orm.tradingRecordRepository.addTradingRecord(from,to,value,createTime);
				response.setSuccess(true);		
				//System.out.println(String.format("付款方：%s - 金额：%s - 收款方：%s - 创建时间：%s", from, value,to,createTime));
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
	
	private BaseController.BaseHandle balanceHandle = new BaseController.BaseHandle() {
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		String userid=(String)request.getParams().get(AccountBalance.USER_ID);
    		String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			try
			{
				orm.accountBalanceRepository.check(userid, secretPassword);
				orm.accountBalanceRepository.getBalance(userid, secretPassword);
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
    		try
			{
    			orm.accountBalanceRepository.getToBill(userid, secretPassword);
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
    		try
			{
    			orm.accountBalanceRepository.getFromBill(userid, secretPassword);
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
    		try
			{
    			orm.accountBalanceRepository.getBill(userid, secretPassword);
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
