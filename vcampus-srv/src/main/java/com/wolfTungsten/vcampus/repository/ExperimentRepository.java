package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Experiment;

public class ExperimentRepository	extends CurdRepository<Experiment>
{

	public ExperimentRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Experiment.class);
	}
	
}
