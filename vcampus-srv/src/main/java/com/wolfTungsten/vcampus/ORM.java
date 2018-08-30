package com.wolfTungsten.vcampus;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.utils.AccessDatabaseType;

// 
public class ORM {
	
    private ORM(){
    	try {
    		DatabaseType databaseType = new AccessDatabaseType();
			ConnectionSource connectionSource =
					  new JdbcConnectionSource("jdbc:ucanaccess://d:/vCampus.accdb;memory=false", databaseType);
			Dao<User, String> userDao =
					  DaoManager.createDao(connectionSource, User.class);
			User user = new User();
			user.setCardnum("213162317");
			user.setUsername("高睿昊");
			userDao.create(user);
			try {
				connectionSource.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
