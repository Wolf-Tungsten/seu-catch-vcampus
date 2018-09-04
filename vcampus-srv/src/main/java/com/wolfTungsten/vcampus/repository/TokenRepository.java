package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Token;


public class TokenRepository extends CurdRepository<Token>
{
	public static long duration =System.currentTimeMillis()/1000;//30*60; 
	public TokenRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Token.class);

	}
	/**
	 * 检查是否为合法请求和返回boolean
	 * @param token
	 * @param uuid
	 * @param timestamp
	 * @return
	 */
	//未测试
	public String checkToken(String token) throws SQLException{
		List<Token> userList = 
				dao.query((PreparedQuery<Token>) dao.queryBuilder().where().eq(Token.TOKEN, token)
						.prepare());
		
		if(userList.get(0).getToken().equals(token)) {
			if(userList.get(0).getTimestamp()+duration > System.currentTimeMillis()/1000) {
				return userList.get(0).getUserUuid();
			}else {
				return null;
			}
	
		}else return null;	
		
		
	}
	public void addToken(String token , String userUuid , long timestamp) throws SQLException {
		
		Token t = new Token();
		t.setToken(token);
		t.setTimestamp(timestamp);
		t.setUserUuid(userUuid);	
		dao.create(t);
		
	}
}
