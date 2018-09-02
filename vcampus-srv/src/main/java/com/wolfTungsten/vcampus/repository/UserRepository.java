package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.stmt.PreparedQuery;
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
	public void addUser(String username,String cardnum,String hash_password,int identity,
			int privilege ,String photo,String idcardNum,long birthdate,String address) throws SQLException {
		
		List<User> userList = dao.query((PreparedQuery<User>)dao.queryBuilder()
				.where().eq(User.CARDNUM, cardnum).prepare());
		if(userList.size()==0) {
		User user = new User();
		user.setUsername(username);
		user.setCardnum(cardnum);
		user.setHash_password(hash_password);
		user.setIdentity(identity);
		user.setPrivilege(privilege);
		user.setPhoto(photo);
		user.setAddress(address);
		user.setBirthdate(birthdate);
		user.setIdcardNum(idcardNum);
		dao.create(user);
		}else
		{
			throw new SQLException("该卡号已注册");
		}
		
		
	}
	
	public UUID login(String cardnum,String password) throws SQLException {
		//根据cardnum和password查询用户
		List<User> userList = 
				dao.query((PreparedQuery<User>) dao.queryBuilder().where().eq(User.CARDNUM, cardnum).and()
						.eq(User.PASSWORD, password).prepare());
		if(userList.size()!=0) {
			System.out.println("userlist size = 0");
			return userList.get(0).getUuid();
		}
		else	
			return null;	
		 
		 
		
		
		
	}
	
	

}
