package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.google.gson.internal.LinkedTreeMap;
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
			dao.update((PreparedUpdate<Book>)dao.updateBuilder().updateColumnValue(columnName, booksinfo.get(columnName)).where()
					.eq(Book.UUID, UUID.fromString(bookUuid)).prepare());
			updateBuilder.updateColumnValue(columnName, booksinfo.get(columnName));
		}
		
		
	}
	//返回全部书
	public List<HashMap<String,Object>> inquireAllBook() {
		
		
		return null;
				
	}
	//根据书名查询书籍
	public List<HashMap<String,Object>> inquireByFlag(String name) {
		return null;
	}
	
}
