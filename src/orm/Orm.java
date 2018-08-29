package orm;

import vo.cls.*;
import vo.relation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import orm.Utils.*;


//封装数据库操作
/**
 * 对某个实体进行数据库操作 Orm orm = new Orm() ; orm.实体名.方法(参数)
 * 
 * @param args
 */
public class Orm
{
	public User user;
	public Course course;
	public Exam exam;
	public Experiment experiment;
	public Merchandise merchandise;
	public TradingRecord tradingRecord;
	public Book book;
	public AccountBalance accountBalance;
	public ABalanceXTRecord abalanceXTRecord;
	public UserXBook userXbook;
	public UserXExam userXexam;
	public UserXExperiment userXexperiment;
	public UserXMerchandise userXmerchandise;
	@SuppressWarnings("rawtypes")
	public static JdbcUtils jdbcUtils;

	public Orm()
	{
		// 实例化
		user = new User();
		exam = new Exam();
		experiment = new Experiment();
		merchandise = new Merchandise();
		tradingRecord = new TradingRecord();
		book = new Book();
		accountBalance = new AccountBalance();
		abalanceXTRecord = new ABalanceXTRecord();
		userXbook = new UserXBook();
		userXexam = new UserXExam();
		userXexperiment = new UserXExperiment();
		userXmerchandise = new UserXMerchandise();

	}

	public static void main(String[] args)
	{
		
		
		
//		大致使用方法
//		Orm orm = new Orm(); //实例化orm 
		
//		orm.accountBalance.add(new AccountBalance(542,"zhongyua")); //添加一个余额账户
//		orm.accountBalance.deleteById(522); // 根据Id 删除 一个余额账户
//		orm.accountBalance.selectAll();  // 返回余额账户数据表
//		orm.accountBalance.selectbyId(15, new AccountBalance()); // 根据Id返回一个余额账户的数据
//		AccountBalance a = orm.accountBalance.findOnebyId(15); // 根据id 返回一个余额账户实体

		// -------------------------------------------------------------
////		AccountBalance ac = new AccountBalance(99, "598jj4751");
////		
//		
////		Orm.accountBalance.add(ac);
//		//Orm.accountBalance.deleteById(99);
//		
//		Orm.book.deleteById(224);
//		orm.book.deleteByFlag("Name", "编译原理");
//		Book book = new Book(225,"编译原理5","25","faq",2222,555);
//		orm.book.update(book);
//		ArrayList<Book> bookslist = orm.book.findByFlag("_id", 99);
//		ArrayList<Book> bookslist = orm.book.selectAll();
//		orm.book.add(new Book (99,"高等数学","2","sadf",222,333));
//		ResultSet res2 = Book.selectbyId(22, new Book());
//		if(bookslist==null)System.out.println("fuck");
//		for(Book book : bookslist) {
//			System.out.println("_id: "+book.get_id() +" name:"+book.getName()+ " amount: "+
//					book.getAmount()+" isbn:"+book.getIsbn() +" creatTime:"+
//					book.getCreateTime()+" updateTime: "+book.getUpdateTime());
//			
//		}
//		

	}

}
