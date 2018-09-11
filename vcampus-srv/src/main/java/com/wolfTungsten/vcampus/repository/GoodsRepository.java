package com.wolfTungsten.vcampus.repository;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.UUID;

//import org.mockito.internal.matchers.And;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

import com.google.gson.internal.LinkedTreeMap;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.Goods;

public class GoodsRepository extends CurdRepository<Goods>
{

	public GoodsRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Goods.class);
	}
	
	public void addGoods(String name,String description,String seller,double price, int amount,String image) throws SQLException
	{	
		//这里每次上架的商品都不一样
		//是否要给上架的商品做检验
		Goods goods = new Goods();
		goods.setName(name);
		goods.setAmount(amount);
		goods.setDescription(description);
		goods.setPrice(price);
		goods.setSeller(seller);
		goods.setImage(image);
		goods.setSold(false);
		dao.create(goods);
	}
	
	public ArrayList<HashMap<String, Object>> inquireAllGoods() throws SQLException 
	{
		//新建名为goodlist的Goods类型的list和HashMap的list
		ArrayList<Goods> goodslist = new ArrayList<>();
		ArrayList<HashMap<String, Object>> goodsinfolist = new ArrayList<>();
		//这里会包括历史上所有卖掉的商品和仍然在市场里的商品
		goodslist = (ArrayList<Goods>)dao.queryForEq(Goods.SOLD, false);
		for(Goods goods:goodslist) {
			HashMap<String, Object>goodsinfo = new HashMap<>();
			goodsinfo.put(Goods.UUID,goods.getUuid().toString());
			goodsinfo.put(Goods.AMOUNT,goods.getAmount());
			goodsinfo.put(Goods.DESCRIPTION, goods.getDescription());
			goodsinfo.put(Goods.IMAGE,goods.getImage());
			goodsinfo.put(Goods.NAME,goods.getName());
			goodsinfo.put(Goods.PRICE, goods.getPrice());
			goodsinfo.put(Goods.SELLER,goods.getSeller());
			goodsinfolist.add(goodsinfo);
		}
		return goodsinfolist;
		
	}

	
	//
	public ArrayList<HashMap<String,Object>> inquireByFlag(String flag,Object value) throws SQLException {
		ArrayList<Goods> goodslist = new ArrayList<>();
		ArrayList<HashMap<String, Object>> goodsinfolist = new ArrayList<>();
		goodslist = (ArrayList<Goods>)dao.queryForEq(flag, value);
		for(Goods goods:goodslist) {
			HashMap<String, Object>goodsinfo = new HashMap<>();
			goodsinfo.put(Goods.UUID,goods.getUuid().toString());
			goodsinfo.put(Goods.AMOUNT,goods.getAmount());
			goodsinfo.put(Goods.DESCRIPTION, goods.getDescription());
			goodsinfo.put(Goods.IMAGE,goods.getImage());
			goodsinfo.put(Goods.NAME,goods.getName());
			goodsinfo.put(Goods.PRICE, goods.getPrice());
			goodsinfo.put(Goods.SELLER,goods.getSeller());
			goodsinfolist.add(goodsinfo);
		}
		return goodsinfolist;
	}//end
	
	private void updateGoods(HashMap<String, Object> goodsinfo) throws SQLException {
		//修改商品信息，通过传一个HashMap
		UpdateBuilder<Goods, String> updateBuilder = dao.updateBuilder();
		String goodsUuid = (String)goodsinfo.get(Goods.UUID);
		updateBuilder.where().eq("uuid", goodsUuid);
	}
	
	//更改商品的信息？
	private void updateGoods(LinkedTreeMap<String, Object> goodsinfo) throws SQLException {
		
		UpdateBuilder<Goods, String> updateBuilder = dao.updateBuilder();
		String goodsUuid = (String)goodsinfo.get("uuid");
		updateBuilder.where().eq("uuid", goodsUuid);
		goodsinfo.remove("uuid");
		for(String columnName:goodsinfo.keySet())
		{
			dao.update((PreparedUpdate<Goods>)dao.updateBuilder()
					.updateColumnValue(columnName, goodsinfo.get(columnName)).where()
					.eq(Goods.UUID, UUID.fromString(goodsUuid)).prepare());
			updateBuilder.updateColumnValue(columnName, goodsinfo.get(columnName));
		}
	}
	
	//那么前端怎么传给我这个uuid呢(用户登录后获取他的uuid）
	public void deleteGoodsByUuid(String uuid) throws SQLException {
		UUID goodsUuid = UUID.fromString(uuid);
		dao.delete((PreparedDelete<Goods>)dao.deleteBuilder()
				.where().eq(Goods.UUID, goodsUuid).prepare());
	}
	
	public void deleteAllGoods() throws SQLException
	{
		//新建一个deletebuilder
		//删除掉所有的uuid不是空的行
		DeleteBuilder<Goods, String> deleteBuilder= dao.deleteBuilder();
		deleteBuilder.where().isNotNull(Goods.UUID);
		deleteBuilder.delete();
	}
	
	public void updateGoods2(LinkedTreeMap<String, Object> goodsinfo) throws SQLException {
		UpdateBuilder<Goods, String> updateBuilder = dao.updateBuilder();
		String goodsUuid = (String)goodsinfo.get("uuid");
		updateBuilder.where().eq("uuid", goodsUuid);
		goodsinfo.remove("uuid");
		for(String columnName:goodsinfo.keySet())
		{		
			updateBuilder.updateColumnValue(columnName, goodsinfo.get(columnName)).update();
		}
		
	}
	
	
};
	
	
	
	

