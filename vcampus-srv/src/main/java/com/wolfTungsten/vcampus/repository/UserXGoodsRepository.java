package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import com.wolfTungsten.vcampus.entity.*;
import com.j256.ormlite.stmt.PreparedQuery;
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
	
	public void addUserXGoods(String user_id,String goods_id,double cost) throws SQLException
	{
		//为购买操作写
		//List<UserXGoods> list = dao.queryForEq(UserXGoods.GOOD_ID,goods_id);
		UserXGoods userXGoods = new UserXGoods();
		userXGoods.setUser_id(user_id);
		userXGoods.setGood_id(goods_id);
		userXGoods.setCost(cost);
		dao.create(userXGoods);
	}
	
	//通过某个用户自己的uuid查询到自己拥有的商品
	public ArrayList<HashMap<String, Object>> inqueryShoppingCart(String userid) throws SQLException
	{
		ArrayList<UserXGoods> userXGoodsList = new ArrayList<>();
		QueryBuilder<UserXGoods,String> queryBuilder= dao.queryBuilder();
		PreparedQuery<UserXGoods> preparedQuery =queryBuilder.where().eq(UserXGoods.IN_CAR, true).and().eq(UserXGoods.USER_ID,userid).prepare();
		userXGoodsList = (ArrayList<UserXGoods>)dao.query(preparedQuery);
		//返回了一个列好的ArrayList<UserXGood>
		ArrayList<HashMap<String,Object>> recordMaplist = new ArrayList<>();
		for(UserXGoods record:userXGoodsList) {
			HashMap<String, Object> recordmap = new HashMap<>();
			recordmap.put(UserXGoods.GOOD_ID, record.getGood_id());
			recordmap.put(UserXGoods.USER_ID,record.getUser_id());
			recordmap.put(UserXGoods.UUID, record.getUuid());
			recordmap.put(UserXGoods.IN_CAR,record.getIn_car());
			recordmap.put(UserXGoods.COST,record.getCost());
			recordMaplist.add(recordmap);
			//goodsList = (ArrayList<Goods>)dao.queryBuilder().where().eq(Goods.UUID, record.GOOD_ID).prepare();
		}
		return recordMaplist;		
	}
	
}
