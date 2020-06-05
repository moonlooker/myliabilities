package com.myliabilities.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myliabilities.dao.accountt.InsertAccount;
import com.myliabilities.dao.accountt.SelectAccountByAccountid;
import com.myliabilities.dao.po.AccountPo;
import com.myliabilities.db.DBFactory;
import com.myliabilities.db.infs.IDMLSupport;

public class UserService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private IDMLSupport ds = DBFactory.createDMLSupport();

	/**
	 * 登录,并确定使用用户
	 * @param accountid
	 * @return true成功,false失败
	 */
	public synchronized boolean login( String accountid) {
		if (accountid == null || "".equals(accountid)) {
			log.error("缺少用户ID!!");
			return false;
		}
		AccountPo accountPo = new AccountPo();
		accountPo.setAccountid(accountid);
		SelectAccountByAccountid psc = new SelectAccountByAccountid(accountPo);
		List<AccountPo> list = ds.selectMore( psc, AccountPo.class);
		if (list.isEmpty()) {
			return false;
		} else {
			NowUser.loginUser = accountid;
			return true;
		}
	}

	/**
	 * 注册
	 * @param accountid 用户id
	 * @param accountName 别名
	 * @return true成功,false失败
	 */
	public synchronized boolean register( String accountid, String accountName) {
		if (accountid == null || "".equals(accountid)) {
			log.error("缺少用户ID!!");
			return false;
		}
		AccountPo accountPo = new AccountPo();
		accountPo.setAccountid(accountid);
		SelectAccountByAccountid psc = new SelectAccountByAccountid(accountPo);
		List<AccountPo> list = ds.selectMore(psc, AccountPo.class);
		if (list.isEmpty()) {
			accountPo.setAccountname(accountName == null || "".equals(accountName) ? "newuser" : accountName);
			InsertAccount ia = new InsertAccount(accountPo);
			ds.insert(ia);
			NowUser.loginUser = accountid;
			return true;
		} else {
			log.warn("用户[{}]已存在!", accountid);
			return false;
		}

	}

	public AccountPo getAcount( String accountid) {
		AccountPo accountPo = new AccountPo();
		accountPo.setAccountid(accountid);
		SelectAccountByAccountid psc = new SelectAccountByAccountid(accountPo);
		List<AccountPo> list = ds.selectMore(psc, AccountPo.class);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
