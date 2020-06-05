package com.myliabilities.db.infs;

import java.sql.PreparedStatement;

/**
 * 拼接一个查询/更新/插入/删除等条件参数
 * 
 * @author LL
 * 2020年5月25日
 */
public interface IPreparedStatementCreate {

    /**
     * 设置PreparedStatement内值,对应SQL需要的输入字段
     * @param ps
     * @throws Exception
     */
	void createPreparedStatement(PreparedStatement ps) throws Exception ;
	/**
	 * 获得执行SQL
	 * @return
	 */
	String getSql();
}
