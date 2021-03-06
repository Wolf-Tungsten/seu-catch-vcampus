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
	public static final String WEEK = "week";
	public static final String CLASSTIME = "classtime";
	public static final String LOCATION = "location";
	public static final String CREDITS ="credits";
	public static final String TYPE ="type";
	
	
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
	@DatabaseField(columnName = Course.WEEK)
	private String week;
	@DatabaseField(columnName = Course.CLASSTIME)
	private String classtime;
	@DatabaseField(columnName = Course.LOCATION)
	private String location;
	@DatabaseField(columnName = Course.CREDITS)
	private int credits;
	
	public Course()
	{
		
	}
	
	

	public int getCredits()
	{
		return credits;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getWeek()
	{
		return week;
	}

	public void setWeek(String week)
	{
		this.week = week;
	}

	public String getClasstime()
	{
		return classtime;
	}

	public void setClasstime(String classtime)
	{
		this.classtime = classtime;
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
