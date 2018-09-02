package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Course;

public class CourseRepository	extends CurdRepository<Course>
{

	public CourseRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Course.class);
		// TODO Auto-generated constructor stub
	}
	
}
