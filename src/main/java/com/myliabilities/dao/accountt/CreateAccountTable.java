package com.myliabilities.dao.accountt;

import com.myliabilities.db.DBFactory;
import com.myliabilities.db.infs.IDDLSupport;

public class CreateAccountTable {

	private IDDLSupport ddl = DBFactory.createDDLSupport();
	
	/**
	 * 创建账户表
	 * @return boolean
	 */
	public boolean createAccountTable() {
		/*
		 * id,accountid,accountname,assets,hope,liabilities,ver,createtime,updatetime,remark
		 * id,账户id,别名,资产,积攒,负债
		 * */
		String sql = "CREATE TABLE IF NOT EXISTS account ("
				+"id integer primary key autoincrement,"
				+ "accountid varchar(64) not null unique,"
				+ "accountname varchar(128),"
				+ "assets REAL,"
				+ "hope REAL,"
				+ "liabilities REAL,"
				+ "ver integer,"
				+ "createtime integer,"
				+ "updatetime integer,"
				+ "remark varchar(255)"
				+ ");";
		
		return ddl.createTable(sql);
	}
	
	public boolean createAccountIndex() {
		return ddl.createIndex("account", "idx_acctid", "accountid");
	}
}
