package com.myliabilities.dao.accountbook;

import com.myliabilities.db.DBFactory;
import com.myliabilities.db.infs.IDDLSupport;

public class CreateAccountbookTable {

	private IDDLSupport ddl = DBFactory.createDDLSupport();

	/**
	 * 创建账户表
	 * @return boolean
	 */
	public boolean createAccountTable() {
		/*
		 * id,accountid,tag,tagname,amount,dc,acountdate,memo,typeid,ver,createtime,updatetime,remark
		 * */
		String sql = "CREATE TABLE IF NOT EXISTS accountbook (" 
				+ "id integer primary key autoincrement,"
				+ "accountid varchar(64) not null," 
				+ "tag varchar(64) not null," 
				+ "tagname varchar(64)," 
				+ "amount REAL not null,"
				+ "dc integer not null," 
				+ "acountdate integer not null," 
				+ "memo varchar(255),"
				+ "typeid integer," 
				+ "ver integer,"
				+ "createtime integer," 
				+ "updatetime integer," 
				+ "remark varchar(255)" 
				+ ");";

		return ddl.createTable(sql);
	}

	public boolean createAccountIndex() {
		return ddl.createIndex("accountbook", "idx_acctid_date", "accountid", "acountdate");
	}
}
