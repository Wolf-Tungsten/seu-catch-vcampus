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
	
	public void addExam(
			String examName, 
			String courseUUID,
			long startTime,
			int duration,
			String location
			) throws SQLException {
		
		Exam newExam = new Exam();
		
		newExam.setName(examName)
		.setCourseUUID(courseUUID)
		.setLocation(location)
		.setStartTime(startTime)
		.setDuration(duration)
		.setUpdateTime(timestamp());
	}
	
	

}
