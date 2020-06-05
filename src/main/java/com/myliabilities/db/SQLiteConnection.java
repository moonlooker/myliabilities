package com.myliabilities.db;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myliabilities.db.infs.IDBConnection;

/**
 * 获得一个SQLite数据库连接对象
 * @author LL
 *
 */
public class SQLiteConnection implements IDBConnection {

	private Logger log = LoggerFactory.getLogger(SQLiteConnection.class);
	
	@Override
	public Connection getConnection() {
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      //如果库不存在会直接创建
	      c = DriverManager.getConnection("jdbc:sqlite:liabilities.db");
	    } catch ( Exception e ) {
	      log.error("Cannot create DB connection !!!",e);
	    }
		return c;
	}

}
