package com.wolfTungsten.vcampus.repository;


import java.sql.SQLException;
<<<<<<< HEAD
import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

=======
import java.util.List;
>>>>>>> 3250d2baade4a7f891dd780198a0f6d750227c53

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Goods;

public class GoodsRepository extends CurdRepository<Goods>
{

	public GoodsRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Goods.class);
	}
	
	public void addGoods(String name,String description,String seller,double price, int amount,String image) throws SQLException
	{	//这里每次上架的商品都不一样
		//是否要给上架的商品做检验
		Goods goods = new Goods();
		goods.setName(name);
		goods.setAmount(amount);
		goods.setDescription(description);
		goods.setPrice(price);
		goods.setSeller(seller);
		goods.setImage(image);
		dao.create(goods);
	}
	
<<<<<<< HEAD
	public void updateGood(String uuid,int newAmount) throws SQLException{
		List
		newAmount=tradingGood.getAmount()-1;
		tradingGood.setAmount(newAmount);
	}
=======
	//
	public List<Goods> findByName(String name) throws SQLException {
		//
		List<Goods> goodsList = dao.query((PreparedQuery<Goods>)dao.queryBuilder()
				.where().eq(Goods.NAME, name).prepare());
		if(goodsList == null)
		{
			return null;
		}else
			return goodsList;
	}
	
>>>>>>> 3250d2baade4a7f891dd780198a0f6d750227c53
}
