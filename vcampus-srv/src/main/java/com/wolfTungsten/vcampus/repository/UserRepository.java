package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.Where;
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
	
	public UUID login(String cardnum,String password) throws SQLException {
		//根据cardnum和password查询用户
		List<User> userList = 
				dao.query((PreparedQuery<User>) dao.queryBuilder().where().eq(User.CARDNUM, cardnum)
						.eq(User.PASSWORD, password).prepare());
		if(userList.size()!=0)
			return userList.get(0).getUuid();
		else	
			return null;	
		 
		 
		
		
		
	}
	
	

}
