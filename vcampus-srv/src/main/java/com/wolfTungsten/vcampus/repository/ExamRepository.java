package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Exam;


public class ExamRepository extends CurdRepository<Exam>
{

	public ExamRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Exam.class);
		
	}

}
