package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Course")
public class Course
{
	public static final String UUID = "uuid";
	public static final String NAME = "name";
	public static final String CAPCITY = "capacity";
	public static final String LECTURER ="lecturer";
	public static final String CREATETIME ="createTime";
	public static final String UPDATETIME  ="updateTime";
	
	@DatabaseField(generatedId = true, columnName = Course.UUID)
	private UUID uuid;
	@DatabaseField(columnName = Course.NAME)
	private String name;
	@DatabaseField(columnName = Course.CAPCITY)
	private int capacity;
	@DatabaseField(columnName = Course.LECTURER)
	private String lecturer;
	@DatabaseField(columnName = Course.CREATETIME)
	private long createTime;
	@DatabaseField(columnName = Course.UPDATETIME)
	private long updateTime;
	public Course()
	{
		
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
	public int getCapcity()
	{
		return capacity;
	}
	public void setCapcity(int capcity)
	{
		this.capacity = capcity;
	}
	public String getLecturer()
	{
		return lecturer;
	}
	public void setLecturer(String lecturer)
	{
		this.lecturer = lecturer;
	}
	public long getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(long createTime)
	{
		this.createTime = createTime;
	}
	public long getUpdateTime()
	{
		return updateTime;
	}
	public void setUpdateTime(long updateTime)
	{
		this.updateTime = updateTime;
	}
	
	
	
	
	
}
