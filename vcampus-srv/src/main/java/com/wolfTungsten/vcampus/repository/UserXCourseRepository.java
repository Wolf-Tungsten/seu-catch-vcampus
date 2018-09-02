package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.UserXCourse;

public class UserXCourseRepository extends CurdRepository<UserXCourse>
{

	public UserXCourseRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, UserXCourse.class);
		// TODO Auto-generated constructor stub
	}
	
}
