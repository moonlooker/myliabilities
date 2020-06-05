package com.myliabilities.db.infs;

import java.util.List;

/**
 * 数据库DML操作
 */
public interface IDMLSupport {
    
    /**
     * 更新操作
     * @param PreparedStatement实现
     * @return 更新记录数
     */
	int update( IPreparedStatementCreate psc);
	
	/**
	 * 插入数据
	 * @param PreparedStatement实现
	 * @return 插入数据条数
	 */
	int insert( IPreparedStatementCreate psc);
	
	/**
	 * 删除数据
	 * @param PreparedStatement实现
	 * @return 删除条数
	 */
	int delete( IPreparedStatementCreate psc);
	
	/**
	 * 查询一条记录
	 * @param PreparedStatement实现
	 * @param clazz 返回的对象
	 * @return 如果没有符合的值返回null
	 */
	<T> T selectOne( IPreparedStatementCreate psc, Class<T> clazz);
	
	/**
	 * 查询多条记录
	 * @param PreparedStatement实现
	 * @param clazz 返回的对象
	 * @return List<T>
	 */
	<T> List<T> selectMore( IPreparedStatementCreate psc, Class<T> clazz);
}
