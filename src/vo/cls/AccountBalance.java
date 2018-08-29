package vo.cls;
import java.sql.*;
import java.util.ArrayList;

import orm.annotation.*;
import orm.Utils.*;

@Table(name = "tb_AccountBalance")
public class AccountBalance 
{
	@ID(name = "_id")
	private int _id;
	@Column(name = "SecretPassword")
	private String secretPassword;
	
	//getter and setter

	public AccountBalance(int _id, String secretPassword)
	{
		super();
		this._id = _id;
		this.secretPassword = secretPassword;
	}
	public AccountBalance()
	{
		// TODO Auto-generated constructor stub
	}
	public int get_id()
	{
		return _id;
	}
	public void set_id(int _id)
	{
		this._id = _id;
	}
	public String getSecretPassword()
	{
		return secretPassword;
	}
	public void setSecretPassword(String secretPassword)
	{
		this.secretPassword = secretPassword;
	}
	/**
	 * 添加一个余额账户
	 * 返回值为Int >0=成功 <0 = 不成功 可用作判断
	 * @param accountbbalance
	 * @return
	 */
	public int add(AccountBalance accountbbalance) {
		

		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(accountbbalance);
		return result;
	}
	
	//根据账户余额实体id  更新信息
	/**
	 * 根据id更新余额账户信息
	 * @param _id
	 * @return
	 */
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new AccountBalance(),Flag,value );
		return result< 0 ?false:true;
		
	}
	/**
	 * 根据id搜索余额账户实体  并返回实体数据
	 * @param id
	 * @param a
	 * @return ResultSet 
	 */
	public  ResultSet selectbyId(int id , AccountBalance a) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, a);
		  
	}
	/**
	 * 获取余额账户数据表
	 * @return ResultSet 接口(相当于临时表)
	 */
	public  ArrayList<AccountBalance> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new AccountBalance());
		
	}
	/**
	 * 将selectbyId方法返回数据构建一个实体
	 * @param id
	 * @param a
	 * @return
	 */
	public  AccountBalance findOnebyId(int id  ) {
		AccountBalance a = new AccountBalance();
		ResultSet res = selectbyId(id,a);
		AccountBalance accountBalance = new AccountBalance();
		try
		{
			while(res.next()) {
				//根据字段数量和类型一个个赋吧 - -
				accountBalance.set_id(res.getInt("_id"));
				accountBalance.setSecretPassword(res.getString("secretPassword"));
				
			}
			return accountBalance;
		} catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return null;
	}
	public <E> ArrayList<AccountBalance> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new AccountBalance(), columnName, element);
		
	}
	
	//测试
	public static void main(String[] args)
	{
		
	}
	
	
}
