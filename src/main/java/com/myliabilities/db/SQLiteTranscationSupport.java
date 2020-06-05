package com.myliabilities.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myliabilities.db.infs.IDBConnection;
import com.myliabilities.db.infs.IPreparedStatementCreate;

/**
 * 带事务的提交
 * @author Pactera
 * 2020年5月25日
 */
public class SQLiteTranscationSupport {

	private Logger log = LoggerFactory.getLogger(getClass());

	private IDBConnection conn = DBFactory.createConnection();

	private List<IPreparedStatementCreate> pscList = new ArrayList<>();
	
	/**
	 * 添加数据库操作集合
	 * @param psc
	 */
	public void addPSC(IPreparedStatementCreate psc) {
		pscList.add(psc);
	}
	
	/**
	 * 事务执行
	 * @return 多条sql的执行结果
	 */
	public List<Integer> transcationExecute() {
		Connection c = null;
		PreparedStatement ps = null;
		List<Integer> i = new ArrayList<>();
		try {
			c = conn.getConnection();
			//需要手动提交事务
			c.setAutoCommit(false);
			
			for(IPreparedStatementCreate psc : pscList) {
				ps = c.prepareStatement(psc.getSql());
				psc.createPreparedStatement(ps);
				i.add(ps.executeUpdate());
			}
			
			c.commit();
		} catch ( Exception e) {
			log.error("TranscationExecute Failed !!!", e);
			try {
				c.rollback();
				//如果回滚,直接结果为0个
				i = new ArrayList<>();
			} catch ( SQLException e1) {
				log.error("Rollback Failed !!!", e1);
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
		return i;
	}
}
