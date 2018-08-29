package vo.relation;

import java.sql.ResultSet;
import java.util.ArrayList;

import orm.Utils.JdbcUtils;
import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;

//<用户,课程> -> 课程表 花名册 成绩
@Table(name = "tb_UserXCourse")
public class UserXCourse
{
	@ID(name = "_id")
	int _id;
	@Column(name = "User_id")
	String user_id;
	@Column(name = "Course_id")
	String course_id;
	@Column(name = "Score")
	int score;
	public UserXCourse() {
		
	}
	public UserXCourse(int _id, String user_id, String course_id, int score)
	{
		super();
		this._id = _id;
		this.user_id = user_id;
		this.course_id = course_id;
		this.score = score;
	}
	public int get_id()
	{
		return _id;
	}
	public void set_id(int _id)
	{
		this._id = _id;
	}
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	public String getCourse_id()
	{
		return course_id;
	}
	public void setCourse_id(String course_id)
	{
		this.course_id = course_id;
	}
	public int getScore()
	{
		return score;
	}
	public void setScore(int score) {
		this.score =score ;
	}
	public int add(UserXCourse u)
	{	
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(u);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new UserXCourse(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public ResultSet selectbyId(int id , UserXCourse u) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, u);
		  
	}
	public ArrayList<UserXCourse> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new UserXCourse());
		
	}
	public <E> ArrayList<UserXCourse> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new UserXCourse(), columnName, element);
		
	}
}
