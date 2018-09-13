/*
 * ClassName :BankController.java
 * 
 * Date:2018/9/13
 */
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

/*
 * 银行模块的控制类，用于接受客户端请求并作出相应响应
 * @author 申池冉 
 */
public class BankController extends BaseController{
	public BankController() {
		super();
		this.addHandle("checkBankUser", bankUserHandle);
		this.addHandle("checkPassword", checkHandle);
		this.addHandle("register", registerHandle);//注册银行账户（设置支付密码）
		this.addHandle("trade", tradeHandle);//取款，转账
		this.addHandle("balance", balanceHandle);//查询余额
		this.addHandle("bill", billHandle);//查询总账单
		this.addHandle("fromBill", fromBillHandle);//查询支出账单
		this.addHandle("toBill", toBillHandle);//查询收入账单
		this.addHandle("secretPassword", secretPasswordHandle);//修改支付密码
		this.addHandle("deposit", depositHandle);
		this.addHandle("withdraw", withdrawHandle);
	}

	private BaseController.BaseHandle bankUserHandle = new BaseController.BaseHandle() {
		/*
		 * 这个Handle提供了银行用户的注册功能
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			try
			{
				String user_id=checkToken(token);
				Boolean registerCheck=orm.accountBalanceRepository.checkBankUser(user_id);
				response.getBody().put("registerPanel", registerCheck);
				response.setSuccess(true);
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "注册失败,"+e.getMessage());
				return response;
			
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};
	private BaseController.BaseHandle checkHandle = new BaseController.BaseHandle() {
		/**
		 * 这个Handle用于检测用户状态
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			String token = request.getToken();
			try
			{
				String user_id=checkToken(token);
				if(orm.accountBalanceRepository.check(user_id, secretPassword))
				{
					response.setSuccess(true);
				}else {
					response.setSuccess(false);
				}		
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "注册失败,"+e.getMessage());
				return response;
			
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};
	private BaseController.BaseHandle registerHandle = new BaseController.BaseHandle() {
		/**
		 * 注册银行账户，设置支付密码
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String cardnum=(String)request.getParams().get(AccountBalance.CARDNUM);
			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			String idcardNum=(String)request.getParams().get(AccountBalance.IDCARDNUM);
			
			String token = request.getToken();
			try
			{
				String user_id=checkToken(token);
				if(orm.userRepository.checkExist(cardnum, idcardNum))//检测是否存在该卡号
				{
					orm.accountBalanceRepository.addAccountBalance(user_id,cardnum, idcardNum, secretPassword);
					response.setSuccess(true);
				}else {
					response.setSuccess(false);
				}		
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "注册失败,"+e.getMessage());
				return response;
			
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};


	//000001为中国银行账户卡号

	private BaseController.BaseHandle depositHandle = new BaseController.BaseHandle() {
		/**
		 * 这个Handle是干啥的我们要问一下原作者
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			
			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			long createTime = System.currentTimeMillis() / 1000;//时间戳
			String token = request.getToken();
			double value=(double)request.getParams().get(TradingRecord.VALUE);
			value=value*100;
			try
			{
				String userid=checkToken(token);
				User user = orm.userRepository.inquireByCardnum("000001");
				if(orm.accountBalanceRepository.check(userid, secretPassword))
				{
					orm.tradingRecordRepository.deposit(user.getUuid().toString(),userid,value,createTime);
				    response.setSuccess(true);
				}else {
					response.setSuccess(false);
				}
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "交易失败,"+e.getMessage());
				return response;
			
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
		
	
	};
	private BaseController.BaseHandle tradeHandle = new BaseController.BaseHandle() {
		/**
		 * 取款和转账
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();

			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			String to=(String)request.getParams().get(AccountBalance.CARDNUM);
			long createTime = System.currentTimeMillis() / 1000;//时间戳
			String token = request.getToken();
			double value=(double)request.getParams().get(TradingRecord.VALUE);
			value=value*100;
			try
			{
				String userid=checkToken(token);
				AccountBalance toUser=new AccountBalance();
				toUser=orm.accountBalanceRepository.findUserid(to);
				if(orm.accountBalanceRepository.check(userid, secretPassword))
				{
					if(orm.accountBalanceRepository.checkTrade(to))
					{
					  orm.tradingRecordRepository.addTradingRecord(userid,toUser.getUserid(),value,createTime);
					  response.setSuccess(true);	
					}else {
						response.setSuccess(false);
					}
				   	
				}else {
					response.setSuccess(false);
				}
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "交易失败,"+e.getMessage());
				return response;
			
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
		
	};
	private BaseController.BaseHandle withdrawHandle = new BaseController.BaseHandle() {
		/**
		 * 验证用户状态
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			
			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			long createTime = System.currentTimeMillis() / 1000;//时间戳
			String token = request.getToken();
			
			double value=(double)request.getParams().get(TradingRecord.VALUE);
			value=value*100;
			try
			{
				
				User bankadmin = orm.userRepository.inquireByCardnum("000001");
				String userid=checkToken(token);
				if(orm.accountBalanceRepository.check(userid, secretPassword))
				{
					orm.tradingRecordRepository.deposit(userid,bankadmin.getUuid().toString(),value,createTime);
				    response.setSuccess(true);		
				}else {
					response.setSuccess(false);
				}
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "交易失败,"+e.getMessage());
				return response;
			
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};
	
	private BaseController.BaseHandle balanceHandle = new BaseController.BaseHandle() {
		/**
		 * 查询余额
		 */
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		String token = request.getToken();
    		long createTime = System.currentTimeMillis() / 1000;//时间戳
    		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Date date=new Date(createTime*1000);
    		
    		double balance=0;
			try
			{
				  String userid=checkToken(token);
				  User user= orm.userRepository.inquireById(userid);
				  balance=orm.tradingRecordRepository.calculateBalance(userid);
				  response.getBody().put("currentTime", time.format(date));
				  response.getBody().put("remain", balance);
				  response.getBody().put(User.USERNAME, user.getUsername());
				  response.getBody().put(User.CARDNUM, user.getCardnum());
				  response.setSuccess(true);
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "查询余额出错,"+e.getMessage());
				return response;
			
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};
	private BaseController.BaseHandle toBillHandle = new BaseController.BaseHandle() {
		/**
		 * 查询收入账单
		 */
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		String token = request.getToken();
    		long currentTime = System.currentTimeMillis() / 1000;//时间戳
    		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		long period=(long)(double)request.getParams().get("period");
    		ArrayList<TradingRecord> toBill = new ArrayList<>();
    		ArrayList<HashMap<String,Object>> tradingRecordList=new ArrayList();
    		try
			{
    			String userid=checkToken(token);
    			User userTo=orm.userRepository.inquireById(userid);
    			toBill=orm.tradingRecordRepository.getToBill(userid,currentTime-period);
    				for(TradingRecord b:toBill) {
    		    		Date date=new Date(b.getCreateTime()*1000);
    					User userFrom=orm.userRepository.inquireById(b.getFrom());
    					HashMap<String,Object>record = new HashMap<>();
    					record.remove(TradingRecord.FROM);
    					record.remove(TradingRecord.TO);
    					record.remove(TradingRecord.UUID);
    					record.remove(TradingRecord.VALUE);
    					record.put("fromCardnum",userFrom.getCardnum());
    					record.put("fromName",userFrom.getUsername());
    					record.put(TradingRecord.VALUE, b.getValue()/100);
    					record.put(TradingRecord.CREATETIME, time.format(date));
    					tradingRecordList.add(record);
    				}
    				response.getBody().put("myCardnum",userTo.getCardnum());
    				response.getBody().put("myName",userTo.getUsername());
    		        response.getBody().put("toBill", tradingRecordList);
				    response.setSuccess(true);	
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "查询收入账单出错,"+e.getMessage());
				return response;
			
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};
	
