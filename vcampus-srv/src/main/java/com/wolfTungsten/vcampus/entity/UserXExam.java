package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "UserXExam")
public class UserXExam
{
	public static final String UUID = "uuid";
	public static final String USER_ID = "user_id";
	public static final String EXAM_ID = "exam_id";
	
	@DatabaseField(generatedId = true, columnName = UserXExam.UUID)
	private UUID uuid ;
	@DatabaseField(columnName= UserXExam.USER_ID)
	private String user_id;
	@DatabaseField(columnName = UserXExam.EXAM_ID)
	private String exam_id;
	
	public UserXExam() {
		
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

	public String getExam_id()
	{
		return exam_id;
	}

	public void setExam_id(String exam_id)
	{
		this.exam_id = exam_id;
	}
	
	
}
