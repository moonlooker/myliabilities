package com.myliabilities.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myliabilities.db.infs.IDBConnection;
import com.myliabilities.db.infs.IDDLSupport;

public class SQLiteDDLSupport implements IDDLSupport {

	private Logger log = LoggerFactory.getLogger(getClass());

	private IDBConnection conn = DBFactory.createConnection();

	@Override
	public boolean createTable( String sql) {
		Connection c = null;
		Statement stt = null;
		try {
			c = conn.getConnection();
			stt = c.createStatement();
			int i = stt.executeUpdate(sql);
			if (i == 0) {
				return false;
			}
			return true;
		} catch ( Exception e) {
			log.error("CreateTable Failed !!!", e);
			return false;
		} finally {
			if (stt != null) {
				try {
					stt.close();
				} catch ( SQLException e) {
					log.error("Close Connection Failed !!!", e);
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch ( SQLException e) {
					log.error("Close Connection Failed !!!", e);
				}
			}
		}
	}

	@Override
	public boolean createIndex( String tableName, String indexName, String... columns) {
		Connection c = null;
		Statement stt = null;
		StringBuilder sb = new StringBuilder();
		sb.append("create index IF NOT EXISTS ").append(indexName).append(" on ").append(tableName);
		sb.append("(");
		if (columns.length > 1) {
			sb.append(columns[0]);
		} else {
		    //多组字段顺序设置
			for (int i = 0; i < columns.length; i++) {
				sb.append(columns[i]);
				if (i < columns.length - 1) {
					sb.append(",");
				}
			}
		}
		sb.append(")");
		try {
			c = conn.getConnection();
			stt = c.createStatement();
			int i = stt.executeUpdate(sb.toString());
			if (i == 0) {
				return false;
			}
			return true;
		} catch ( Exception e) {
			log.error("CreateIndex Failed !!!", e);
			return false;
		} finally {
			if (stt != null) {
				try {
					stt.close();
				} catch ( SQLException e) {
					log.error("Close Connection Failed !!!", e);
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch ( SQLException e) {
					log.error("Close Connection Failed !!!", e);
				}
			}
		}
	}

}
