package com.wolfTungsten.vcampus.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampus.entity.Goods;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;;

public class MarketController extends BaseController {
	
	public MarketController() {
		super();
		this.addHandle("addGoods", addGoodsHandle);
		this.addHandle("sendMessage", sendMessageHandle);
	}
	
	//添加商品的Handle
	private BaseController.BaseHandle addGoodsHandle = new BaseHandle() {
		
		@Override
		public Response work(Request request) {
			Response response = new Response();
			
			String name = (String)request.getParams().get(Goods.NAME);
			String description = (String)request.getParams().get(Goods.DESCRIPTION);
			String seller = (String)request.getParams().get(Goods.SELLER);
			Double price = (Double)request.getParams().get(Goods.PRICE);
			int amount = (int)(double)request.getParams().get(Goods.AMOUNT);
			String image = (String)request.getParams().get(Goods.IMAGE);
			
			//在这里用ormlite的方法给数据库写入数据
			try {
				orm.goodsRepository.addGoods(name, description, seller, price, amount, image);
				response.setSuccess(true);
				return response;
			}catch(SQLException e)
			{	
				e.printStackTrace();
				response.setSuccess(false);
				response.getBody().put("result", "数据库读写出错,"+e.getMessage());
				return response;
			}
			
		}
	};
	
	//
	private BaseController.BaseHandle findByNameHandle = new BaseHandle() {
			@Override
			public Response work(Request request) {
				
				return null;
			}
			
	};
	
		

}
