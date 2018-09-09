package com.wolfTungsten.vcampus.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;
import com.wolfTungsten.vcampus.entity.TradingRecord;
import com.wolfTungsten.vcampus.entity.AccountBalance;
import com.wolfTungsten.vcampus.entity.User;

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
			String idcardNum=(String)request.getParams().get(AccountBalance.IDCARDNUM);
			String user_id=(String)request.getParams().get(User.UUID);
			String token = request.getToken();
			try
			{
				checkToken(token);
				if(orm.userRepository.checkExist(cardnum, idcardNum))//检测是否存在该卡号
				{
					orm.accountBalanceRepository.addAccountBalance(user_id,cardnum, idcardNum, secretPassword);
					response.setSuccess(true);
				}		
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


	private BaseController.BaseHandle tradeHandle = new BaseController.BaseHandle() {
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String userid=(String)request.getParams().get(AccountBalance.USER_ID);
			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			String from=(String)request.getParams().get(TradingRecord.FROM);
			String to=(String)request.getParams().get(TradingRecord.TO);
			long createTime = System.currentTimeMillis() / 1000;//时间戳
			String token = request.getToken();
			double value=(double)request.getParams().get(TradingRecord.VALUE);
			try
			{
				checkToken(token);
				if(orm.accountBalanceRepository.check(userid, secretPassword))
				{
					if(orm.userRepository.checkTrade(from)&&orm.userRepository.checkTrade(to))
					{
					  orm.tradingRecordRepository.addTradingRecord(from,to,value,createTime);
					}
				   response.setSuccess(true);		
				}
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
    		String token = request.getToken();
    		double balance=0;
			try
			{
				checkToken(token);
				if(orm.accountBalanceRepository.check(userid, secretPassword))
				{
				  balance=orm.tradingRecordRepository.calculateBalance(userid);
				  response.getBody().put("balance", balance);
				  response.setSuccess(true);
				}
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "查询余额出错,"+e.getMessage());
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
    		String token = request.getToken();
    		long currentTime = System.currentTimeMillis() / 1000;//时间戳
    		long period=(long)request.getParams().get("period");
    		ArrayList<HashMap<String, Object>> toBill = new ArrayList<>();
    		try
			{
    			checkToken(token);
    			if(orm.accountBalanceRepository.check(userid,secretPassword))
    			{
    				orm.tradingRecordRepository.getToBill(userid,currentTime-period);
    		        response.getBody().put("toBill", toBill);
				    response.setSuccess(true);	
    			}
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "查询收入账单出错,"+e.getMessage());
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
    		String token = request.getToken();
    		long currentTime = System.currentTimeMillis() / 1000;//时间戳
    		long period=(long)request.getParams().get("period");
    		ArrayList<HashMap<String, Object>> fromBill = new ArrayList<>();
    		try
			{
    			checkToken(token);
    			if(orm.accountBalanceRepository.check(userid,secretPassword))
    			{
    				orm.tradingRecordRepository.getFromBill(userid,currentTime-period);
    			    response.getBody().put("fromBill", fromBill);
				    response.setSuccess(true);	
				}	
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "查询支出账单出错,"+e.getMessage());
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
    		String token = request.getToken();
    		long currentTime = System.currentTimeMillis() / 1000;//时间戳
    		long period=(long)request.getParams().get("period");
    		ArrayList<HashMap<String, Object>> bill=new ArrayList<>();
    		try
			{
    			checkToken(token);
    			if(orm.accountBalanceRepository.check(userid, secretPassword))
    			{
    			    bill=orm.tradingRecordRepository.getBill(userid,currentTime-period);
    			    response.getBody().put("bill", bill);
				    response.setSuccess(true);	
    			}
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "查询总账单出错,"+e.getMessage());
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
			String newPassword=(String)request.getParams().get("newSecretPassword");
			String token = request.getToken();
			try
			{
				checkToken(token);
				if(orm.accountBalanceRepository.check(userid,secretPassword))
    			{
					orm.accountBalanceRepository.changeSecretPassword(newPassword);
					response.setSuccess(true);
    			}		
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "修改密码出错,"+e.getMessage());
				return response;
			
			}
		}
	};
}
