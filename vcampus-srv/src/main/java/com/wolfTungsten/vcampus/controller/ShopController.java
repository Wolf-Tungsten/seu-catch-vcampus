/**
 * Classname ：ShopController.java
 * 
 * Date:2018/9/14
 * 
 * @author 袁皓东
 */
package com.wolfTungsten.vcampus.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampus.entity.AccountBalance;
import com.wolfTungsten.vcampus.entity.Goods;
import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.entity.UserXGoods;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

public class ShopController extends BaseController
{
	public ShopController () {
		super();
		this.pathMap.put("addGoods", addGoodsHandle);
		this.pathMap.put("deleteGoods", deleteGoodsHandle);
		this.pathMap.put("updateGoods", updateGoodsHandle);
		this.pathMap.put("queryByFlag", queryByFlagHandle);
		this.pathMap.put("purchaseNow", purchaseNowHandle);
		this.pathMap.put("purchaseByCart", purchaseBycart);
		this.pathMap.put("addCart", addCartHandle);
		this.pathMap.put("queryCart", inqueryCart);
		this.pathMap.put("buyRecord",buyRecordHandle);
		this.pathMap.put("queryBySel", queryGoodsByselHandle);
		this.pathMap.put("cartremove", cartremoveHandle);
		this.pathMap.put("updateGood", updateGoodHandle);
	}
	
	
	//ok
	private BaseController.BaseHandle addGoodsHandle = new BaseHandle()
	{
		/**
		 * 新增商品
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();			
			String name = (String)request.getParams().get(Goods.NAME);
			String description = (String)request.getParams().get(Goods.DESCRIPTION);
			
			Double price = (Double)request.getParams().get(Goods.PRICE);
			int amount = (int)(double)request.getParams().get(Goods.AMOUNT);
			String image = (String)request.getParams().get(Goods.IMAGE);
			String type = (String) request.getParams().get(Goods.TYPE);//后面加
			String token = request.getToken();		
			try
			{
				String userid = checkToken(token);
				User u = orm.userRepository.inquireById(userid);
				String seller = u.getUsername();
				orm.goodsRepository.addGoods(name, description, seller, price, amount, image,type,0);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
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
		/**
		 * 从前端接受一个删除商品的uuid列表，删除商品
		 */
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
				orm.goodsRepository.updateGoodsByfalg(uuid, Goods.DELETE, 1);
				
				
			}
			response.setSuccess(true);
			return response;
			}catch(SQLException e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		
		}
	};
	private BaseController.BaseHandle updateGoodHandle = new BaseHandle() {
		/**
		 * 更新商品信息
		 */
		@Override
		public Response work(Request request) {
			Response response = new Response();
			String token = request.getToken();
			String good_uuid = (String) request.getParams().get(Goods.UUID);
			String name =(String)request.getParams().get(Goods.NAME);
			int amount =(int)(double)request.getParams().get(Goods.AMOUNT);
			String description=(String) request.getParams().get(Goods.DESCRIPTION);
			double price = (double)request.getParams().get(Goods.PRICE);
			LinkedTreeMap<String,Object> ltm = new LinkedTreeMap<>();
			ltm.put(Goods.UUID, good_uuid);
			ltm.put(Goods.NAME, name);
			ltm.put(Goods.PRICE, price);
			ltm.put(Goods.AMOUNT, amount);
			ltm.put(Goods.DESCRIPTION, description);
			try {
				checkToken(token);
				orm.goodsRepository.updateGoods2(ltm);
				response.setSuccess(true);
				return response;
			} catch (SQLException e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
			
			
		}
	};
	
	private BaseController.BaseHandle updateGoodsHandle = new BaseHandle()
	{
		/**
		 * 更新商品信息
		 */
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
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
			
			
		}
	};
	//按type搜索返回商品
	//未测试 
	private BaseController.BaseHandle queryByFlagHandle = new BaseHandle()
	{
		/**
		 * 按flag查询商品
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String flag = (String) request.getParams().get(Goods.TYPE);//后面加type
		
			 try
			{
				 checkToken(token);
				ArrayList<HashMap<String,Object>> goodsinfomaplist = orm.goodsRepository.inquireByFlag(Goods.TYPE, flag);
				response.setSuccess(true);
				response.getBody().put("Goodinfomaplist", goodsinfomaplist);
				return response;
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}	
		}
	};
	private BaseController.BaseHandle queryGoodsByselHandle = new BaseHandle()
	{
		/**
		 * 按选中查询商品
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String name = (String) request.getParams().get(Goods.NAME);
			
			try
			{
				checkToken(token);
				ArrayList<HashMap<String,Object>> goodsinfomaplist = 
						orm.goodsRepository.inquireByName(Goods.NAME, name);
				response.getBody().put("goodsinfomaplist", goodsinfomaplist);
				response.setSuccess(true);
				return response;
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				e.printStackTrace();
				response.getBody().put("result", e.getMessage());
				return response;
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
			
		}
	};
	//立即购买
	//注册个卡号为000000为商店管理员
	//待改
	private BaseController.BaseHandle purchaseNowHandle = new BaseHandle()
	{
		/**
		 * 立即购买
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String good_uuid = (String) request.getParams().get(Goods.UUID);
			int amount = (int) (double)request.getParams().get(Goods.AMOUNT);
			double price = (double)request.getParams().get(Goods.PRICE);
			String buyPwd = (String) request.getParams().get(AccountBalance.SECRETPASSWORD);
			try
			{
				String useruuid =checkToken(token); //用户
				if(orm.accountBalanceRepository.check(useruuid, buyPwd)) {
				
				User shopuser = orm.userRepository.inquireByCardnum("000000");//商店管理员
				String shopuseruuid = shopuser.getUuid().toString();
				
				
				Goods good = orm.goodsRepository.inquireById(good_uuid);
				if(good.getAmount()-amount<0)throw new SQLException("库存不足，请减少购买数量");
				orm.userXGoodsRepository.addUXG(useruuid, good_uuid, amount
						, price, 0,1,System.currentTimeMillis()/1000);
				
				orm.tradingRecordRepository.addTradingRecord(useruuid, shopuseruuid,
						price*amount*100, System.currentTimeMillis()/1000);
				orm.goodsRepository.updateGoodsByfalg(good_uuid, Goods.AMOUNT, good.getAmount()-amount);
				response.setSuccess(true);
				return response;
				}else {
					response.setSuccess(false);
					response.getBody().put("result", "支付密码错误");
					return response;
				}
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result",e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}			
		}
	};
	//购物车总购买
	//传商品购买信息ArrayList<LinkedTreeMap<String,Object>>
	private BaseController.BaseHandle purchaseBycart = new BaseHandle()
	{
		/**
		 * 购物车总购买
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			ArrayList<LinkedTreeMap<String,Object>> goodsinfomaplist = 
					(ArrayList<LinkedTreeMap<String, Object>>) request.getParams().get("Goodinfomaplist");
			String buyPwd = (String) request.getParams().get(AccountBalance.SECRETPASSWORD);
			try
			{
				String userid = checkToken(token);
				if(orm.accountBalanceRepository.check(userid, buyPwd)) {
			
				User shopuser = orm.userRepository.inquireByCardnum("000000");
				for(LinkedTreeMap<String,Object> goodinfo:goodsinfomaplist) {
					String shop_userid = shopuser.getUuid().toString();
					
					String uxguuid = (String) goodinfo.get(UserXGoods.UUID);
					UserXGoods uxg = orm.userXGoodsRepository.queryOne(uxguuid);
					
					
					String gooduuid = uxg.getGood_id();	
					
					int amount =(int)(double) goodinfo.get(UserXGoods.AMOUNT);
					double price = (double) goodinfo.get(UserXGoods.COST);
					Goods good = orm.goodsRepository.inquireById(gooduuid);
					if(good.getAmount()-amount<0)throw new SQLException("库存不足，请减少购买数量");
					orm.userXGoodsRepository.updateUXGbyFlag(uxguuid, UserXGoods.WHETHERBUY, 1);
					orm.userXGoodsRepository.updateUXGbyFlag(uxguuid, UserXGoods.CREATETIME, System.currentTimeMillis()/1000);				
					
					
					orm.goodsRepository.updateGoodsByfalg(gooduuid, Goods.AMOUNT, good.getAmount()-amount);
					orm.tradingRecordRepository.addTradingRecord(userid,
							shop_userid, price*amount*100,System.currentTimeMillis()/1000 );
					
				}
				response.setSuccess(true);
				return response;
				}else {
					response.setSuccess(false);
					response.getBody().put("result", "支付密码错误");
					return response;
				}
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());				
				e.printStackTrace();
				return response;
				
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		
		}
	};
	
	//添加购物车
	//前端传token 商品uuid  
	private BaseController.BaseHandle addCartHandle = new BaseHandle()
	{
		/**
		 * 添加购物车
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String gooduuid = (String) request.getParams().get(UserXGoods.GOOD_ID);
			int amount =(int)(double) request.getParams().get(UserXGoods.AMOUNT);
			double cost = (double)request.getParams().get(UserXGoods.COST);
			
			try
			{
				String useruuid = checkToken(token);
				orm.userXGoodsRepository.addUXG(useruuid, gooduuid, amount,
						cost, 1, 0,System.currentTimeMillis()/1000);
				response.setSuccess(true);
				return response;
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
			
		}
	};
	
	//查看购物车
	private BaseController.BaseHandle inqueryCart = new BaseHandle()
	{
		/**
		 * 查询购物车内容
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			
			try
			{
				String userid = checkToken(token);
				ArrayList<HashMap<String,Object>> cartinfolist = new ArrayList<>();
				ArrayList<UserXGoods> uxglist = orm.userXGoodsRepository.inqueryShopCart(userid);
				for(int i=0;i<uxglist.size();i++) {
					HashMap<String,Object> cartinfo = new HashMap<>();
					UserXGoods uxg = uxglist.get(i);
					Goods good = orm.goodsRepository.inquireById(uxg.getGood_id());
					String goodname = good.getName();
					int amount = uxg.getAmount();
					double price = good.getPrice();
					String uxguuid = uxg.getUuid().toString();
					String image =good.getImage();
					
					cartinfo.put(UserXGoods.UUID, uxguuid);
					cartinfo.put(UserXGoods.AMOUNT, amount);
					cartinfo.put(Goods.PRICE, price);
					cartinfo.put(Goods.NAME, goodname);			
					cartinfo.put(Goods.IMAGE, image);
					cartinfolist.add(cartinfo);					
				}
				response.setSuccess(true);
				response.getBody().put("cartinfomaplist", cartinfolist);
				return response;
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
			
		
		}
	};
	
	
	//查看购买记录
	private BaseController.BaseHandle buyRecordHandle = new BaseHandle()
	{
		/**
		 * 查看购买记录
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();		
			String token = request.getToken();
			try
			{
				String userid = checkToken(token);
				User user = orm.userRepository.inquireById(userid);
				ArrayList<UserXGoods> uxglist = 
						orm.userXGoodsRepository.inqueryByFlag(userid, UserXGoods.WHETHERBUY, 1);
				ArrayList<HashMap<String,Object>> buyRecord = new ArrayList<>();
				
				for(int i=0;i<uxglist.size();i++) {
					HashMap<String,Object> br = new HashMap<>();
					UserXGoods uxg = uxglist.get(i);
					Goods good = orm.goodsRepository.inquireById(uxg.getGood_id().toString());
					int amount = uxg.getAmount();
					double cost = uxg.getCost()*amount;
					long createtime = uxg.getCreatetime();
					br.put(UserXGoods.UUID, uxg.getUuid().toString());
					br.put(UserXGoods.AMOUNT, amount);
					br.put(UserXGoods.COST, cost);
					br.put(UserXGoods.CREATETIME, createtime);
					br.put(Goods.NAME, good.getName());
					buyRecord.add(br);			
					
				}
				response.setSuccess(true);
				response.getBody().put("buyRecordmaplist", buyRecord);
				response.getBody().put(User.USERNAME, user.getUsername());
				response.getBody().put(User.CARDNUM, user.getCardnum());
				return response;			
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		
		}
	};
	
	private BaseController.BaseHandle cartremoveHandle = new BaseHandle() {
		/**
		 * 删除购物车商品
		 */
		@Override
		public Response work(Request request) {
			Response response = new Response();
			String token = request.getToken();
			ArrayList<String> goodidlist = 
					(ArrayList<String>) request.getParams().get("deletelist");//待删商品uuid列表
			try {
				checkToken(token);
			for(String uuid:goodidlist) {
				orm.userXGoodsRepository.deleteById(uuid);
				
			}
			response.setSuccess(true);
			return response;
			}catch(SQLException e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
			
		}
	};
	
	//按升序返回 
	
	//按降序返回
	
	
}
