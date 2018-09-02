package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Token;


public class TokenRepository extends CurdRepository<Token>
{

	public TokenRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Token.class);

	}
	/**
	 * 检查是否为合法请求和返回上一次操作的时间戳
	 * @param token
	 * @param uuid
	 * @param timestamp
	 * @return
	 */
	public long checkToken(String token,String uuid) throws SQLException{
		List<Token> userList = 
				dao.query((PreparedQuery<Token>) dao.queryBuilder().where().eq(Token.UUID, uuid)
						.prepare());
		
		if(userList.get(0).getToken().equals(token))
			return userList.get(0).getTimestamp();;
		
		
			
		return 0;
		
		
	}
	public void addToken(String token , String userUuid , long timestamp) throws SQLException {
		
		Token t = new Token();
		t.setToken(token);
		t.setTimestamp(timestamp);
		t.setUserUuid(userUuid);	
		dao.create(t);
		
	}
}
