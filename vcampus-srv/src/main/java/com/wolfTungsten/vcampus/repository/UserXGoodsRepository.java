package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.parser.Entity;

import com.wolfTungsten.vcampus.entity.*;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.UserXGoods;

public class UserXGoodsRepository extends CurdRepository<UserXGoods>
{

	public UserXGoodsRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, UserXGoods.class);
		// TODO Auto-generated constructor stub
	}
	
	//通过某个用户自己的uuid查询到自己拥有的商品
	public ArrayList<HashMap<String, Object>> inqueryShoppingCart(String userid) throws SQLException
	{
		ArrayList<HashMap<String, Object>> shoppingCartList = new ArrayList<>();
		ArrayList<UserXGoods> goodsList = new ArrayList<>();
		QueryBuilder<UserXGoods,String> queryBuilder= dao.queryBuilder();
		//逻辑是用户id相同（理论不应该存在重复
		queryBuilder.where().eq(UserXGoods.IN_CAR, true).and().eq(UserXGoods.GOOD_ID,userid);
		goodsList = (ArrayList<UserXGoods>)queryBuilder.query();
		return null;
				
		//把list写到hashmap里
	}
	
	public void addUXG(String user_id , String good_id ,int amount,double price) {
		UserXGoods uxg = new UserXGoods();
		//uxg.setCost(price);
		//uxg.setAmount(amount);
		uxg.setGood_id(good_id);
		uxg.setUser_id(user_id);
		
	}
	
}