	private BaseController.BaseHandle fromBillHandle = new BaseController.BaseHandle() {
		/**
		 * 查询支出账单
		 */
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		String token = request.getToken();
    		long currentTime = System.currentTimeMillis() / 1000;//时间戳
    		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		
    		long period=(long)(double)request.getParams().get("period");
    		ArrayList<TradingRecord> fromBill=new ArrayList<>();
    		ArrayList<HashMap<String,Object>> tradingRecordList=new ArrayList();
    		try
			{
    			String userid=checkToken(token);
    			User userFrom=orm.userRepository.inquireById(userid);
    			fromBill=orm.tradingRecordRepository.getFromBill(userid,currentTime-period);
    			for(TradingRecord b:fromBill) {
    					Date date=new Date(b.getCreateTime()*1000);
    					
    					User userTo=orm.userRepository.inquireById(b.getTo());
    					HashMap<String,Object>record = new HashMap<>();
    					record.remove(TradingRecord.FROM);
    					record.remove(TradingRecord.TO);
    					record.remove(TradingRecord.UUID);
    					record.remove(TradingRecord.VALUE);

    					record.put("toCardnum",userTo.getCardnum());
    					record.put("toName",userTo.getUsername());
    					record.put(TradingRecord.VALUE, b.getValue()/100);
    					record.put(TradingRecord.CREATETIME, time.format(date));
    					tradingRecordList.add(record);
    				}
    			    response.getBody().put("myCardnum",userFrom.getCardnum());
				    response.getBody().put("myName",userFrom.getUsername());
    			    response.getBody().put("fromBill", tradingRecordList);
				    response.setSuccess(true);	
				 return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "查询支出账单出错,"+e.getMessage());
				return response;
			
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};
	
