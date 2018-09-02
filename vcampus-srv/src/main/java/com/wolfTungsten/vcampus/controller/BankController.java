package com.wolfTungsten.vcampus.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;
import com.wolfTungsten.vcampus.entity.TradingRecord;

public class BankController extends BaseController{
	public BankController() {
		super();
		this.addHandle("deposit", depositHandle);//存款
		this.addHandle("withdrawal", withdrawalHandle);//取款
		this.addHandle("pay", payHandle);//转账（包括支付）
		this.addHandle("balance", balanceHandle);//查询余额
		this.addHandle("bill", billHandle);//查询账单
		this.addHandle("secretPassword", payPasswordHandle);//修改支付密码
	}

	private BaseController.BaseHandle depositHandle = new BaseController.BaseHandle() {
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String from=(String)request.getParams().get(TradingRecord.FROM);
			String to=(String)request.getParams().get(TradingRecord.TO);
			String value=(String)request.getParams().get(TradingRecord.VALUE);
			long createTime=(long)request.getParams().get(TradingRecord.CREATETIME);
			try
			{
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
    private BaseController.BaseHandle withdrawalHandle = new BaseController.BaseHandle() {
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		return null;
		}
	};
	private BaseController.BaseHandle payHandle = new BaseController.BaseHandle() {
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		return null;
		}
	};
	private BaseController.BaseHandle balanceHandle = new BaseController.BaseHandle() {
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		return null;
		}
	};
	private BaseController.BaseHandle billHandle = new BaseController.BaseHandle() {
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		return null;
		}
	};
	private BaseController.BaseHandle payPasswordHandle = new BaseController.BaseHandle() {
    	@Override
    	public Response work(Request request)
		{
    		Response response = new Response();
    		return null;
		}
	};
}
