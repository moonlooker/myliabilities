package com.myliabilities.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myliabilities.dao.accountbook.DeleteAccountBookById;
import com.myliabilities.dao.accountbook.InsertAccountBook;
import com.myliabilities.dao.accountbook.SelectAccountBookByAccountidAndDate;
import com.myliabilities.dao.accountbook.SelectAccountBookById;
import com.myliabilities.dao.accountt.SelectAccountByAccountid;
import com.myliabilities.dao.accountt.UpdateAccountById;
import com.myliabilities.dao.po.AccountBookPo;
import com.myliabilities.dao.po.AccountPo;
import com.myliabilities.db.DBFactory;
import com.myliabilities.db.SQLiteTranscationSupport;
import com.myliabilities.db.infs.IDMLSupport;
import com.myliabilities.enums.DC;
import com.myliabilities.utils.MyDateUtils;

public class AccountBookService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private IDMLSupport ds = DBFactory.createDMLSupport();

	public boolean addBook( DC dc, BigDecimal amount, Date accountDate, String tag, String tagName, String memo) {
		
		if (dc == null) {
			log.error("收支方向不能空");
			return false;
		} else if (amount == null) {
			log.error("金额不能空");
			return false;
		} else if (accountDate == null) {
			log.error("记账日不能空");
			return false;
		} else if (tag == null || "".equals(tag)) {
			log.error("标签不能空");
			return false;
		}
		
		AccountPo accountPo = new AccountPo();
		accountPo.setAccountid(NowUser.loginUser);
		SelectAccountByAccountid psc = new SelectAccountByAccountid(accountPo);
		List<AccountPo> list = ds.selectMore(psc, AccountPo.class);
		if (list.isEmpty()) {
			log.error("无用户记录{}!!!", NowUser.loginUser);
			return false;
		}else {
			accountPo = list.get(0);
		}
		
		//DB事务执行器
		SQLiteTranscationSupport sts = new SQLiteTranscationSupport();

		AccountBookPo accountBookPo = new AccountBookPo();
		accountBookPo.setAccountid(NowUser.loginUser);
		accountBookPo.setTag(tag);
		accountBookPo.setTagname(tagName);
		accountBookPo.setAcountdate(accountDate);
		accountBookPo.setAmount(amount);
		accountBookPo.setDc(dc.getValue());
		accountBookPo.setMemo(memo);
		InsertAccountBook iab = new InsertAccountBook(accountBookPo);
		sts.addPSC(iab);

		AccountPo accountPo2 = new AccountPo();
		accountPo2.setId(accountPo.getId());
		if(DC.INCOME.equals(dc)) {
			accountPo2.setAssets(amount);
		}else if(DC.DRAW.equals(dc)){
			accountPo2.setAssets(amount.multiply(new BigDecimal("-1")));
			
		}
		
		//修改账户金额
		UpdateAccountById ubi = new UpdateAccountById(accountPo2, "assets");
		sts.addPSC(ubi);
		//执行
		sts.transcationExecute();
		
		return true;
	}

	public boolean delBook( Long id) {
		if (id == null) {
			log.error("ID不能空");
			return false;
		}
		
		AccountPo accountPo = new AccountPo();
        accountPo.setAccountid(NowUser.loginUser);
        SelectAccountByAccountid psc = new SelectAccountByAccountid(accountPo);
        List<AccountPo> list = ds.selectMore(psc, AccountPo.class);
        if (list.isEmpty()) {
            log.error("无用户记录{}!!!", NowUser.loginUser);
            return false;
        }else {
            accountPo = list.get(0);
        }
        
        SelectAccountBookById sabb = new SelectAccountBookById(id);
        AccountBookPo abp = ds.selectOne(sabb, AccountBookPo.class);
        if(abp == null) {
            log.error("无账务记录{}!!!", id);
            return false;
        }
        
      //DB事务执行器
        SQLiteTranscationSupport sts = new SQLiteTranscationSupport();
        
        AccountPo accountPo2 = new AccountPo();
        accountPo2.setId(accountPo.getId());
        if(DC.INCOME.getValue() == abp.getDc()) {
            accountPo2.setAssets(abp.getAmount().multiply(new BigDecimal("-1")));
        }else if(DC.DRAW.getValue() == abp.getDc()){
            accountPo2.setAssets(abp.getAmount());
            
        }
        
        //修改账户金额
        UpdateAccountById ubi = new UpdateAccountById(accountPo2, "assets");
        sts.addPSC(ubi);

		DeleteAccountBookById dabb = new DeleteAccountBookById(id);
		sts.addPSC(dabb);
		
		sts.transcationExecute();
		
		return true;
	}

	public List<AccountBookPo> monthBooks() {
		Date[] d = MyDateUtils.thisMonthBeginEnd();
		SelectAccountBookByAccountidAndDate asbb = new SelectAccountBookByAccountidAndDate(NowUser.loginUser, d[0], d[1]);
		List<AccountBookPo> books = ds.selectMore(asbb, AccountBookPo.class);
		return books;
	}
	
	public List<AccountBookPo> monthBooks(Date assginDate) {
		Date[] d = MyDateUtils.monthBeginEnd(assginDate);
		SelectAccountBookByAccountidAndDate asbb = new SelectAccountBookByAccountidAndDate(NowUser.loginUser, d[0], d[1]);
		List<AccountBookPo> books = ds.selectMore(asbb, AccountBookPo.class);
		return books;
	}
	
	public List<AccountBookPo> monthBooks(Date begindate, Date enddate) {
        SelectAccountBookByAccountidAndDate asbb = new SelectAccountBookByAccountidAndDate(NowUser.loginUser, begindate, enddate);
        List<AccountBookPo> books = ds.selectMore(asbb, AccountBookPo.class);
        return books;
    }
}
