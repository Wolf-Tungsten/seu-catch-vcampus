package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "UserXGoods")
public class UserXGoods
{
	public static final String UUID = "uuid";
	public static final String USER_ID = "user_id";
	public static final String GOOD_ID = "good_id";
	public static final String COST = "cost";
	public static final String IN_CAR = "in_car";
	
	@DatabaseField(generatedId = true, columnName = UserXGoods.UUID)
	private UUID uuid ;
	@DatabaseField(columnName= UserXGoods.USER_ID)
	private String user_id;
	@DatabaseField(columnName = UserXGoods.GOOD_ID)
	private String good_id;
	@DatabaseField(columnName = UserXGoods.COST)
	private double cost;
	//in_car属性用于表示用户加入购物车的商品，如果不在购物车里则表示是用户已经购买的商品
	@DatabaseField(columnName = UserXGoods.IN_CAR)
	private boolean in_car;
	
	public UserXGoods() {
		
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

	public String getGood_id()
	{
		return good_id;
	}

	public void setGood_id(String good_id)
	{
		this.good_id = good_id;
	}

	public double getCost()
	{
		return cost;
	}

	public void setCost(Double cost)
	{
		this.cost = cost;
	}
	
	public boolean getIn_car()
	{
		return in_car;
	}
	
	public void setIn_car(boolean in_car)
	{
		this.in_car = in_car;
	}

}
