package com.myliabilities.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myliabilities.dao.accountt.SelectAccountByAccountid;
import com.myliabilities.dao.accountt.UpdateAccountById;
import com.myliabilities.dao.liabilities.DeleteLiabilitiesById;
import com.myliabilities.dao.liabilities.InsertLiabilities;
import com.myliabilities.dao.liabilities.SelectLiabilitiesByAccountid;
import com.myliabilities.dao.liabilities.SelectLiabilitiesByid;
import com.myliabilities.dao.liabilities.SelectLiabilitiesForLeft;
import com.myliabilities.dao.liabilities.UpdateLiabilitiesById;
import com.myliabilities.dao.po.AccountPo;
import com.myliabilities.dao.po.LiabilitiesPo;
import com.myliabilities.db.DBFactory;
import com.myliabilities.db.SQLiteTranscationSupport;
import com.myliabilities.db.infs.IDMLSupport;
import com.myliabilities.enums.LiabilitiesStatusEnum;

/**
 * 债务:
 * 每月债务=债务额/到期月数
 * 每月可用余额=资产-月债务
 * @author LL
 * 2020年5月27日
 */
public class LiabilitiesService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private IDMLSupport ds = DBFactory.createDMLSupport();

    public boolean addLiabilities(Date settledate, BigDecimal monthpay, BigDecimal liabilities, String memo,
            boolean nextMonthStart) {

        AccountPo accountPo = new AccountPo();
        accountPo.setAccountid(NowUser.loginUser);
        SelectAccountByAccountid psc = new SelectAccountByAccountid(accountPo);
        List<AccountPo> list = ds.selectMore(psc, AccountPo.class);
        if (list.isEmpty()) {
            log.error("无用户记录{}!!!", NowUser.loginUser);
            return false;
        } else {
            accountPo = list.get(0);
        }

        Calendar calsettle = Calendar.getInstance();
        calsettle.setTime(settledate);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        //包含结算日月份
        int months = dateSubMonth(cal, calsettle) + 1;

        if (nextMonthStart) {
            //如果是下個月開始計算需要减掉一个月
            months--;
            cal.add(Calendar.MONTH, 1);
        }

        BigDecimal totle = monthpay.multiply(new BigDecimal(months));

        if (liabilities != null && liabilities.compareTo(new BigDecimal(0)) != 0) {
            //没想好怎么处理
        }

        //DB事务执行器
        SQLiteTranscationSupport sts = new SQLiteTranscationSupport();

        AccountPo accountPo2 = new AccountPo();
        accountPo2.setId(accountPo.getId());
        accountPo2.setLiabilities(totle);
        UpdateAccountById ubi = new UpdateAccountById(accountPo2, "liabilities");
        sts.addPSC(ubi);

        //性能优化点
        for (int i = 0; i < months; i++) {
            LiabilitiesPo lp = new LiabilitiesPo();
            lp.setAccountid(NowUser.loginUser);
            lp.setLiabilities(totle);
            lp.setType(0);
            lp.setSettledate(settledate);
            lp.setMonthpay(monthpay);
            cal.add(Calendar.MONTH, i == 0 ? 0 : 1);
            lp.setPaymonth(monthDate(cal));
            lp.setStatu(LiabilitiesStatusEnum.WAIT.getValue());
            lp.setMemo(memo);
            InsertLiabilities il = new InsertLiabilities(lp);
            sts.addPSC(il);
        }

        sts.transcationExecute();

        return true;
    }

    public boolean settledel(Long id) {

        AccountPo accountPo = new AccountPo();
        accountPo.setAccountid(NowUser.loginUser);
        SelectAccountByAccountid psc = new SelectAccountByAccountid(accountPo);
        List<AccountPo> list = ds.selectMore(psc, AccountPo.class);
        if (list.isEmpty()) {
            log.error("无用户记录{}!!!", NowUser.loginUser);
            return false;
        } else {
            accountPo = list.get(0);
        }

        SelectLiabilitiesByid slis = new SelectLiabilitiesByid(id);
        LiabilitiesPo liab = ds.selectOne(slis, LiabilitiesPo.class);
        if (liab == null) {
            log.error("无债务记录id{}!!!", id);
            return false;
        }

        if (LiabilitiesStatusEnum.SETTLE.getValue() == liab.getStatu()) {
            log.error("已经是结算状态id{}!!!", id);
            return false;
        }

        //DB事务执行器
        SQLiteTranscationSupport sts = new SQLiteTranscationSupport();

        AccountPo accountPo2 = new AccountPo();
        accountPo2.setId(accountPo.getId());
        accountPo2.setLiabilities(liab.getMonthpay().multiply(new BigDecimal("-1")));
        UpdateAccountById ubi = new UpdateAccountById(accountPo2, "liabilities");

        DeleteLiabilitiesById dbi = new DeleteLiabilitiesById(id);

        sts.addPSC(ubi);
        sts.addPSC(dbi);

        List<Integer> i = sts.transcationExecute();
        for (Integer tmp : i) {
            if (tmp == 0) {

            }
        }

        return true;

    }

    public boolean settleRecord(Long id) {

        AccountPo accountPo = new AccountPo();
        accountPo.setAccountid(NowUser.loginUser);
        SelectAccountByAccountid psc = new SelectAccountByAccountid(accountPo);
        List<AccountPo> list = ds.selectMore(psc, AccountPo.class);
        if (list.isEmpty()) {
            log.error("无用户记录{}!!!", NowUser.loginUser);
            return false;
        } else {
            accountPo = list.get(0);
        }

        SelectLiabilitiesByid slis = new SelectLiabilitiesByid(id);
        LiabilitiesPo liab = ds.selectOne(slis, LiabilitiesPo.class);
        if (liab == null) {
            log.error("无债务记录id{}!!!", id);
            return false;
        }

        if (LiabilitiesStatusEnum.SETTLE.getValue() == liab.getStatu()) {
            log.error("已经是结算状态id{}!!!", id);
            return false;
        }

        //DB事务执行器
        SQLiteTranscationSupport sts = new SQLiteTranscationSupport();

        AccountPo accountPo2 = new AccountPo();
        accountPo2.setId(accountPo.getId());
        accountPo2.setLiabilities(liab.getMonthpay().multiply(new BigDecimal("-1")));
        accountPo2.setAssets(liab.getMonthpay().multiply(new BigDecimal("-1")));
        //修改债务金额
        UpdateAccountById ubi = new UpdateAccountById(accountPo2, "assets", "liabilities");

        LiabilitiesPo liabilitiesPo = new LiabilitiesPo();
        liabilitiesPo.setId(id);
        liabilitiesPo.setStatu(LiabilitiesStatusEnum.SETTLE.getValue());
        UpdateLiabilitiesById ul = new UpdateLiabilitiesById(liabilitiesPo, "statu");

        sts.addPSC(ubi);
        sts.addPSC(ul);

        List<Integer> i = sts.transcationExecute();
        for (Integer tmp : i) {
            if (tmp == 0) {

            }
        }

        return true;
    }

    public boolean waitpayRecord(Long id) {

        AccountPo accountPo = new AccountPo();
        accountPo.setAccountid(NowUser.loginUser);
        SelectAccountByAccountid psc = new SelectAccountByAccountid(accountPo);
        List<AccountPo> list = ds.selectMore(psc, AccountPo.class);
        if (list.isEmpty()) {
            log.error("无用户记录{}!!!", NowUser.loginUser);
            return false;
        } else {
            accountPo = list.get(0);
        }

        SelectLiabilitiesByid slis = new SelectLiabilitiesByid(id);
        LiabilitiesPo liab = ds.selectOne(slis, LiabilitiesPo.class);
        if (liab == null) {
            log.error("无债务记录id{}!!!", id);
            return false;
        }

        if (LiabilitiesStatusEnum.WAIT.getValue() == liab.getStatu()) {
            log.error("已经是待付状态id{}!!!", id);
            return false;
        }

        //DB事务执行器
        SQLiteTranscationSupport sts = new SQLiteTranscationSupport();

        AccountPo accountPo2 = new AccountPo();
        accountPo2.setId(accountPo.getId());
        accountPo2.setLiabilities(liab.getMonthpay());
        accountPo2.setAssets(liab.getMonthpay());
        //修改债务金额
        UpdateAccountById ubi = new UpdateAccountById(accountPo2, "assets", "liabilities");

        LiabilitiesPo liabilitiesPo = new LiabilitiesPo();
        liabilitiesPo.setId(id);
        liabilitiesPo.setStatu(LiabilitiesStatusEnum.WAIT.getValue());
        UpdateLiabilitiesById ul = new UpdateLiabilitiesById(liabilitiesPo, "statu");

        sts.addPSC(ubi);
        sts.addPSC(ul);

        List<Integer> i = sts.transcationExecute();
        for (Integer tmp : i) {
            if (tmp == 0) {

            }
        }

        return true;
    }

    public List<LiabilitiesPo> getUserData(Integer statu) {

        LiabilitiesPo lp = new LiabilitiesPo();
        lp.setAccountid(NowUser.loginUser);
        lp.setStatu(statu);
        SelectLiabilitiesByAccountid slba = new SelectLiabilitiesByAccountid(lp);
        return ds.selectMore(slba, LiabilitiesPo.class);
    }

    public List<LiabilitiesPo> getUserLiabilities() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        SelectLiabilitiesForLeft slba = new SelectLiabilitiesForLeft(NowUser.loginUser, monthDate(cal));
        return ds.selectMore(slba, LiabilitiesPo.class);
    }

    private int dateSubMonth(Calendar begin, Calendar end) {

        int months = end.get(Calendar.MONTH) - begin.get(Calendar.MONTH);
        int year = end.get(Calendar.YEAR) - begin.get(Calendar.YEAR);
        return Math.abs(year * 12 + months);
    }

    private int monthDate(Calendar calendar) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String d = sdf.format(calendar.getTime());
        return new BigDecimal(d.substring(0, 6)).intValue();
    }
}
