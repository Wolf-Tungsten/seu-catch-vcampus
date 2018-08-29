package vo.relation;

import java.sql.ResultSet;
import java.util.ArrayList;

import orm.Utils.JdbcUtils;
import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;

//<用户,考试> ->考试助手 考试名单
@Table(name = "tb_UserXExam")
public class UserXExam
{
	@ID(name = "_id")
	private int _id;
	@Column(name = "User_id")
	private String user_id;
	@Column(name = "Exam_id")
	private String exam_id;
	
	public UserXExam() {
		
	}
	
	public UserXExam(int _id, String user_id, String exam_id)
	{
		super();
		this._id = _id;
		this.user_id = user_id;
		this.exam_id = exam_id;
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
	public String getExam_id()
	{
		return exam_id;
	}
	public void setExam_id(String exam_id)
	{
		this.exam_id = exam_id;
	}
	public int add(UserXExam u)
	{	
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(u);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new UserXExam(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public ResultSet selectbyId(int id , UserXExam u) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, u);
		  
	}
	public ArrayList<UserXExam> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new UserXExam());
		
	}
	public <E> ArrayList<UserXExam> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new UserXExam(), columnName, element);
		
	}
	
}
