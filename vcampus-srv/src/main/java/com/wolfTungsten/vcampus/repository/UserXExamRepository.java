package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.UserXExam;

public class UserXExamRepository extends CurdRepository<UserXExam>
{

	public UserXExamRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, UserXExam.class);
		// TODO Auto-generated constructor stub
	}
	
}
