package com.myliabilities.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.db.utils.SelectTemplate;

/**
 * 查询一个表的全部记录
 * @author Pactera
 * 2020年5月25日
 * @param <T>
 */
public class SelectAll<T> implements IPreparedStatementCreate {

	private String tableName;
	private Class<T> clazz;
	
	public SelectAll(String tableName) {
		this.tableName = tableName;
	}
	@Override
	public void createPreparedStatement( PreparedStatement ps) throws SQLException {
	}

	public String getSql() {
		String sql = "select " + SelectTemplate.selectResultColumn(clazz) + " from ";
		return sql + this.tableName;
	}

}
