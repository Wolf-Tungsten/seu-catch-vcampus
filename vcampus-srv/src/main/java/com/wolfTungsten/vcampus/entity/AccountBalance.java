package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "AccountBalance")
public class AccountBalance
{
	public static final String UUID = "uuid";
	public static final String SECRETPASSWORD = "secretPassword";
	public static final String USER_ID="user_id";
	@DatabaseField(generatedId = true, columnName = AccountBalance.UUID)
	private UUID uuid;
	@DatabaseField(columnName = AccountBalance.SECRETPASSWORD)
	private String secretPassword;
	@DatabaseField(columnName = AccountBalance.USER_ID)
	private String userid;
	
	public String getUserid()
	{
		return userid;
	}


	public void setUserid(String userid)
	{
		this.userid = userid;
	}


	public AccountBalance() {
		
	}


	public UUID getUuid()
	{
		return uuid;
	}


	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}


	public String getSecretPassword()
	{
		return secretPassword;
	}


	public void setSecretPassword(String secretPassword)
	{
		this.secretPassword = secretPassword;
	}
	
	
}
