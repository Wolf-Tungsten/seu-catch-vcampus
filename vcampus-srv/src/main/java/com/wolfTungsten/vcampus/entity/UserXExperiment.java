package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "UserXExperiment")
public class UserXExperiment
{
	public static final String UUID = "uuid";
	public static final String USER_ID = "user_id";
	public static final String EXPERIMENT_ID = "experiment_id";
	
	
	@DatabaseField(generatedId = true, columnName = UserXExperiment.UUID)
	private UUID uuid ;
	@DatabaseField(columnName= UserXExperiment.USER_ID)
	private String user_id;
	@DatabaseField(columnName = UserXExperiment.EXPERIMENT_ID)
	private String experiment_id;
	
	
	public UserXExperiment() {
		
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

	public String getExperiment_id()
	{
		return experiment_id;
	}

	public void setExperiment_id(String experiment_id)
	{
		this.experiment_id = experiment_id;
	}
	
	
}
