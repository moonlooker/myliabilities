package com.myliabilities.dao.accountbook;

import java.sql.PreparedStatement;

import com.myliabilities.dao.po.AccountBookPo;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.db.utils.InsertTemplate;

/**
 * 插入账本数据
 * 
 * @author LL
 * 2020年5月24日
 */
public class InsertAccountBook implements IPreparedStatementCreate {

	private AccountBookPo accountBookPo;
	private String tableName = "accountbook";
	private String[] columns = { "id", "accountid", "tag", "tagname", "amount", "dc", "acountdate", "memo", "typeid",
			"createtime", "updatetime", "remark" };

	public InsertAccountBook( AccountBookPo accountBookPo) {
		this.accountBookPo = accountBookPo;
	}

	public InsertAccountBook( AccountBookPo accountBookPo, String tableName, String... columns) {
		this.accountBookPo = accountBookPo;
		this.tableName = tableName;
		this.columns = columns;
	}

	@Override
	public void createPreparedStatement( PreparedStatement ps) throws Exception {
		InsertTemplate.createPreparedStatement(ps, accountBookPo, AccountBookPo.class, columns);
	}

	public AccountBookPo getAccountPo() {
		return accountBookPo;
	}

	public String getSql() {
		return InsertTemplate.createInsertSQL(tableName, columns);
	}

}
