package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.text.html.parser.Entity;

import com.wolfTungsten.vcampus.entity.*;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;

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
		PreparedQuery<UserXGoods> preparedQuery =queryBuilder.where().eq(UserXGoods.IN_CAR, 1).and().eq(UserXGoods.USER_ID,userid).prepare();
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
			recordmap.put(UserXGoods.CREATETIME, record.getCreatetime());
			recordMaplist.add(recordmap);
			//goodsList = (ArrayList<Goods>)dao.queryBuilder().where().eq(Goods.UUID, record.GOOD_ID).prepare();
		}
		return recordMaplist;		
	}
	
	public ArrayList<UserXGoods> inqueryShopCart(String userid) throws SQLException {
		ArrayList<UserXGoods> userXGoodsList = new ArrayList<>();
		QueryBuilder<UserXGoods,String> queryBuilder= dao.queryBuilder();
		PreparedQuery<UserXGoods> preparedQuery =queryBuilder.where().eq(UserXGoods.IN_CAR, 1).and().eq(UserXGoods.USER_ID,userid)
				.and().eq(UserXGoods.WHETHERBUY, 0).prepare();
		userXGoodsList = (ArrayList<UserXGoods>)dao.query(preparedQuery);
		return userXGoodsList;
	}
	
	//根据flag 返回关系集
	public ArrayList<UserXGoods> inqueryByFlag(String uuid ,String column,Object value) throws SQLException{
		ArrayList<UserXGoods> uxglist = new ArrayList<>();
		QueryBuilder<UserXGoods,String> queryBuilder = dao.queryBuilder();
		PreparedQuery<UserXGoods> preparedQuery = queryBuilder.where().eq(UserXGoods.USER_ID,UUID.fromString(uuid))
				.and().eq(column, value).prepare();
		uxglist = (ArrayList<UserXGoods>) dao.query(preparedQuery);
		return uxglist;
	}
	
	
	
	public void addUXG(String user_id , String good_id ,int amount,double price,int in_cart,int whetherbuy,long createtime) throws SQLException {
		UserXGoods uxg = new UserXGoods();
		uxg.setCost(price);
		uxg.setAmount(amount);
		uxg.setGood_id(good_id);
		uxg.setUser_id(user_id);
		uxg.setIn_car(in_cart);
		uxg.setWhetherbuy(whetherbuy);
		uxg.setCreatetime(createtime);
		dao.create(uxg);
		
	}
	
	public void updateUXGbyFlag(String uxguuid,String column,Object value) throws SQLException {
		UpdateBuilder< UserXGoods, String>upd = dao.updateBuilder();
		upd.where().eq(UserXGoods.UUID, UUID.fromString(uxguuid));
		upd.updateColumnValue(column, value);
		upd.update();
		
	}
	
	public UserXGoods queryOne (String uuid) throws SQLException {
		List<UserXGoods> uxg = dao.queryForEq(UserXGoods.UUID, UUID.fromString(uuid));
		if(uxg.size()==0)throw new SQLException("发生了不知名的错误");
		return uxg.get(0);
	}
	public void deleteById(String uuid) throws SQLException {
		DeleteBuilder<UserXGoods, String>dlb = dao.deleteBuilder();
		dlb.where().eq(UserXGoods.UUID, UUID.fromString(uuid));
		dlb.delete();
		
	}
	
}
