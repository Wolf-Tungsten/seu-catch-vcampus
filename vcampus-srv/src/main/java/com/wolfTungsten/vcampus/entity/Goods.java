package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Goods")
public class Goods
{
	public static final String UUID = "uuid";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String SELLER = "seller";
	public static final String PRICE = "price";
	public static final String AMOUNT = "amount";
	public static final String IMAGE  = "image";
	
	@DatabaseField(generatedId = true, columnName = Goods.UUID)
	private UUID uuid;
	@DatabaseField(columnName = Goods.NAME)
	private String name;
	@DatabaseField(columnName = Goods.DESCRIPTION)
	private String description ;
	@DatabaseField(columnName = Goods.SELLER)
	private String seller;
	@DatabaseField(columnName = Goods.PRICE)
	private String price ; 
	@DatabaseField(columnName = Goods.AMOUNT)
	private int amount;
	@DatabaseField(columnName = Goods.IMAGE)
	private String image;
	
	public Goods() {
		
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getSeller()
	{
		return seller;
	}

	public void setSeller(String seller)
	{
		this.seller = seller;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}
	
	
	
}