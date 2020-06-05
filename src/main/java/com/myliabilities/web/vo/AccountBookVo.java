package com.myliabilities.web.vo;

import java.math.BigDecimal;

public class AccountBookVo {

	private Long id;
	//用户id
	private String accountid;
	//标签
	private String tag;
	//标签名
	private String tagname;
	//金额
	private BigDecimal amount;
	//出入标识0-入,1-出
	private String dc;
	//记账日
	private String acountdate;
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId( Long id) {
		this.id = id;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid( String accountid) {
		this.accountid = accountid;
	}

	public String getTag() {
		return tag;
	}

	public void setTag( String tag) {
		this.tag = tag;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname( String tagname) {
		this.tagname = tagname;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount( BigDecimal amount) {
		this.amount = amount;
	}

	public String getDc() {
		return dc;
	}

	public void setDc( String dc) {
		this.dc = dc;
	}

	public String getAcountdate() {
		return acountdate;
	}

	public void setAcountdate( String acountdate) {
		this.acountdate = acountdate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo( String memo) {
		this.memo = memo;
	}

}
