/**
 * ORM类
 * 
 */
package com.wolfTungsten.vcampus;

import java.sql.SQLException;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.repository.AccountBalanceRepository;
import com.wolfTungsten.vcampus.repository.BookRepository;

import com.wolfTungsten.vcampus.repository.CourseRepository;

import com.wolfTungsten.vcampus.repository.GoodsRepository;

import com.wolfTungsten.vcampus.repository.ExamRepository;
import com.wolfTungsten.vcampus.repository.ExperimentRepository;
import com.wolfTungsten.vcampus.repository.GoodsRepository;
import com.wolfTungsten.vcampus.repository.TokenRepository;
import com.wolfTungsten.vcampus.repository.UserRepository;
import com.wolfTungsten.vcampus.repository.TradingRecordRepository;
import com.wolfTungsten.vcampus.repository.UserXBookRepository;
import com.wolfTungsten.vcampus.repository.UserXCourseRepository;
import com.wolfTungsten.vcampus.repository.UserXExamRepository;
import com.wolfTungsten.vcampus.repository.UserXExperimentRepository;
import com.wolfTungsten.vcampus.repository.UserXGoodsRepository;
import com.wolfTungsten.vcampus.utils.AccessDatabaseType;

// 
public class ORM {
	
	private ConnectionSource conn;
	
	public UserRepository userRepository;
	public TokenRepository tokenRepository;
	public BookRepository bookRepository;
	
	public TradingRecordRepository tradingRecordRepository;
	public AccountBalanceRepository accountBalanceRepository;
	public UserXBookRepository userXBookRepository;
	public UserXCourseRepository userXCourseRepository;
	public UserXExamRepository userXExamRepository;
	public ExamRepository examRepository;
	public ExperimentRepository experimentRepository;
	public UserXExperimentRepository userXExperimentRepository;
	public UserXGoodsRepository userXGoodsRepository;
	

	public CourseRepository courseRepository;

	public GoodsRepository goodsRepository;

    private ORM(){
    	
    	DatabaseType databaseType = new AccessDatabaseType();
		try {
			conn = new JdbcConnectionSource("jdbc:ucanaccess://E:/vCampus.accdb;memory=false", databaseType);
			// initialize repositories
			userRepository = new UserRepository(conn);	
			tokenRepository = new TokenRepository(conn);
			bookRepository = new BookRepository(conn);
			goodsRepository = new GoodsRepository(conn);
			tradingRecordRepository=new TradingRecordRepository(conn);
			accountBalanceRepository=new AccountBalanceRepository(conn);
			userXBookRepository = new UserXBookRepository(conn);
			userXCourseRepository = new UserXCourseRepository(conn);
			userXExamRepository = new UserXExamRepository(conn);
			examRepository = new ExamRepository(conn);
			experimentRepository = new ExperimentRepository(conn);
			userXExperimentRepository = new UserXExperimentRepository(conn);
			userXGoodsRepository = new UserXGoodsRepository(conn);

			courseRepository = new CourseRepository(conn);

			goodsRepository =new GoodsRepository(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    }
    
    private static class ORMHolder{
        private final static ORM instance=new ORM();
    }
    
    public static ORM getInstance(){
        return ORMHolder.instance;
    }
	
};
