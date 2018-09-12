package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "Course")
public class Exam
{
	public static final String UUID = "uuid";
	public static final String NAME = "name";
	public static final String COURSE_UUID = "courseUUID";
	public static final String STARTTIME = "startTime";
	public static final String DURATION ="duration";
	public static final String LOCATION ="location";
	public static final String CREATETIME ="createTime";
	public static final String UPDATETIME = "updateTime";
	
	@DatabaseField(generatedId = true, columnName = Exam.UUID)
	private UUID uuid;
	@DatabaseField(columnName = Exam.NAME)
	private String name;
	@DatabaseField(columnName = Exam.STARTTIME)
	private long startTime ;
	@DatabaseField(columnName = Exam.DURATION)
	private String duration ;
	@DatabaseField(columnName = Exam.LOCATION)
	private String location;
	@DatabaseField(columnName = Exam.CREATETIME)
	private long createTime;
	@DatabaseField(columnName = Exam.UPDATETIME)
	private long updateTime;
	@DatabaseField(columnName = Exam.COURSE_UUID)
	private String courseUUID;
	
	public Exam() {
		
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

	public Exam setName(String name)
	{
		this.name = name;
		return this;
	}

	public long getStartTime()
	{
		return startTime;
	}

	public Exam setStartTime(long startTime)
	{
		this.startTime = startTime;
		return this;
	}

	public String getDuration()
	{
		return duration;
	}

	public Exam setDuration(String duration)
	{
		this.duration = duration;
		return this;
	}

	public String getLocation()
	{
		return location;
	}

	public Exam setLocation(String location)
	{
		this.location = location;
		return this;
	}

	public long getCreateTime()
	{
		return createTime;
	}

	public Exam setCreateTime(long createTime)
	{
		this.createTime = createTime;
		return this;
	}

	public long getUpdateTime()
	{
		return updateTime;
	}

	public Exam setUpdateTime(long updateTime)
	{
		this.updateTime = updateTime;
		return this;
	}

	public String getCourseUUID() {
		return courseUUID;
	}

	public Exam setCourseUUID(String courseUUID) {
		this.courseUUID = courseUUID;
		return this;
	}
	
	
	
	
}
