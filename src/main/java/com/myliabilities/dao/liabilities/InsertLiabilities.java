package com.myliabilities.dao.liabilities;

import java.sql.PreparedStatement;

import com.myliabilities.dao.po.LiabilitiesPo;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.db.utils.InsertTemplate;

/**
 * 插入账户数据
 * 
 * @author LL
 * 2020年5月24日
 */
public class InsertLiabilities implements IPreparedStatementCreate {

	private LiabilitiesPo liabilitiesPo;
	private String tableName = "liabilities";
	private String[] columns = { "id", "accountid", "liabilities", "type", "settledate", "monthpay", "paymonth", "statu", "memo",
			"ver", "createtime", "updatetime", "remark" };

	public InsertLiabilities( LiabilitiesPo liabilitiesPo) {
		this.liabilitiesPo = liabilitiesPo;
	}

	public InsertLiabilities( LiabilitiesPo liabilitiesPo, String tableName, String... columns) {
		this.liabilitiesPo = liabilitiesPo;
		this.tableName = tableName;
		this.columns = columns;
	}

	@Override
	public void createPreparedStatement( PreparedStatement ps) throws Exception {
		InsertTemplate.createPreparedStatement(ps, liabilitiesPo, LiabilitiesPo.class, columns);
	}

	public String getSql() {
		return InsertTemplate.createInsertSQL(tableName, columns);
	}

}
