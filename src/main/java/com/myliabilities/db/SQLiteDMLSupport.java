package com.myliabilities.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myliabilities.db.infs.IDBConnection;
import com.myliabilities.db.infs.IDMLSupport;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.utils.MyClassUtils;

public class SQLiteDMLSupport implements IDMLSupport {

	private Logger log = LoggerFactory.getLogger(getClass());

	private IDBConnection conn = DBFactory.createConnection();

	@Override
	public int update( IPreparedStatementCreate psc) {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = conn.getConnection();
			ps = c.prepareStatement(psc.getSql());
			psc.createPreparedStatement(ps);
			int i = ps.executeUpdate();
			return i;
		} catch ( Exception e) {
			log.error("Update Failed !!!", e);
			return 0;
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
	}

	@Override
	public int insert( IPreparedStatementCreate psc) {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = conn.getConnection();
			ps = c.prepareStatement(psc.getSql());
			psc.createPreparedStatement(ps);
			int i = ps.executeUpdate();
			return i;
		} catch ( Exception e) {
			log.error("Insert Failed !!!", e);
			return 0;
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
	}

	@Override
	public int delete( IPreparedStatementCreate psc) {
		
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = conn.getConnection();
			ps = c.prepareStatement(psc.getSql());
			psc.createPreparedStatement(ps);
			int i = ps.executeUpdate();
			return i;
		} catch ( Exception e) {
			log.error("Delete Failed !!!", e);
			return 0;
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
	}

	@Override
	public <T> T selectOne( IPreparedStatementCreate psc, Class<T> clazz) {
		Connection c = null;
		PreparedStatement ps = null;
		List<T> list = new ArrayList<>();
		try {
			c = conn.getConnection();
			ps = c.prepareStatement(psc.getSql());
			psc.createPreparedStatement(ps);
			ResultSet rs = ps.executeQuery();
			int collen = rs.getMetaData().getColumnCount();
			while (rs.next()) {
			    /*根据输入的类型创建返回对象,因为使用反射,没有额外配置,所以数据对象成员变量必须与表字段名一致*/
				@SuppressWarnings( "unchecked" )
				T t = (T) Class.forName(clazz.getName()).newInstance();
				for (int i = 0; i < collen; i++) {
					String colname = rs.getMetaData().getColumnName(i+1);
					MyClassUtils.setField(colname, rs.getObject(colname), t, clazz);
				}
				list.add((T)t);
			}
			
		} catch ( Exception e) {
			log.error("Select Failed !!!", e);
		}finally {
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
		//因为是查询单条,需要判断返回条数
		if(list.size()>1 || list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public <T> List<T> selectMore( IPreparedStatementCreate psc, Class<T> clazz) {
		Connection c = null;
		PreparedStatement ps = null;
		List<T> list = new ArrayList<>();
		try {
			c = conn.getConnection();
			ps = c.prepareStatement(psc.getSql());
			psc.createPreparedStatement(ps);
			ResultSet rs = ps.executeQuery();
			int collen = rs.getMetaData().getColumnCount();
			while (rs.next()) {
			    /*根据输入的类型创建返回对象,因为使用反射,没有额外配置,所以数据对象成员变量必须与表字段名一致*/
				T t = (T) Class.forName(clazz.getName()).newInstance();
				for (int i = 0; i < collen; i++) {
					String colname = rs.getMetaData().getColumnName(i+1);
					MyClassUtils.setField(colname, rs.getObject(colname), t, clazz);
				}
				list.add((T)t);
			}
			
		} catch ( Exception e) {
			log.error("Select Failed !!!", e);
		}finally {
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
		return list;
	}
	
}
