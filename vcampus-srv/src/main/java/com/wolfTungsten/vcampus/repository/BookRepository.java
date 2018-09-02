package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;

public class BookRepository extends CurdRepository<Book>
{

	public BookRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Book.class);
		
	}

}
