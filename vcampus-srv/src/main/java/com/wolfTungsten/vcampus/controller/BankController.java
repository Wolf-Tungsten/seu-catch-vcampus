package com.wolfTungsten.vcampus.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

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
			return null;
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
