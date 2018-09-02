package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.UserXBook;

public class UserXBookRepository extends CurdRepository<UserXBook>
{

	public UserXBookRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, UserXBook.class);
	
	}
	
}
