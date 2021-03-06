package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.entity.UserXBook;

public class UserXBookRepository extends CurdRepository<UserXBook>
{
	public static final long duration = 30*24*3600;
	public UserXBookRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, UserXBook.class);
	
	}
	
	public void addUserXBook(String useruuid,String bookuuid,int isReturn,long borrowdate) throws SQLException {
		List<UserXBook> list = dao.queryForEq(UserXBook.BOOK_ID, bookuuid);
		if(list.size()!=0&&list.get(0).getIsReturn()==0)throw new SQLException("该书已被借！");
		UserXBook uxb= new UserXBook();
		uxb.setUser_id(useruuid);
		uxb.setBook_id(bookuuid);
		uxb.setReturn(isReturn);
		uxb.setBorrowdate(borrowdate);
		uxb.setReturndate(0);
		uxb.setDeaddate(borrowdate+duration);
		dao.create(uxb);
	}
	//update isreturn to true or false
	public String updateUserXBook(String uuid ,String column,Object i) throws SQLException {
		
		dao.update((PreparedUpdate<UserXBook>)dao.updateBuilder()
				.updateColumnValue(column, i)
				.where().eq(UserXBook.UUID,UUID.fromString(uuid) ).prepare());
//		dao.update((PreparedUpdate<UserXBook>)dao.updateBuilder()
//				.updateColumnValue(UserXBook.RETURNDATE, System.currentTimeMillis()/1000)
//				.where().eq(UserXBook.UUID,UUID.fromString(uuid) ).prepare());
		
		List<UserXBook> uxblist = dao.query((PreparedQuery<UserXBook>)dao
				.queryBuilder().where().eq(UserXBook.UUID, UUID.fromString(uuid)).prepare());
		return uxblist.get(0).getBook_id();
	}
	/**
	 * 计算图书剩余量
	 * @throws SQLException 
	 */
	public int surplusCal (String column,Object value) throws SQLException {

		List<UserXBook> relationlist =  dao.query((PreparedQuery<UserXBook>)dao.queryBuilder().where().eq(column, value).and()
				.eq(UserXBook.ISRETURN, true).prepare());
				
//				List<User> userList = 
//				dao.query((PreparedQuery<User>) dao.queryBuilder().where().eq(User.CARDNUM, cardnum).and()
//						.eq(User.PASSWORD, password).prepare());
		return relationlist.size();
	}
	
	public void deleteUserXBook(String bookUuid) throws SQLException {
		UUID bookuuid =UUID.fromString(bookUuid);
		
		dao.delete((PreparedDelete<UserXBook>)dao.deleteBuilder()
				.where().eq(UserXBook.UUID, bookuuid).prepare());
	}
	
	//判断是否可以续借，如果可以返回新到期时间
	
	public long checkRenew(String uuid) throws Exception {
		List<UserXBook> booklist =dao.queryForEq(UserXBook.UUID, UUID.fromString(uuid));
		if(booklist.size()==0)throw new SQLException("凉了，没得续借");
		long borrowdate = booklist.get(0).getBorrowdate();
		long deadDate = booklist.get(0).getDeaddate();
		long newReturndate =deadDate+duration;
		if(deadDate<System.currentTimeMillis()/1000)
			throw new Exception("超过规定时间未还书，请先还书!");
		if(newReturndate-borrowdate >2*duration) {
			throw new Exception("你已经续借过了，每人只能续借一次");
		}
		return newReturndate;
	}
	//查借阅记录或被借阅记录
	public ArrayList<HashMap<String, Object>> inquireByFlag(String flag, Object value) throws SQLException {
		ArrayList<UserXBook> recordList = new ArrayList<>();
		recordList = (ArrayList<UserXBook>)dao.queryForEq(flag,value);
		ArrayList<HashMap<String,Object>> recordMaplist = new ArrayList<>();
		for(UserXBook record : recordList) {
			HashMap<String,Object> recordmap = new HashMap<>();
			recordmap.put(UserXBook.UUID, record.getUuid().toString());
			recordmap.put(UserXBook.BOOK_ID, record.getBook_id());
			recordmap.put(UserXBook.ISRETURN, record.getIsReturn());
			recordmap.put(UserXBook.BORROWDATE, record.getBorrowdate());
			recordmap.put(UserXBook.RETURNDATE, record.getReturndate());
			recordmap.put(UserXBook.DEADDATE,record.getDeaddate());
			recordMaplist.add(recordmap);
		}
		return recordMaplist;			
				
		}
	// 0 没还 1 还了
	public boolean checkBorrow(String uuid) throws SQLException {
		List<UserXBook> list = dao.queryForEq(UserXBook.BOOK_ID, uuid);
		
		if(list.size()==0)return true;
		else {
			for(UserXBook uxb : list) {
				if(uxb.getIsReturn()==0)return false;
			}
		return true;
		}
	
	}
	
}
