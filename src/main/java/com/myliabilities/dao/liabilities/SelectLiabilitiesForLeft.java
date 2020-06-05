package com.myliabilities.dao.liabilities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.myliabilities.dao.po.LiabilitiesPo;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.db.utils.SelectTemplate;

public class SelectLiabilitiesForLeft implements IPreparedStatementCreate {

    private String accountid;
    private int paymonth;

    public SelectLiabilitiesForLeft(String accountid , int paymonth) {

        this.accountid = accountid;
        this.paymonth = paymonth;
    }

    @Override
    public void createPreparedStatement(PreparedStatement ps) throws SQLException {

        ps.setObject(1, accountid);
        ps.setObject(2, paymonth);
    }

    public String getSql() {

        StringBuilder sb = new StringBuilder();
        sb.append("select ").append(SelectTemplate.selectResultColumn(LiabilitiesPo.class))
            .append(" from liabilities where accountid=? and statu = 0 and paymonth <=?");
        return sb.toString();
    }

}