	private BaseController.BaseHandle billHandle = new BaseController.BaseHandle() {
		/**
		 * 查询所有账单
		 */
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		String token = request.getToken();
    		long currentTime = System.currentTimeMillis() / 1000;//时间戳
    		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		long period=(long)(double)request.getParams().get("period");
    		ArrayList<TradingRecord> bill=new ArrayList<>();
    		ArrayList<HashMap<String,Object>> tradingRecordList=new ArrayList();
    		try
			{
    			String userid=checkToken(token);
                User me=orm.userRepository.inquireById(userid);
    			bill=orm.tradingRecordRepository.getBill(userid,currentTime-period);
    				for(TradingRecord b:bill) {
    		    		Date date=new Date(b.getCreateTime()*1000);
    					User userFrom=orm.userRepository.inquireById(b.getFrom());
    					User userTo=orm.userRepository.inquireById(b.getTo());
    					HashMap<String,Object>record = new HashMap<>();
//    					record.remove(TradingRecord.FROM);
//    					record.remove(TradingRecord.TO);
//    					record.remove(TradingRecord.UUID);
//    					record.remove(TradingRecord.VALUE);
    					if(userid.equals(b.getTo())) {
    						record.put("otherCardnum", userFrom.getCardnum());
    						record.put("otherName", userFrom.getUsername());
    						record.put(TradingRecord.VALUE, "+"+String.valueOf(b.getValue()/100));
    						record.put(TradingRecord.CREATETIME, time.format(date));
    					}else if(userid.equals(b.getFrom())) {
    						record.put("otherCardnum", userTo.getCardnum());
    						record.put("otherName",userTo.getUsername());
    						record.put(TradingRecord.VALUE, "-"+String.valueOf(b.getValue()/100));
        					record.put(TradingRecord.CREATETIME, time.format(date));
    					}
    				
    					tradingRecordList.add(record);
    				}
    				response.getBody().put("myName", me.getUsername());
    				response.getBody().put("myCardNum", me.getCardnum());
    			    response.getBody().put("bill", tradingRecordList);
				    response.setSuccess(true);	
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "查询总账单出错,"+e.getMessage());
				return response;
			
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};
	
	private BaseController.BaseHandle secretPasswordHandle = new BaseController.BaseHandle() {
		/**
		 * 修改支付密码
		 */
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		
			String secretPassword=(String)request.getParams().get(AccountBalance.SECRETPASSWORD);
			String newPassword=(String)request.getParams().get("newSecretPassword");
			String token = request.getToken();
			try
			{
				String userid=checkToken(token);
				if(orm.accountBalanceRepository.check(userid,secretPassword))
    			{
					orm.accountBalanceRepository.changeSecretPassword(userid,newPassword);
					response.getBody().put("result", "修改密码成功:"+newPassword);
					response.setSuccess(true);
    			}else {
					response.setSuccess(false);
				}		
				return response;	
			} catch (SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "修改密码出错,"+e.getMessage());
				return response;
			
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};
}
