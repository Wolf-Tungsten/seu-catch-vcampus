package vo.relation;

import java.sql.ResultSet;
import java.util.ArrayList;

import orm.Utils.JdbcUtils;
import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;

//<用户,实验> ->实验助手 实验名单
@Table(name = "tb_UserXExperiment")
public class UserXExperiment
{
	@ID(name = "_id")
	private int _id;
	@Column(name = "User_id")
	private String user_id;
	@Column(name = "Experiment_id")
	private String experiment_id;
	public UserXExperiment() {
		
	}
	
	public UserXExperiment(int _id, String user_id, String experiment_id)
	{
		super();
		this._id = _id;
		this.user_id = user_id;
		this.experiment_id = experiment_id;
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
	public String getExperiment_id()
	{
		return experiment_id;
	}
	public void setExperiment_id(String experiment_id)
	{
		this.experiment_id = experiment_id;
	}
	public int add(UserXExperiment u)
	{	
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(u);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new UserXExperiment(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public ResultSet selectbyId(int id , UserXExperiment u) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, u);
		  
	}
	public ArrayList<UserXExperiment> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new UserXExperiment());
		
	}
	public <E> ArrayList<UserXExperiment> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new UserXExperiment(), columnName, element);
		
	}
}
