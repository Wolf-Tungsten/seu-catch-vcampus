package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ABalanceXTRecord")
public class ABalanceXTRecord
{
	public static final String UUID = "uuid";
	public static final String ACCOUNTBALANCE_ID="accountBalance_id";
	public static final String RECORD_ID = "record_id";
	
	@DatabaseField(generatedId = true, columnName = ABalanceXTRecord.UUID)
	private UUID uuid;
	@DatabaseField(columnName = ABalanceXTRecord.ACCOUNTBALANCE_ID)
	private String accountBalance_id;
	@DatabaseField(columnName = ABalanceXTRecord.RECORD_ID)
	private String record_id;
	
	public ABalanceXTRecord() {
		
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public String getAccountBalance_id()
	{
		return accountBalance_id;
	}

	public void setAccountBalance_id(String accountBalance_id)
	{
		this.accountBalance_id = accountBalance_id;
	}

	public String getRecord_id()
	{
		return record_id;
	}

	public void setRecord_id(String record_id)
	{
		this.record_id = record_id;
	}
	
}
