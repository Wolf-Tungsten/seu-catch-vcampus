package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "User")
public class User
{

	public static final String UUID = "uuid";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "hash_password";
	public static final String CARDNUM = "cardnum";
	public static final String IDENTITY = "identity";
	public static final String PRIVILEGE = "privilege";
	public static final String PHOTO = "photo";

	@DatabaseField(generatedId = true, columnName = User.UUID)
	private UUID uuid;
	@DatabaseField(columnName = User.USERNAME)
	private String username;
	@DatabaseField(columnName = User.CARDNUM)
	private String cardnum;
	@DatabaseField(columnName = User.PASSWORD)
	private String hash_password;
	@DatabaseField(columnName = User.IDENTITY)
	private int identity;
	@DatabaseField(columnName = User.PRIVILEGE)
	private int privilege;
	@DatabaseField(columnName = User.PHOTO)
	private String photo;

	public User()
	{

	}

	public String getHash_password()
	{
		return hash_password;
	}

	public void setHash_password(String hash_password)
	{
		this.hash_password = hash_password;
	}

	public int getIdentity()
	{
		return identity;
	}

	public void setIdentity(int identity)
	{
		this.identity = identity;
	}

	public int getPrivilege()
	{
		return privilege;
	}

	public void setPrivilege(int privilege)
	{
		this.privilege = privilege;
	}

	public String getPhoto()
	{
		return photo;
	}

	public void setPhoto(String photo)
	{
		this.photo = photo;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public User(String name, String cardnum)
	{
		this.username = name;
		this.cardnum = cardnum;
	}

	public String getCardnum()
	{
		return cardnum;
	}

	public void setCardnum(String cardnum)
	{
		this.cardnum = cardnum;
	}

}