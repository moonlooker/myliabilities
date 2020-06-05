package com.myliabilities.db.infs;

import java.sql.Connection;

/**
 * 创建数据库链接
 * @author LL
 *
 */
public interface IDBConnection {

	Connection getConnection();
}
