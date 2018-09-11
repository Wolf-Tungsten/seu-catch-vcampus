package com.wolfTungsten.vcampus.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampus.entity.Goods;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

public class ShopController extends BaseController
{
	public ShopController () {
		super();
		
		
	}
	//未测试
	private BaseController.BaseHandle addGoodsHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();			
			String name = (String)request.getParams().get(Goods.NAME);
			String description = (String)request.getParams().get(Goods.DESCRIPTION);
			String seller = (String)request.getParams().get(Goods.SELLER);
			Double price = (Double)request.getParams().get(Goods.PRICE);
			int amount = (int)(double)request.getParams().get(Goods.AMOUNT);
			String image = (String)request.getParams().get(Goods.IMAGE);
			int type = (int)(double) request.getParams().get("type");//后面加
			String token = request.getToken();
			
			try
			{
				checkToken(token);
				orm.goodsRepository.addGoods(name, description, seller, price, amount, image);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		
		}
	};
	//从前端接受一个删除商品的uuid列表
	private BaseController.BaseHandle deleteGoodsHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			
			Response response = new Response();
			String token = request.getToken();
			ArrayList<String> goodidlist = 
					(ArrayList<String>) request.getParams().get("deletelist");//待删商品uuid列表
			try {
				checkToken(token);
			for(String uuid:goodidlist) {
				orm.goodsRepository.deleteGoodsByUuid(uuid);
				
			}
			response.setSuccess(true);
			return response;
			}catch(SQLException e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		
		}
	};
	
	private BaseController.BaseHandle updateGoodsHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			ArrayList<LinkedTreeMap<String,Object>> goodsinfomaplist = 
					(ArrayList<LinkedTreeMap<String, Object>>) request.getParams().get("Goodinfomaplist");
			try {
				checkToken(token);
			for(LinkedTreeMap<String,Object> goodinfomap:goodsinfomaplist) {
				orm.goodsRepository.updateGoods2(goodinfomap);
			}
			response.setSuccess(true);
			return response;
			}catch(SQLException e){
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
			
			
		}
	};
	//按flag搜索返回商品  未完成  等待一手接管商店
	private BaseController.BaseHandle queryByFlagHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String flag = (String) request.getParams().get("type");//后面加type
			ArrayList<HashMap<String,Object>> goodsinfomaplist = new ArrayList<>();
			// ArrayList<HashMap<String,Object>> goodsinfomaplist = orm.goodsRepository.inquireByFlag(Goods.TYPE, flag);
			response.setSuccess(true);
			return response;
			
		
		}
	};
	//立即购买
	private BaseController.BaseHandle purchaseNowHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String uuid = (String) request.getParams().get(Goods.UUID);
			int amount = (int) request.getParams().get(Goods.AMOUNT);
			double price = (double)request.getParams().get(Goods.PRICE);
			
			
			
			return null;
		}
	};
	//购物车总购买
	
}
