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
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.User;

public class BookRepository extends CurdRepository<Book>
{

	public BookRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Book.class);
		
	}
	public void addBook(String name,String isbn,String author,long createTime,long updateTime) throws SQLException {
		Book book = new Book();
		book.setName(name);
		book.setIsbn(isbn);
		book.setCreateTime(createTime);
		book.setUpdateTime(updateTime);
		book.setAuthor(author);
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
			dao.update((PreparedUpdate<Book>)dao.updateBuilder()
					.updateColumnValue(columnName, booksinfo.get(columnName)).where()
					.eq(Book.UUID, UUID.fromString(bookUuid)).prepare());
			updateBuilder.updateColumnValue(columnName, booksinfo.get(columnName));
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
			booksinfoList.add(bookinfo);
		}
		return booksinfoList;
				
	}
	/**
	 * 根据就字段和值查书
	 * @param colName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<HashMap<String,Object>> inquireByFlag(String colName,Object value) throws SQLException {
		//反射找匹配的字段名 然后执行查询
		ArrayList<Book> booksList = new ArrayList<>();
		Book book = new Book();
		Class clz = book.getClass();
		Field[] fields = clz.getDeclaredFields();
		String columnName ="";
		for(Field field:fields) {
			field.setAccessible(true);
			columnName=field.getAnnotation(DatabaseField.class).columnName();
		if(columnName.equals(colName)) {
			columnName=colName;
			break;
			}		
		}
		ArrayList<HashMap<String,Object>> booksinfoList = new ArrayList<>(); 
		booksList = (ArrayList<Book>)dao.queryForEq(columnName, value);
		for(Book b:booksList) {
			HashMap<String,Object> bookinfo = new HashMap<>();
			bookinfo.put(Book.UUID, b.getUuid().toString());
			bookinfo.put(Book.NAME, b.getName());
			bookinfo.put(Book.ISBN,b.getIsbn());
			bookinfo.put(Book.CREATETIME,b.getCreateTime());
			bookinfo.put(Book.AUTHOR,b.getAuthor());
			booksinfoList.add(bookinfo);
		}	
		return booksinfoList;
		
	}
	
}
