package com.myliabilities.dao.liabilities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.myliabilities.dao.po.LiabilitiesPo;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.db.utils.SelectTemplate;

public class SelectLiabilitiesByAccountid implements IPreparedStatementCreate {

    private LiabilitiesPo lp;

    public SelectLiabilitiesByAccountid(LiabilitiesPo lp) {

        this.lp = lp;
    }

    @Override
    public void createPreparedStatement(PreparedStatement ps) throws SQLException {

        ps.setString(1, lp.getAccountid());
        if (lp.getStatu() != null) {
            ps.setObject(2, lp.getStatu());
        }
    }

    public String getSql() {

        StringBuilder sb = new StringBuilder();
        sb.append("select ").append(SelectTemplate.selectResultColumn(LiabilitiesPo.class))
            .append(" from liabilities where accountid=?");
        if (lp.getStatu() != null) {
            sb.append("and statu = ?");
        }
        sb.append(" order by paymonth");
        return sb.toString();
    }

}
