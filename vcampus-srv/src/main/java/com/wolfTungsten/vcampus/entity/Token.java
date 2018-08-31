package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Token_uuid")
public class Token
{
	
	public static final String UUID = "uuid";
	public static final String TOKEN = "token";
	public static final String TIMESTAMP ="timestamp";
	public static final String USERUUID = "userUuid";
	
	
	
	@DatabaseField(generatedId = true,columnName = Token.UUID)
	private UUID uuid;
	@DatabaseField(columnName = Token.TOKEN)
	private String token ;
	@DatabaseField(columnName = Token.USERUUID)
	private String userUuid;
	@DatabaseField(columnName = Token.TIMESTAMP)
	private int timestamp;
	public Token() {
		
	}
	public UUID getUuid()
	{
		return uuid;
	}
	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	public String getUserUuid()
	{
		return userUuid;
	}
	public void setUserUuid(String userUuid)
	{
		this.userUuid = userUuid;
	}
	public int getTimestamp()
	{
		return timestamp;
	}
	public void setTimestamp(int createTime)
	{
		this.timestamp = createTime;
	}
	
}
