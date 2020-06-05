package com.myliabilities.dao.accountbook;

import java.sql.PreparedStatement;

import com.myliabilities.db.infs.IPreparedStatementCreate;

public class DeleteAccountBookById implements IPreparedStatementCreate {

	private Long id;
	private String tableName = "accountbook";
	
	public DeleteAccountBookById(Long id) {
		this.id = id;
	}
	
	@Override
	public void createPreparedStatement( PreparedStatement ps) throws Exception {
		ps.setObject(1, id);

	}

	@Override
	public String getSql() {
		return "delete from " + tableName + " where id=?;";
	}

}
