package com.myliabilities.dao.liabilities;

import com.myliabilities.db.DBFactory;
import com.myliabilities.db.infs.IDDLSupport;

public class CreateLiabilitiesTable {

	private IDDLSupport ddl = DBFactory.createDDLSupport();
	
	/**
	 * 创建负债表
	 * @return boolean
	 */
	public boolean createLiabilitiesTable() {
		/*
		 * id,accountid,liabilities,type,settledate,monthpay,paymonth,statu,memo,ver,createtime,updatetime,remark
		 * id,账户id,负债总额,类型0-默认,结算日,月付额,支付月,状态0-待付1-结清
		 * */
		String sql = "CREATE TABLE IF NOT EXISTS liabilities ("
				+"id integer primary key autoincrement,"
				+ "accountid varchar(64),"
				+ "liabilities REAL not null,"
				+ "type integer not null,"
				+ "settledate integer not null,"
				+ "monthpay REAL not null,"
				+ "paymonth integer not null,"
				+ "statu integer not null,"
				+ "memo varchar(255),"
				+ "ver integer,"
				+ "createtime integer,"
				+ "updatetime integer,"
				+ "remark varchar(255)" + ");";

		return ddl.createTable(sql);
	}

	public boolean createLiabilitiesIndex() {
		return ddl.createIndex("account", "idx_acctid", "accountid", "statu", "paymonth");
	}
}
