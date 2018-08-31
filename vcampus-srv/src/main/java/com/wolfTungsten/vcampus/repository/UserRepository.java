package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.User;

public class UserRepository extends CurdRepository<User> {

	public UserRepository(ConnectionSource conn) throws SQLException{
		super(conn, User.class);
	}
	
	public void AddUser(String username, String cardnum) throws SQLException {
		User user = new User();
		user.setCardnum(cardnum);
		user.setUsername(username);
		dao.create(user);
	}

}
