package com.myliabilities.dao.liabilities;

import java.sql.PreparedStatement;
import java.util.Date;

import com.myliabilities.dao.po.LiabilitiesPo;
import com.myliabilities.db.infs.IPreparedStatementCreate;
import com.myliabilities.utils.MyClassUtils;

public class UpdateLiabilitiesById implements IPreparedStatementCreate {

    private LiabilitiesPo liabilitiesPo;
    private String[] params;

    public UpdateLiabilitiesById(LiabilitiesPo liabilitiesPo, String... strings) {

        this.liabilitiesPo = liabilitiesPo;
        this.params = strings;
    }

    @Override
    public void createPreparedStatement(PreparedStatement ps) throws Exception {

        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, MyClassUtils.getFieldValue(params[i], liabilitiesPo, liabilitiesPo.getClass()));
        }
        ps.setObject(params.length + 1, new Date().getTime());
        ps.setObject(params.length + 2, liabilitiesPo.getId());
    }

    @Override
    public String getSql() {

        StringBuilder sb = new StringBuilder();
        sb.append("update liabilities set ");
        for (int i = 0; i < params.length; i++) {
            sb.append(params[i]).append("=").append("?");
            sb.append(",");
        }
        sb.append("updatetime=?");
        sb.append("where id = ?");
        return sb.toString();
    }

}
