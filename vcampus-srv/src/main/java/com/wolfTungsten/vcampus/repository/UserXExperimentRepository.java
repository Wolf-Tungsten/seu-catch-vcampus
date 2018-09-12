package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.UserXExperiment;

public class UserXExperimentRepository	extends CurdRepository<UserXExperiment>
{

	public UserXExperimentRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, UserXExperiment.class);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
