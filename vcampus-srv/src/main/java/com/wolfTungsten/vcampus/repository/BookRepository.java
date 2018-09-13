package com.wolfTungsten.vcampus.repository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.google.gson.internal.LinkedTreeMap;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.Goods;
import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.entity.UserXBook;

public class BookRepository extends CurdRepository<Book>
{

	public BookRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Book.class);
		
	}
	public void addBook(String name,String isbn,String author,String publisher,String location ,long createTime,long updateTime) throws SQLException {
		Book book = new Book();
		book.setName(name);
		book.setIsbn(isbn);
		book.setCreateTime(createTime);
		book.setUpdateTime(updateTime);
		book.setAuthor(author);
		book.setLocation(location);
		book.setPublisher(publisher);
		dao.create(book);
		
	}
	
	public void deleteBookByUuid(String uuid) throws SQLException {
		UUID bookuuid =UUID.fromString(uuid);
		dao.delete((PreparedDelete<Book>)dao.deleteBuilder()
				.where().eq(Book.UUID, bookuuid).prepare());
		
	}
	
	public void updateBook(LinkedTreeMap<String,Object> booksinfo) throws SQLException {
		UpdateBuilder<Book,String> updateBuilder = dao.updateBuilder();
		String bookUuid = (String)booksinfo.get("uuid");
		updateBuilder.where().eq("uuid", bookUuid);
		booksinfo.remove("uuid");
		for(String columnName:booksinfo.keySet())
		{
			updateBuilder.updateColumnValue(columnName, booksinfo.get(columnName)).update();
		}

	}
	//返回全部书
	public ArrayList<HashMap<String,Object>> inquireAllBook() throws SQLException {
		ArrayList<Book> booksList = new ArrayList<>();
		ArrayList<HashMap<String,Object>> booksinfoList = new ArrayList<>(); 	
		booksList = (ArrayList<Book>) dao.queryForAll();
		for(Book book:booksList) {
			HashMap<String,Object> bookinfo = new HashMap<>();
			bookinfo.put(Book.UUID, book.getUuid().toString());
			bookinfo.put(Book.NAME, book.getName());
			bookinfo.put(Book.ISBN,book.getIsbn());
			bookinfo.put(Book.CREATETIME,book.getCreateTime());
			bookinfo.put(Book.AUTHOR,book.getAuthor());
			bookinfo.put(Book.LOCATION, book.getLocation());
			bookinfo.put(Book.PUBLISHER, book.getPublisher());
			booksinfoList.add(bookinfo);
		}
		return booksinfoList;				
	}
	
	//根据字段和值找对应书籍
	public ArrayList<HashMap<String,Object>> inquireByFlag(String flag, Object value) throws SQLException {
		ArrayList<Book> booksList = new ArrayList<>();
		QueryBuilder<Book, String> qbd = dao.queryBuilder();
		qbd.where().like(flag, "%"+value+"%");
		
		
		ArrayList<HashMap<String,Object>> booksinfoList = new ArrayList<>();
		booksList = (ArrayList<Book>) qbd.query();//(ArrayList<Book>)dao.queryForEq(flag,value);
		for(Book b:booksList) {
			HashMap<String,Object>bookinfo = new HashMap<>();
			bookinfo.put(Book.UUID, b.getUuid().toString());
			bookinfo.put(Book.NAME, b.getName());
			bookinfo.put(Book.ISBN,b.getIsbn());
			bookinfo.put(Book.CREATETIME,b.getCreateTime());
			bookinfo.put(Book.AUTHOR, b.getAuthor());
			bookinfo.put(Book.LOCATION, b.getLocation());
			bookinfo.put(Book.PUBLISHER, b.getPublisher());
			booksinfoList.add(bookinfo);			
		}
		if(booksinfoList.size()==0) throw new SQLException("没有找到这本书");
		return booksinfoList;		
				
		}
		/**
		 * 根据uuid查书
		 * @param uuid
		 * @return
		 * @throws SQLException
		 */
	public Book inquireById(String uuid) throws SQLException {
		Book book = new Book();
		List<Book> books = dao.queryForEq(Book.UUID, UUID.fromString(uuid));
		if(books==null)throw new SQLException("没找到这本书");
		
		return books.get(0);
		
	}
	
	public void updateByflag(String uuid,String column,Object value) throws SQLException {
		dao.update((PreparedUpdate<Book>)dao.updateBuilder()
				.updateColumnValue(column,value )
				.where().eq(UserXBook.UUID, UUID.fromString(uuid)).prepare());
		
	}
	
	

}
