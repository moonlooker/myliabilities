package com.myliabilities.db;

import com.myliabilities.db.infs.IDBConnection;
import com.myliabilities.db.infs.IDDLSupport;
import com.myliabilities.db.infs.IDMLSupport;

/**
 * 数据库操作对象工厂
 * @author LL
 *
 */
public class DBFactory {
    /**
     * 获取数据库连接
     * @return
     */
	public static IDBConnection createConnection() {
		return new SQLiteConnection();
	}
	/**
	 * 获取一个DDL操作对象
	 * @return
	 */
	public static IDDLSupport createDDLSupport() {
		return new SQLiteDDLSupport();
	}
	/**
	 * 获取一个DML操作对象
	 * @return
	 */
	public static IDMLSupport createDMLSupport() {
		return new SQLiteDMLSupport();
	}
}
