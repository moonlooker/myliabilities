package com.myliabilities.dao.liabilities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.myliabilities.dao.po.LiabilitiesPo;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.db.utils.SelectTemplate;

public class SelectLiabilitiesByid implements IPreparedStatementCreate {

    private long lp;

    public SelectLiabilitiesByid(long id) {

        this.lp = id;
    }

    @Override
    public void createPreparedStatement(PreparedStatement ps) throws SQLException {

        ps.setObject(1, lp);
    }

    public String getSql() {

        StringBuilder sb = new StringBuilder();
        sb.append("select ").append(SelectTemplate.selectResultColumn(LiabilitiesPo.class))
            .append(" from liabilities where id=?");
        return sb.toString();
    }

}
