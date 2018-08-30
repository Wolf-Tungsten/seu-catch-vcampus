package com.wolfTungsten.vcampus.repository;

import java.sql.Connection;

public abstract class CurdRepository<E> {
	private Connection conn;
	private String tableName;
	
	public CurdRepository(Connection conn, String tableName) {
		this.conn = conn;
		this.tableName = tableName;
	}
	
	
}
