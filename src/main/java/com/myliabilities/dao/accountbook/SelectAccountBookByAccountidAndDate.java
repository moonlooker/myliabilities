package com.myliabilities.dao.accountbook;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.myliabilities.dao.po.AccountBookPo;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.db.utils.SelectTemplate;

public class SelectAccountBookByAccountidAndDate implements IPreparedStatementCreate {

    private Date begin;
    private Date end;
    private String accountid;

    public SelectAccountBookByAccountidAndDate(String accountid, Date begin, Date end) {

        this.accountid = accountid;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void createPreparedStatement(PreparedStatement ps) throws SQLException {

        ps.setString(1, accountid);
        ps.setObject(2, begin.getTime());
        ps.setObject(3, end.getTime());
    }

    public String getSql() {

        String sql = "select " + SelectTemplate.selectResultColumn(AccountBookPo.class)
            + " from accountbook where accountid=? and acountdate >=? and acountdate <=? order by acountdate";
        return sql;
    }

}
