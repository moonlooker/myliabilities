package com.myliabilities.dao.accountt;

import java.sql.PreparedStatement;

import com.myliabilities.dao.po.AccountPo;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.db.utils.InsertTemplate;

/**
 * 插入账户数据
 * 
 * @author Pactera
 * 2020年5月24日
 */
public class InsertAccount implements IPreparedStatementCreate {

	private AccountPo accountPo;
	//	private String sql = "insert into account(id,accountid,accountname,assets,liabilities,ver,createtime,updatetime,remark) values (?,?,?,?,?,?,?,?,?)";
	private String tableName = "account";
	private String[] columns = { "id", "accountid", "accountname", "assets", "hope", "liabilities", "ver", "createtime",
			"updatetime", "remark" };

	public InsertAccount( AccountPo accountPo) {
		this.accountPo = accountPo;
	}

	public InsertAccount( AccountPo accountPo, String tableName, String... columns) {
		this.accountPo = accountPo;
		this.tableName = tableName;
		this.columns = columns;
	}

	@Override
	public void createPreparedStatement( PreparedStatement ps) throws Exception {

		InsertTemplate.createPreparedStatement(ps, accountPo, AccountPo.class, columns);
		//		ps.setObject(1, null);
		//		ps.setString(2, accountPo.getAccountid());
		//		ps.setString(3, accountPo.getAccountname());
		//		ps.setBigDecimal(4, accountPo.getAssets());
		//		ps.setBigDecimal(5, accountPo.getLiabilities());
		//		ps.setInt(6, accountPo.getVer());
		//		ps.setObject(7, System.currentTimeMillis());
		//		ps.setObject(8, System.currentTimeMillis());
		//		ps.setString(9, "");
	}

	public AccountPo getAccountPo() {
		return accountPo;
	}

	public String getSql() {
		return InsertTemplate.createInsertSQL(tableName, columns);
	}

}
