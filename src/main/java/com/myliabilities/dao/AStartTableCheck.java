package com.myliabilities.dao;

import com.myliabilities.dao.accountbook.CreateAccountbookTable;
import com.myliabilities.dao.accountt.CreateAccountTable;
import com.myliabilities.dao.liabilities.CreateLiabilitiesTable;

/**
 * 应用启动,如果没有表就创建一个
 * @author LL
 * 2020年5月25日
 */
public class AStartTableCheck {

	static {
		CreateAccountTable cbt = new CreateAccountTable();
		cbt.createAccountTable();
		cbt.createAccountIndex();
		CreateAccountbookTable cabt = new CreateAccountbookTable();
		cabt.createAccountTable();
		cabt.createAccountIndex();
		CreateLiabilitiesTable clt = new CreateLiabilitiesTable();
		clt.createLiabilitiesTable();
		clt.createLiabilitiesIndex();
	}
}
