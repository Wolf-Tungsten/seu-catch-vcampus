package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.UUID;

import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.UserXBook;

public class UserXBookRepository extends CurdRepository<UserXBook>
{

	public UserXBookRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, UserXBook.class);
	
	}
	
	public void addUserXBook(String useruuid,String bookuuid,boolean isReturn) throws SQLException {
		UserXBook uxb= new UserXBook();
		uxb.setUser_id(useruuid);
		uxb.setBook_id(bookuuid);
		uxb.setReturn(isReturn);
		dao.create(uxb);
	}
	//update isreturn to true
	public void updateUserXBook(String bookUuid) throws SQLException {
		UserXBook uxb = new UserXBook();
		dao.update((PreparedUpdate<UserXBook>)dao.updateBuilder().updateColumnValue(UserXBook.BOOK_ID, bookUuid)
				.where().eq(UserXBook.ISRETURN, true).prepare());
	}
	
	
}
