package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Experiment")
public class Experiment
{
	public static final String UUID = "uuid";
	public static final String NAME = "name";
	public static final String STARTTIME = "startTime";
	public static final String DURATION ="duration";
	public static final String LOCATION ="location";
	public static final String CREATETIME ="createTime";
	public static final String UPDATETIME = "updateTime";
	
	@DatabaseField(generatedId = true, columnName = Experiment.UUID)
	private UUID uuid;
	@DatabaseField(columnName = Experiment.NAME)
	private String name;
	@DatabaseField(columnName = Experiment.STARTTIME)
	private int startTime ;
	@DatabaseField(columnName = Experiment.DURATION)
	private int duration ;
	@DatabaseField(columnName = Experiment.LOCATION)
	private String location;
	@DatabaseField(columnName = Experiment.CREATETIME)
	private int createTime;
	@DatabaseField(columnName = Experiment.UPDATETIME)
	private int updateTime;
	
	public Experiment() {
		
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getStartTime()
	{
		return startTime;
	}

	public void setStartTime(int startTime)
	{
		this.startTime = startTime;
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public int getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(int createTime)
	{
		this.createTime = createTime;
	}

	public int getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(int updateTime)
	{
		this.updateTime = updateTime;
	}
	
	
}
