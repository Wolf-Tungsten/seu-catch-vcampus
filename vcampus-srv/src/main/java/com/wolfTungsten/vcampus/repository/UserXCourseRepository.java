package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.entity.UserXCourse;

public class UserXCourseRepository extends CurdRepository<UserXCourse>
{

	public UserXCourseRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, UserXCourse.class);
		// TODO Auto-generated constructor stub
	}
	
	public void addUserXCourse(String useruuid, String courseuuid) throws SQLException {
		UserXCourse  uxc = new UserXCourse();
		uxc.setCourse_id(courseuuid);
		uxc.setUser_id(useruuid);
		uxc.setScore(0);
		dao.create(uxc);
		
	}
	//更新 更新成绩吧- -大概也用不到
	public void updateUserXCourse(String uuid ,String column,Object i) throws SQLException {
		dao.update((UserXCourse)dao.updateBuilder()
				.updateColumnValue(column, i)
				.where().eq(UserXCourse.UUID,UUID.fromString(uuid) ).prepare());
	}
	
	public void deleteUserXCourse(String userUuid,String courseUuid) throws SQLException {
		dao.delete((PreparedDelete<UserXCourse>)dao.deleteBuilder()
				.where().eq(UserXCourse.USER_ID, userUuid)
				.and().eq(UserXCourse.COURSE_ID, courseUuid).prepare());
	}
	
	//查
	public ArrayList<HashMap<String, Object>> inquireByFlag(String flag, Object value) throws SQLException{
		
		
		ArrayList<UserXCourse> uxcList = new ArrayList<>();
		uxcList = (ArrayList<UserXCourse>) dao.queryForEq(flag, value);
		ArrayList<HashMap<String, Object>> uxcMaplist = new ArrayList<>();
		for(UserXCourse uxc:uxcList) {
			HashMap<String,Object>recordmap = new HashMap<>();
			recordmap.put(UserXCourse.UUID, uxc.getUuid().toString());
			recordmap.put(UserXCourse.USER_ID, uxc.getUser_id());
			recordmap.put(UserXCourse.COURSE_ID, uxc.getCourse_id());
			recordmap.put(UserXCourse.SCORE, uxc.getScore());
			uxcMaplist.add(recordmap);
		}
		return uxcMaplist ; 
	}
	
	
	
	
	
	
	
}
