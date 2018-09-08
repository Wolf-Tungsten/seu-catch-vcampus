package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

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
	
	
}
