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
	public static final String CARDNUM = "cardnum";
	public static final String IDCARDNUM = "idcardNum";
	@DatabaseField(generatedId = true, columnName = AccountBalance.UUID)
	private UUID uuid;
	@DatabaseField(columnName = AccountBalance.SECRETPASSWORD)
	private String secretPassword;
	@DatabaseField(columnName = AccountBalance.USER_ID)
	private String userid;
	@DatabaseField(columnName = AccountBalance.CARDNUM)
	private String cardnum;
	@DatabaseField(columnName = AccountBalance.IDCARDNUM)
	private String idcardNum;
	
	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getCardnum()
	{
		return cardnum;
	}

	public void setCardnum(String cardnum)
	{
		this.cardnum = cardnum;
	}

	public String getIdcardNum()
	{
		return idcardNum;
	}

	public void setIdcardNum(String idcardNum)
	{
		this.idcardNum = idcardNum;
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
