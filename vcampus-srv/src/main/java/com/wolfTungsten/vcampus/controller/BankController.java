package com.wolfTungsten.vcampus.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
			value=value*100;
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
    		long createTime = System.currentTimeMillis() / 1000;//时间戳
    		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Date date=new Date(createTime*1000);
    		
    		double balance=0;
			try
			{
				checkToken(token);
				if(orm.accountBalanceRepository.check(userid, secretPassword))
				{
				  balance=orm.tradingRecordRepository.calculateBalance(userid);
				  response.getBody().put("currentTime", time.format(date));
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
    		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		long period=(long)(double)request.getParams().get("period");
    		ArrayList<TradingRecord> toBill = new ArrayList<>();
    		ArrayList<HashMap<String,Object>> tradingRecordList=new ArrayList();
    		try
			{
    			checkToken(token);
    			if(orm.accountBalanceRepository.check(userid,secretPassword))
    			{
    				toBill=orm.tradingRecordRepository.getToBill(userid,currentTime-period);
    				for(TradingRecord b:toBill) {
    		    		Date date=new Date(b.getCreateTime()*1000);
    					User userFrom=orm.userRepository.inquireById(b.getFrom());
    					User userTo=orm.userRepository.inquireById(b.getTo());
    					HashMap<String,Object>record = new HashMap<>();
    					record.remove(TradingRecord.FROM);
    					record.remove(TradingRecord.TO);
    					record.remove(TradingRecord.UUID);
    					record.remove(TradingRecord.VALUE);
    					record.put("fromCardnum",userFrom.getCardnum());
    					record.put("fromName",userFrom.getUsername());
    					record.put("toCardnum",userTo.getCardnum());
    					record.put("toName",userTo.getUsername());
    					record.put(TradingRecord.VALUE, b.getValue()/100);
    					record.put(TradingRecord.CREATETIME, time.format(date));
    					tradingRecordList.add(record);
    				}
    		        response.getBody().put("toBill", tradingRecordList);
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
    		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		
    		long period=(long)(double)request.getParams().get("period");
    		ArrayList<TradingRecord> fromBill=new ArrayList<>();
    		ArrayList<HashMap<String,Object>> tradingRecordList=new ArrayList();
    		try
			{
    			checkToken(token);
    			if(orm.accountBalanceRepository.check(userid,secretPassword))
    			{
    				fromBill=orm.tradingRecordRepository.getFromBill(userid,currentTime-period);
    				for(TradingRecord b:fromBill) {
    					Date date=new Date(b.getCreateTime()*1000);
    					User userFrom=orm.userRepository.inquireById(b.getFrom());
    					User userTo=orm.userRepository.inquireById(b.getTo());
    					HashMap<String,Object>record = new HashMap<>();
    					record.remove(TradingRecord.FROM);
    					record.remove(TradingRecord.TO);
    					record.remove(TradingRecord.UUID);
    					record.remove(TradingRecord.VALUE);
    					record.put("fromCardnum",userFrom.getCardnum());
    					record.put("fromName",userFrom.getUsername());
    					record.put("toCardnum",userTo.getCardnum());
    					record.put("toName",userTo.getUsername());
    					record.put(TradingRecord.VALUE, b.getValue()/100);
    					record.put(TradingRecord.CREATETIME, time.format(date));
    					tradingRecordList.add(record);
    				}
    			    response.getBody().put("fromBill", tradingRecordList);
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
    		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		long period=(long)(double)request.getParams().get("period");
    		ArrayList<TradingRecord> bill=new ArrayList<>();
    		ArrayList<HashMap<String,Object>> tradingRecordList=new ArrayList();
    		try
			{
    			checkToken(token);
    			if(orm.accountBalanceRepository.check(userid, secretPassword))
    			{
    			    bill=orm.tradingRecordRepository.getBill(userid,currentTime-period);
    				for(TradingRecord b:bill) {
    		    		Date date=new Date(b.getCreateTime()*1000);
    					User userFrom=orm.userRepository.inquireById(b.getFrom());
    					User userTo=orm.userRepository.inquireById(b.getTo());
    					HashMap<String,Object>record = new HashMap<>();
    					record.remove(TradingRecord.FROM);
    					record.remove(TradingRecord.TO);
    					record.remove(TradingRecord.UUID);
    					record.remove(TradingRecord.VALUE);
    					record.put("fromCardnum",userFrom.getCardnum());
    					record.put("fromName",userFrom.getUsername());
    					record.put("toCardnum",userTo.getCardnum());
    					record.put("toName",userTo.getUsername());
    					record.put(TradingRecord.VALUE, b.getValue()/100);
    					record.put(TradingRecord.CREATETIME, time.format(date));
    					tradingRecordList.add(record);
    				}
    			    response.getBody().put("bill", tradingRecordList);
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
					orm.accountBalanceRepository.changeSecretPassword(userid,newPassword);
					response.getBody().put("result", "修改密码成功:"+newPassword);
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
