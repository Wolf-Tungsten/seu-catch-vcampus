package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "TradingRecord")
public class TradingRecord
{
	public static final String UUID = "uuid";
	public static final String FROM = "from";
	public static final String TO = "to";
	public static final String VALUE = "value";
	public static final String CREATETIME = "createTime";
	
	@DatabaseField(generatedId = true, columnName = TradingRecord.UUID)
	private UUID uuid;
	@DatabaseField(columnName = TradingRecord.FROM)
	private String from;
	@DatabaseField(columnName = TradingRecord.TO)
	private String to;
	@DatabaseField(columnName = TradingRecord.VALUE)
	private String value;
	@DatabaseField(columnName = TradingRecord.CREATETIME)
	private int createTime;
	
	public TradingRecord() {
		
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
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
