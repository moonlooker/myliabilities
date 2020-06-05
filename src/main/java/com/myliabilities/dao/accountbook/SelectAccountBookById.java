package com.myliabilities.dao.accountbook;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.myliabilities.dao.po.AccountBookPo;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.db.utils.SelectTemplate;

public class SelectAccountBookById implements IPreparedStatementCreate {

    private long id;

    public SelectAccountBookById(long id) {

        this.id = id;
    }

    @Override
    public void createPreparedStatement(PreparedStatement ps) throws SQLException {

        ps.setObject(1, id);
    }

    public String getSql() {

        String sql = "select " + SelectTemplate.selectResultColumn(AccountBookPo.class)
            + " from accountbook where id=?";
        return sql;
    }

}
