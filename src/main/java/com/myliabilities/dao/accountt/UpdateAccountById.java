package com.myliabilities.dao.accountt;

import java.sql.PreparedStatement;
import java.util.Date;

import com.myliabilities.dao.po.AccountPo;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.utils.MyClassUtils;

public class UpdateAccountById implements IPreparedStatementCreate {

	private AccountPo accountPo;
	private String[] params;

	public UpdateAccountById( AccountPo accountPo, String... strings) {
		this.accountPo = accountPo;
		this.params = strings;
	}

	@Override
	public void createPreparedStatement( PreparedStatement ps) throws Exception {
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i + 1, MyClassUtils.getFieldValue(params[i], accountPo, accountPo.getClass()));
		}
		ps.setObject(params.length + 1, new Date().getTime());
		ps.setObject(params.length + 2, accountPo.getId());
	}

	@Override
	public String getSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("update account set ");
		for (int i = 0; i < params.length; i++) {
			if ("assets".equals(params[i]) || "liabilities".equals(params[i]) || "hope".equals(params[i])) {
				sb.append(params[i]).append("=").append(params[i]).append("+").append("?");
			} else {
				sb.append(params[i]).append("=").append("?");
			}
			sb.append(",");
		}
		sb.append("updatetime=?");
		sb.append("where id = ?");
		return sb.toString();
	}

}
