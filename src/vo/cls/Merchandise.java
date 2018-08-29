package vo.cls;

import java.sql.ResultSet;
import java.util.ArrayList;

import orm.Utils.JdbcUtils;
import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;

//商品实体
@Table(name = "tb_Merchandise")
public class Merchandise
{
	@ID(name = "_id")
	private int _id;
	@Column(name = "Name")
	private String name;
	@Column(name = "Description")
	private String description; //商品描述
	@Column(name = "Price")
	private String price;  //价格
	@Column(name = "Amount")
	private int amount; //数量
	@Column(name = "Image")
	private String image; //图片
	@Column(name = "Seller")
	private String seller; //销售方
	@Column(name = "CreateTime")
	private int createTime; // 创建时间戳
	@Column(name = "UpdateTime")
	private int updateTime; //更新时间戳
	
	public Merchandise()
	{
		
	}
	
	public Merchandise(int _id, String name, String description, String price, int amount, String image,
			String seller, int createTime, int updateTime)
	{
		super();
		this._id = _id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.image = image;
		this.seller = seller;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	//getter and setter
	public int get_id()
	{
		return _id;
	}
	public void set_id(int _id)
	{
		this._id = _id;
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
	public String getSeller()
	{
		return seller;
	}
	public void setSeller(String seller)
	{
		this.seller = seller;
	}
	public int getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(int createTime)
	{
		this.createTime = createTime;
	}
	public int getUpdateTime()
	{
		return updateTime;
	}
	public void setUpdateTime(int updateTime)
	{
		this.updateTime = updateTime;
	}
	public  int add(Merchandise m)
	{	
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.add(m);
		return result;
	}
	public   <E> boolean deleteByFlag(String Flag ,E value) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		int result = jdbcUtil.delete(new Merchandise(),Flag,value );
		
		return result< 0 ?false:true;
		
	}
	public  ResultSet selectbyId(int id , Merchandise m) {
		JdbcUtils jdbcUtil = new JdbcUtils<>();
		 return  jdbcUtil.selectOneById(id, m);
		  
	}
	public  ArrayList<Merchandise> selectAll() {
		JdbcUtils jdbcUtils = new JdbcUtils<>();	
		return jdbcUtils.selectAll(new Merchandise());
		
	}
	public <E> ArrayList<Merchandise> findByFlag(String columnName,E element){
		JdbcUtils jdbcUtils = new JdbcUtils<>();
		return jdbcUtils.findByFlag(new Merchandise(), columnName, element);
		
	}
	

}
