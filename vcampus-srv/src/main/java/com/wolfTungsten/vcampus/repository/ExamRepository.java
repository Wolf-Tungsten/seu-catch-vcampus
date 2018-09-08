package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.google.gson.internal.LinkedTreeMap;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.Exam;
import com.wolfTungsten.vcampus.entity.Experiment;


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
		dao.create(newExam);
	}
	//删除
	public void deleteExam(String examUuid) throws SQLException {
		UUID examuuid =UUID.fromString(examUuid);
		dao.delete((PreparedDelete<Exam>)dao.deleteBuilder()
				.where().eq(Exam.UUID, UUID.fromString(examUuid)).prepare());

	}
	public ArrayList<HashMap<String,Object>>queryByFlag(String columnname,Object value) throws SQLException{
		ArrayList<Exam> examList = new ArrayList<>();
		ArrayList<HashMap<String,Object>> examinfoList = new ArrayList<>();
		examList = ((ArrayList<Exam>)dao.queryForEq(columnname, value));//**
		for(Exam exam : examList) {
			HashMap<String,Object>examinfo = new HashMap<>();
			examinfo.put(Exam.UUID, exam.getUuid().toString());
			examinfo.put(Exam.LOCATION, exam.getLocation());
			examinfo.put(Exam.NAME,exam.getName());
			examinfo.put(Exam.STARTTIME, exam.getStartTime());
			examinfo.put(Exam.DURATION, exam.getDuration());
			examinfoList.add(examinfo);
		}
		return examinfoList;
	}
	
	public ArrayList<Exam> queryByCourseUUID(String uuid) throws SQLException{
		UUID euuid = UUID.fromString(uuid);
		ArrayList<Exam> list = (ArrayList<Exam>) dao.queryForEq(Exam.COURSE_UUID, euuid);
		return list;			
	}
	
	
//	public void updateBook(LinkedTreeMap<String,Object> booksinfo) throws SQLException {
//		UpdateBuilder<Book,String> updateBuilder = dao.updateBuilder();
//		String bookUuid = (String)booksinfo.get("uuid");
//		updateBuilder.where().eq("uuid", bookUuid);
//		booksinfo.remove("uuid");
//		for(String columnName:booksinfo.keySet())
//		{
//			dao.update((PreparedUpdate<Book>)dao.updateBuilder()
//					.updateColumnValue(columnName, booksinfo.get(columnName)).where()
//					.eq(Book.UUID, UUID.fromString(bookUuid)).prepare());
//			updateBuilder.updateColumnValue(columnName, booksinfo.get(columnName));
//		}
//
//	}

}
