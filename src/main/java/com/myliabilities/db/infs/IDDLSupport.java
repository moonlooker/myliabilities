package com.myliabilities.db.infs;

/**
 * 数据库DDL操作
 * @author LL
 *
 */
public interface IDDLSupport {
	
	/**
	 * 创建表
	 * @param sql
	 * @return true成功,false失败
	 */
	boolean createTable( String sql);
	
	/**
	 * 创建索引
	 * @param tableName 表名
	 * @param indexName 索引名
	 * @param column 字段集合
	 * @return true成功,false失败
	 */
	boolean createIndex( String tableName, String indexName, String... columns);
}
