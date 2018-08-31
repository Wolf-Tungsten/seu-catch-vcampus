package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Token_uuid")
public class Token
{
	
	@DatabaseField(generatedId = true)
	private UUID uuid;
	@DatabaseField
	private String token ;
	@DatabaseField
	private String userUuid;
	@DatabaseField
	private int createTime;
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
	public int getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(int createTime)
	{
		this.createTime = createTime;
	}
	
}
