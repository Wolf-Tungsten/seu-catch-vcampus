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
	public static final String BORROWDATE = "borrowdate";
	public static final String RETURNDATE = "returndate";
	
	@DatabaseField(generatedId = true, columnName = UserXBook.UUID)
	private UUID uuid;
	@DatabaseField(columnName= UserXBook.USER_ID)
	private String user_id;
	@DatabaseField(columnName= UserXBook.BOOK_ID)
	private String book_id;
	@DatabaseField(columnName= UserXBook.ISRETURN)
	private int isReturn;
	@DatabaseField(columnName = UserXBook.BORROWDATE)
	private long borrowdate;
	@DatabaseField(columnName = UserXBook.RETURNDATE)
	private long returndate;
	public long getBorrowdate()
	{
		return borrowdate;
	}

	public void setBorrowdate(long borrowdate)
	{
		this.borrowdate = borrowdate;
	}

	public long getReturndate()
	{
		return returndate;
	}

	public void setReturndate(long returndate)
	{
		this.returndate = returndate;
	}

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

	public int getIsReturn()
	{
		return isReturn;
	}

	public void setReturn(int isReturn)
	{
		this.isReturn = isReturn;
	}
	
	
}
