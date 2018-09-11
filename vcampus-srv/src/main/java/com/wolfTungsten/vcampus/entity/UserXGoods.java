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
	public static final String IN_CAR = "in_cart";
	public static final String AMOUNT = "amount";
	public static final String WHETHERBUY = "whetherbuy";
	public static final String CREATETIME = "createtime";
	
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
	private int in_cart; //1表示在购物车  
	@DatabaseField(columnName = UserXGoods.AMOUNT)
	private int amount ;
	@DatabaseField(columnName = UserXGoods.WHETHERBUY)
	private int whetherbuy; //1 表示已购买该商品
	@DatabaseField(columnName = UserXGoods.CREATETIME)
	private long createtime;
	public UserXGoods() {
		
	}

	public long getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(long createtime)
	{
		this.createtime = createtime;
	}

	public int getWhetherbuy()
	{
		return whetherbuy;
	}

	public void setWhetherbuy(int whetherbuy)
	{
		this.whetherbuy = whetherbuy;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
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
	
	public int getIn_car()
	{
		return in_cart;
	}
	
	public void setIn_car(int in_cart)
	{
		this.in_cart = in_cart;
	}

}
