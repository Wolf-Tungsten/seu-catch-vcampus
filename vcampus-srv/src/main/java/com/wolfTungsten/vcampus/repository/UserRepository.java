package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.google.gson.internal.LinkedTreeMap;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.User;

public class UserRepository extends CurdRepository<User> {

	public UserRepository(ConnectionSource conn) throws SQLException{
		super(conn, User.class);
	}
	
	public void AddUser(String username, String cardnum) throws SQLException {
		User user = new User();
		user.setCardnum(cardnum);
		user.setUsername(username);
		dao.create(user);
	}
	public void addUser(String username,String cardnum,String hash_password,int identity,
			int privilege ,String photo,String idcardNum,long birthdate,String address) throws SQLException {
		
		List<User> userList = dao.query((PreparedQuery<User>)dao.queryBuilder()
				.where().eq(User.CARDNUM, cardnum).prepare());
		if(userList.size()==0) {
		User user = new User();
		user.setUsername(username);
		user.setCardnum(cardnum);
		user.setHash_password(hash_password);
		user.setIdentity(identity);
		user.setPrivilege(privilege);
		user.setPhoto(photo);
		user.setAddress(address);
		user.setBirthdate(birthdate);
		user.setIdcardNum(idcardNum);
		dao.create(user);
		}else
		{
			throw new SQLException("该卡号已注册");
		}
	}
	
	public HashMap<String,Object> login(String cardnum,String password) throws SQLException {
		//根据cardnum和password查询用户
		HashMap<String,Object> loginreturn = new HashMap<>();
		List<User> userList = 
				dao.query((PreparedQuery<User>) dao.queryBuilder().where().eq(User.CARDNUM, cardnum).and()
						.eq(User.PASSWORD, password).prepare());
		if(userList.size()!=0) {
			System.out.println("userlist size = 0");
			loginreturn.put("uuid",userList.get(0).getUuid() );
			loginreturn.put("privilege", userList.get(0).getPrivilege());
			return loginreturn;
		}
		else	
			return null;	
		 	
		
	}

	public User inquireById(String uuid) throws SQLException {
		List<User> userlist = dao.queryForEq(User.UUID, UUID.fromString(uuid));
		return userlist.get(0);
	}
	public ArrayList<User> inquireByIds(String uuid) throws SQLException{
		List<User> userlist = dao.queryForEq(User.UUID, UUID.fromString(uuid));
		return (ArrayList<User>) userlist;
	}
	public User inquireByCardnum(String cardnum) throws SQLException{
		List<User> ulist = dao.queryForEq(User.CARDNUM, cardnum);
		return ulist.get(0);
	}
	
	//用于银行系统注册时检测卡号是否存在
	public Boolean checkExist(String cardnum,String idcardNum) throws SQLException {
		List<User> userList = 
				dao.query((PreparedQuery<User>) dao.queryBuilder().where().eq(User.CARDNUM, cardnum).and()
						.eq(User.IDCARDNUM, idcardNum).prepare());
		if(userList.size()!=0) {
			return true;
		}
		else	
			throw new SQLException("该卡号不存在");	 	
	}
	
	
	public Boolean updateUser(String userid, String username,String cardnum,String hash_password,int identity,
			int privilege ,String photo,String idcardNum,long birthdate,String address) throws SQLException {
		UpdateBuilder<User, String> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq("uuid", userid);//UUID.from(userid)
		updateBuilder.updateColumnValue(User.USERNAME, username)
					.updateColumnValue(User.CARDNUM, cardnum)
					.updateColumnValue(User.PASSWORD, hash_password)
					.updateColumnValue(User.IDENTITY, identity)
					.updateColumnValue(User.PRIVILEGE, privilege)
					.updateColumnValue(User.PHOTO, photo)
					.updateColumnValue(User.IDCARDNUM, idcardNum)
					.updateColumnValue(User.BIRTHDATE, birthdate)
					.updateColumnValue(User.ADDRESS, address)
					.update();
		return true;
	}
	
	public void modifyOldPwd(String newPwd,String userid) throws SQLException {
		
		UpdateBuilder<User,String> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(User.UUID, UUID.fromString(userid));
		updateBuilder.updateColumnValue(User.PASSWORD, newPwd).update();
	} 

	public void modifyByflag(String userid,String column ,Object value) throws SQLException {
		UpdateBuilder<User,String> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(User.UUID, UUID.fromString(userid));
		updateBuilder.updateColumnValue(column, value).update();
	}
	public void updateUser2(HashMap<String,Object> userinfo,String useruuid) throws SQLException {
		UpdateBuilder<User, String> updateBuilder = dao.updateBuilder();
		
		updateBuilder.where().eq(User.UUID, UUID.fromString(useruuid));
		userinfo.remove("uuid");
		for(String columnName:userinfo.keySet())
		{
			updateBuilder.updateColumnValue(columnName, userinfo.get(columnName)).update();
		}

	}
}
