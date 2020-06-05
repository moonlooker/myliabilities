package com.myliabilities.db;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.myliabilities.dao.accountbook.InsertAccountBook;
import com.myliabilities.dao.accountbook.SelectAccountBookByAccountidAndDate;
import com.myliabilities.dao.accountt.SelectAccountByAccountid;
import com.myliabilities.dao.accountt.UpdateAccountById;
import com.myliabilities.dao.po.AccountBookPo;
import com.myliabilities.dao.po.AccountPo;
import com.myliabilities.db.infs.IDMLSupport;
import com.myliabilities.enums.DC;
import com.myliabilities.utils.MyDateUtils;

public class SQLiteJDBCTest {

	public static void main( String args[]) {

		//创建表
		//		CreateLiabilitiesTable cbt = new CreateLiabilitiesTable();
		//		cbt.createAccountTable();
		//		cbt.createAccountIndex();
		//		CreateAccountbookTable cabt = new CreateAccountbookTable();
		//		cabt.createAccountTable();
		//		cabt.createAccountIndex();

		IDMLSupport ds = DBFactory.createDMLSupport();

		AccountPo accountPo = new AccountPo();
		accountPo.setAccountid("123");
		accountPo.setAccountname("myaccount");
		accountPo.setAssets(null);
		accountPo.setLiabilities(null);
		accountPo.setVer(0);
		//插入账户数据
		//		InsertAccount ia = new InsertAccount(accountPo);
		//		ds.insert(ia.getSql(), ia);

		//查询账户数据
		SelectAccountByAccountid psc = new SelectAccountByAccountid(accountPo);
		List<AccountPo> list = ds.selectMore(psc, AccountPo.class);
		for (AccountPo a : list) {
			System.out.println(a.toString());
		}

		BigDecimal in = new BigDecimal("10");
		BigDecimal out = new BigDecimal("5");
		AccountPo accountPo3 = list.get(0);
		AccountBookPo accountBookPo = new AccountBookPo();
		accountBookPo.setAccountid(accountPo3.getAccountid());
		accountBookPo.setTag("A");
		accountBookPo.setTagname("收入");
		accountBookPo.setAcountdate(new Date());
		accountBookPo.setAmount(in);
		accountBookPo.setDc(DC.INCOME.ordinal());
		accountBookPo.setMemo("测试");
		//插入记账明细
		InsertAccountBook iab = new InsertAccountBook(accountBookPo);
		//		ds.insert(iab.getSql(), iab);

		SQLiteTranscationSupport sts = new SQLiteTranscationSupport();

		AccountPo accountPo2 = new AccountPo();
		accountPo2.setId(list.get(0).getId());
		accountPo2.setAssets(in);
		accountPo2.setLiabilities(out);

		//修改账户金额
		UpdateAccountById ubi = new UpdateAccountById(accountPo2, "assets", "liabilities");
		//		int upcount = ds.update(ubi.getSql(), ubi);
		//		System.out.println("updatecount : " + upcount);

		sts.addPSC(ubi);
		sts.addPSC(iab);

//		sts.transcationExecute();

		List<AccountPo> list2 = ds.selectMore(psc, AccountPo.class);
		for (AccountPo a : list2) {
			System.out.println(a.toString());
		}

		//查询记账明细
		Date[] d = MyDateUtils.thisMonthBeginEnd();
		SelectAccountBookByAccountidAndDate asbb = new SelectAccountBookByAccountidAndDate(accountPo3.getAccountid(),
				d[0], d[1]);
		List<AccountBookPo> books = ds.selectMore(asbb, AccountBookPo.class);

		for (AccountBookPo a : books) {
			System.out.println(a.toString());
			//删除一条记账明细
			//					DeleteLiabilitiesById dabb = new DeleteLiabilitiesById(a.getId());
			//					ds.delete(dabb.getSql(), dabb);
		}
	}
}
