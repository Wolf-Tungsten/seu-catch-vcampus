package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.google.gson.internal.LinkedTreeMap;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.Course;
import com.wolfTungsten.vcampus.entity.User;


public class CourseRepository	extends CurdRepository<Course>
{
	
	public CourseRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Course.class);
		// TODO Auto-generated constructor stub
	}
	public void  addCourse(String name , int capacity,String lecturer) throws SQLException {

		Course course = new Course();
		course.setCapcity(capacity);
		course.setLecturer(lecturer);
		course.setName(name);
		long time = System.currentTimeMillis()/1000 ;
		course.setCreateTime(time);
		course.setUpdateTime(time);
		dao.create(course);	
	}
	/**
	 * 返回课程信息
	 * @return ArrayList<HashMap<String,Object>>
	 * @throws SQLException
	 */
	public ArrayList<HashMap<String,Object>> queryAllCourses() throws SQLException {
		ArrayList<HashMap<String,Object>>  courseMapList = new ArrayList<>();


		ArrayList<Course> courseList = new ArrayList<>();
		courseList = (ArrayList<Course>) dao.queryForAll();
		for(Course course : courseList) {
			HashMap<String,Object> courseinfo = new HashMap<>();
			courseinfo.put(Course.NAME, course.getName());
			courseinfo.put(Course.LECTURER, course.getLecturer());
			courseinfo.put(Course.CAPCITY, course.getCapcity());
			courseinfo.put(Course.UPDATETIME, course.getUpdateTime());
			courseinfo.put(Course.CREATETIME,course.getCreateTime());		
			courseinfo.put(Course.UUID,course.getUuid().toString());
			courseMapList.add(courseinfo);
		}
		return courseMapList;
	}
	
	public void deleteCourse(String uuid) throws SQLException {
		UUID courseuuid =UUID.fromString(uuid);
			dao.delete((PreparedDelete<Course>)dao.deleteBuilder()
				.where().eq(Course.UUID, courseuuid).prepare());
	}
	
	public void updateBook(LinkedTreeMap<String,Object> courseinfo) throws SQLException {
		UpdateBuilder<Course, String> updateBuilder = dao.updateBuilder();
		String bookUuid = (String)courseinfo.get("uuid");
		updateBuilder.where().eq("uuid", bookUuid);
		courseinfo.remove("uuid");
		for(String columnName:courseinfo.keySet())
		{
			dao.update((PreparedUpdate<Course>)dao.updateBuilder()
					.updateColumnValue(columnName, courseinfo.get(columnName)).where()
					.eq(Course.UUID, UUID.fromString(bookUuid)).prepare());
			updateBuilder.updateColumnValue(columnName, courseinfo.get(columnName));
		}

	}
	public Course inquireById(String uuid) throws SQLException {
		Book book = new Book();
		List<Course> books = dao.queryForEq(Course.UUID, UUID.fromString(uuid));	
		return books.get(0);
		
	}
	//根据老师和课程
		public ArrayList<Course> inquireByFlags(String teacher,String courseName) throws SQLException{
			ArrayList<Course> courselist = (ArrayList<Course>) dao.query((PreparedQuery<Course>)dao.queryBuilder()
					.where().eq(Course.LECTURER, teacher)
					.eq(Course.NAME, courseName).prepare());

			return courselist;
			
		}
	
	
	
}
