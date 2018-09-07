package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public abstract class CurdRepository<E> {

	protected Dao<E, String> dao;
	
	public CurdRepository(ConnectionSource conn, Class<E> entityClass) throws SQLException {
		dao =  DaoManager.createDao(conn, entityClass);
	}
	
	public long timestamp() {
		return System.currentTimeMillis() / 1000;
	}
		
}
