
package com.wolfTungsten.vcampus.controller;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampus.repository.GoodsRepository;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.AccountBalance;
import com.wolfTungsten.vcampus.entity.Goods;
import com.wolfTungsten.vcampus.entity.Token;
import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.entity.UserXGoods;
import com.wolfTungsten.vcampus.repository.UserXGoodsRepository;

import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

public class MarketController extends BaseController {
	
	public MarketController() {
		super();
		this.addHandle("addGoods", addGoodsHandle);
		this.addHandle("queryByName",queryByNameHandle);
		this.addHandle("queryAll", queryAllHandle);
		this.addHandle("queryBySeller", queryBySellerHandle);
		this.addHandle("queryShoppingCart", queryShoopingCartHandle);
		this.addHandle("queryBought", queryBoughtGoodsHandle);
		this.addHandle("purchase", purchaseHandle);
		this.addHandle("deleteGoods", deleteGoodsHandle);
		this.addHandle("deleteAll", deleteAllGoodsHandle);

	}
	
	//添加商品的Handle
	private BaseController.BaseHandle addGoodsHandle = new BaseHandle() {   
		//测试成功
		@Override
		public Response work(Request request) {
			Response response = new Response();
			
			String name = (String)request.getParams().get(Goods.NAME);
			String description = (String)request.getParams().get(Goods.DESCRIPTION);
			String seller = (String)request.getParams().get(Goods.SELLER);
			Double price = (Double)request.getParams().get(Goods.PRICE);
			int amount = (int)(double)request.getParams().get(Goods.AMOUNT);
			String image = (String)request.getParams().get(Goods.IMAGE);
			//String token = (String) request.getParams().get("token");
			try
			{
				//checkToken(token);
				orm.goodsRepository.addGoods(name, description, seller, price, amount, image);
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
		
	};//end of addGoodHandle
	
	
	private BaseController.BaseHandle queryAllHandle = new BaseHandle() {
		//注释掉token测试完成
		@Override
		public Response work(Request request) {
			Response response = new Response();
			//String token = request.getToken();
			ArrayList<HashMap<String, Object>> goodsinfolist = new ArrayList<>();
			try {
				//checkToken(token);
				goodsinfolist =  orm.goodsRepository.inquireAllGoods();
				response.getBody().put("goodsInfoMapList", goodsinfolist);
				
				response.setSuccess(true);
				return response;
			}catch(SQLException e) {
				response.setSuccess(false);
				e.printStackTrace();
				return response;
			}

		}
	};//end of queryAllHandle
	
	private BaseController.BaseHandle queryByNameHandle = new BaseHandle() {
		
		@Override
		public Response work(Request request) {
			Response response = new Response();
			String value = (String) request.getParams().get(Goods.NAME);
			ArrayList<HashMap<String, Object>> goodsinfolist = new ArrayList<>();
			try
			{
				goodsinfolist = orm.goodsRepository.inquireByFlag(Goods.NAME, value);
				response.getBody().put("goodsInfoMapList",goodsinfolist);
				response.setSuccess(true);
				return response;
			}
			catch (SQLException e) {
				response.setSuccess(false);
				e.printStackTrace();
				return response;
			}

		}

	};//end of queryByNameHandle
	


private BaseController.BaseHandle queryBySellerHandle = new BaseHandle() {
		
		@Override
		public Response work(Request request) 
		{
			Response response = new Response();
			String value = (String) request.getParams().get(Goods.SELLER);
			ArrayList<HashMap<String, Object>> goodsinfolist = new ArrayList<>();
			try
			{
				goodsinfolist = orm.goodsRepository.inquireByFlag(Goods.SELLER,value);
				response.getBody().put("goodsInfoMapList",goodsinfolist);
				response.setSuccess(true);
				return response;
			}
			catch (SQLException e) {
				response.setSuccess(false);
				e.printStackTrace();
				return response;

			}
		}
	};

		private BaseController.BaseHandle purchaseHandle = new BaseHandle() {
			
			@Override
			public Response work(Request request)
			{
				Response response = new Response();
				String uuid=(String)request.getParams().get(Goods.UUID);
				String name = (String)request.getParams().get(Goods.NAME);
				String seller = (String)request.getParams().get(Goods.SELLER);
				String buyer=(String)request.getParams().get(AccountBalance.USER_ID);
				double price=(double)request.getParams().get(Goods.PRICE);
				long createTime = System.currentTimeMillis() / 1000;
				int amount = (int)(double)request.getParams().get(Goods.AMOUNT);
				
				try
				{
					orm.tradingRecordRepository.addTradingRecord(buyer, seller, price, createTime);

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

		private BaseController.BaseHandle updateGoodsHandle = new BaseHandle() {
			//更新商品信息，未测试
			@Override
			public Response work(Request request) {
				return null;
			}
		}; 

		private BaseController.BaseHandle deleteGoodsHandle = new BaseHandle() {
			//待测试
			@Override
			public Response work(Request request) {
				Response response = new Response();
				ArrayList<String> goodsUuidList = (ArrayList<String>) request.getParams().get("uuidList");
				for(String uuid : goodsUuidList)
				{
					try {
						orm.bookRepository.deleteBookByUuid(uuid);
						//这里还要删除掉和用户有关的信息
					}catch(SQLException e){
						response.setSuccess(false);
						e.printStackTrace();
					}
					response.setSuccess(true);
					return response;
				}
				return response;
			}
		};
		
		public BaseController.BaseHandle deleteAllGoodsHandle = new BaseHandle() {
			//大招：从删库到跑路
			@Override
			public Response work(Request request) {
				Response response = new Response();
				//request.getToken();
				try {
					//checkToken(request.getToken());
					orm.goodsRepository.deleteAllGoods();
					response.setSuccess(true);
					return response;
				}catch (SQLException e) {
					//其实我也不知道该抛出啥异常
					response.setSuccess(false);
					e.printStackTrace();
				}
				return response;
			}
		};
		public BaseController.BaseHandle queryShoopingCartHandle = new BaseHandle(){
			//查询某个用户的购物车（通过用户的uuid
			@Override
			public Response work(Request request) {
				Response response = new Response();
				String token = request.getToken();
				//拿user_id
				try {
					String user_id = checkToken(token);
					User user = orm.userRepository.inquireById(user_id);//其实是uuid
					ArrayList<HashMap<String,Object>> recordMapList = orm.userXGoodsRepository
							.inqueryShoppingCart(user_id);
					for(HashMap<String, Object> record:recordMapList)
					{
<<<<<<< HEAD
						//根据商品id查这个人给购物车加了哪些商品
						//Goods goods = orm.goodsRepository.inquireById(user_id);
						//record.remove(UserXGoods.GOOD_ID);
						//record.put(Goods.NAME,goods.getName());
						//record.put(Goods.DESCRIPTION,goods.getDescription());
						//record.put(Goods.SELLER,goods.getSeller());
						//record.put(Goods.PRICE,goods.getPrice());
=======
						
						Goods goods = orm.goodsRepository.inquireById(user_id);
						record.remove(UserXGoods.GOOD_ID);
						record.put(Goods.NAME,goods.getName());
						record.put(Goods.DESCRIPTION,goods.getDescription());
						record.put(Goods.SELLER,goods.getSeller());
						record.put(Goods.PRICE,goods.getPrice());
>>>>>>> 0774ec65b65322664d067fc6ff564e20618431bd
					}
					response.getBody().put("recordMaplist", recordMapList);
					response.getBody().put(User.USERNAME,user.getUsername());
					//response.getBody().put(User.USERNAME,user.getUsername());
					response.setSuccess(true);
					return response;
				}catch (SQLException e) {
					response.setSuccess(false);
					e.printStackTrace();
					return response;
				}
			}
			
		};
		
		public BaseController.BaseHandle queryBoughtGoodsHandle = new BaseHandle() {
			//查询某个用户购买过的商品
			@Override
			public Response work(Request request) {
				
				return null;
			}
		};
};







	

		


