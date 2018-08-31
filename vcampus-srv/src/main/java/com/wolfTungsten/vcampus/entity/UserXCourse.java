package com.wolfTungsten.vcampus.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.UUID;
@DatabaseTable(tableName = "UserXCourse")
public class UserXCourse
{
	public static final String UUID = "uuid";
	public static final String USER_ID = "user_id";
	public static final String COURSE_ID = "course_id";
	public static final String SCORE = "score";
	
	@DatabaseField(generatedId = true, columnName = UserXCourse.UUID)
	private UUID uuid ;
	@DatabaseField(columnName= UserXCourse.USER_ID)
	private String user_id;
	@DatabaseField(columnName= UserXCourse.COURSE_ID)
	private String course_id;
	@DatabaseField(columnName= UserXCourse.SCORE)
	private int score;
	
	public UserXCourse() {
		
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getCourse_id()
	{
		return course_id;
	}

	public void setCourse_id(String course_id)
	{
		this.course_id = course_id;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}
	
}
