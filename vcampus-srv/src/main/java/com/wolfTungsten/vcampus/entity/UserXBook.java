package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName ="UserXBook")
public class UserXBook
{
	public static final String UUID = "uuid";
	public static final String USER_ID = "user_id";
	public static final String BOOK_ID ="book_id";
	public static final String ISRETURN ="isReturn";
	
	@DatabaseField(generatedId = true, columnName = UserXBook.UUID)
	private UUID uuid;
	@DatabaseField(columnName= UserXBook.USER_ID)
	private String user_id;
	@DatabaseField(columnName= UserXBook.BOOK_ID)
	private String book_id;
	@DatabaseField(columnName= UserXBook.ISRETURN)
	private boolean isReturn;
	
	public UserXBook() {
		
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public String getBook_id()
	{
		return book_id;
	}

	public void setBook_id(String book_id)
	{
		this.book_id = book_id;
	}

	public boolean isReturn()
	{
		return isReturn;
	}

	public void setReturn(boolean isReturn)
	{
		this.isReturn = isReturn;
	}
	
	
}
