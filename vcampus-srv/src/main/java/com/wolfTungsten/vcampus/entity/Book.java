package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Book")
public class Book
{
	public static final String UUID = "uuid";
	public static final String NAME = "name";
	public static final String ISBN = "isbn";
	public static final String AUTHOR ="author";
	public static final String CREATETIME = "createTime";
	public static final String UPDATETIME = "updateTime";
	public static final String AMOUNT ="amount";
	public static final String SURPLUS = "surplus";
	public static final String PUBLISHER = "publisher";
	
	@DatabaseField(generatedId = true, columnName = Book.UUID)
	private UUID uuid;
	@DatabaseField(columnName = Book.NAME)
	private String name;
	@DatabaseField(columnName = Book.ISBN)
	private String isbn;
	@DatabaseField(columnName = Book.CREATETIME)
	private long createTime ;
	@DatabaseField(columnName = Book.AUTHOR)
	private String author;
	@DatabaseField(columnName = Book.UPDATETIME)
	private long updateTime;
	@DatabaseField(columnName = Book.AMOUNT)
	private int amount=0;
	@DatabaseField(columnName = Book.SURPLUS)
	private int surplus = 0;
	@DatabaseField(columnName = Book.PUBLISHER)
	private String publisher;
	public Book() {
		
	}
	
	
	public String getPublisher()
	{
		return publisher;
	}


	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}


	public int getSurplus()
	{
		return surplus;
	}


	public void setSurplus(int surplus)
	{
		this.surplus = surplus;
	}


	public int getAmount()
	{
		return amount;
	}


	public void setAmount(int amount)
	{
		this.amount = amount;
	}


	public String getAuthor()
	{
		return author;
	}


	public void setAuthor(String author)
	{
		this.author = author;
	}


	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getIsbn()
	{
		return isbn;
	}

	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}



	public long getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(long createTime)
	{
		this.createTime = createTime;
	}

	public long getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(long updateTime)
	{
		this.updateTime = updateTime;
	}
	
	
	
	
}
