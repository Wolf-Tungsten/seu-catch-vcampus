package com.wolfTungsten.vcampus;

import java.sql.SQLException;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.repository.BookRepository;
import com.wolfTungsten.vcampus.repository.TokenRepository;
import com.wolfTungsten.vcampus.repository.UserRepository;
import com.wolfTungsten.vcampus.repository.TradingRecordRepository;
import com.wolfTungsten.vcampus.utils.AccessDatabaseType;

// 
public class ORM {
	
	private ConnectionSource conn;
	
	public UserRepository userRepository;
	public TokenRepository tokenRepository;
	public BookRepository bookRepository;
	public TradingRecordRepository tradingRecordRepository;
    private ORM(){
    	
    	DatabaseType databaseType = new AccessDatabaseType();
		try {
			conn = new JdbcConnectionSource("jdbc:ucanaccess://d:/vCampus.accdb;memory=false", databaseType);
			// initialize repositories
			userRepository = new UserRepository(conn);	
			tokenRepository = new TokenRepository(conn);
			bookRepository = new BookRepository(conn);
			tradingRecordRepository=new TradingRecordRepository(conn);
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
	
}
