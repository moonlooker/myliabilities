package com.myliabilities.dao.liabilities;

import java.sql.PreparedStatement;

import com.myliabilities.db.infs.IPreparedStatementCreate;

public class DeleteLiabilitiesById implements IPreparedStatementCreate {

	private Long id;
	private String tableName = "liabilities";
	
	public DeleteLiabilitiesById(Long id) {
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
