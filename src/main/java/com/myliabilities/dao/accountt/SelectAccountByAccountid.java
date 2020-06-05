package com.myliabilities.dao.accountt;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.myliabilities.dao.po.AccountPo;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.db.utils.SelectTemplate;

public class SelectAccountByAccountid implements IPreparedStatementCreate {

	private AccountPo accountPo;

	public SelectAccountByAccountid(AccountPo accountPo) {
		this.accountPo = accountPo;
	}
	@Override
	public void createPreparedStatement( PreparedStatement ps) throws SQLException {
		ps.setString(1, accountPo.getAccountid());
	}

	public String getSql() {
		String sql = "select " + SelectTemplate.selectResultColumn(AccountPo.class) + " from account where accountid=?";
		return sql;
	}

}
