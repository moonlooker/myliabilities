package com.myliabilities.dao.po;

import java.math.BigDecimal;
import java.util.Date;

import com.myliabilities.utils.MyClassUtils;

public class AccountBookPo {

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
	private Integer dc;
	//记账日
	private Date acountdate;
	//摘要
	private String memo;
	//类型id,一类负债/希望标识
	private Integer typeid;
	private Integer ver;
	private Date createtime;
	private Date updatetime;
	private String remark;

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

	public Integer getDc() {
		return dc;
	}

	public void setDc( Integer dc) {
		this.dc = dc;
	}

	public Date getAcountdate() {
		return acountdate;
	}

	public void setAcountdate( Date acountdate) {
		this.acountdate = acountdate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo( String memo) {
		this.memo = memo;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer( Integer ver) {
		this.ver = ver;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime( Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime( Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark( String remark) {
		this.remark = remark;
	}

	public String toString() {
		String tmp = null;
		try {
			tmp = MyClassUtils.objectToString(this, this.getClass());
		} catch ( Exception e) {
			tmp = e.getMessage();
		}
		return tmp;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid( Integer typeid) {
		this.typeid = typeid;
	}
}
