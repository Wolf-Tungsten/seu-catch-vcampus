package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Goods;

public class GoodsRepository extends CurdRepository<Goods>
{

	public GoodsRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Goods.class);
		
	}
	
}
