package com.myliabilities.dao.po;

import java.math.BigDecimal;
import java.util.Date;

import com.myliabilities.utils.MyClassUtils;

public class AccountPo {

	private Long id;
	//用户id
	private String accountid;
	//用户名
	private String accountname;
	//资产
	private BigDecimal assets = new BigDecimal("0");
	//预期
	private BigDecimal hope = new BigDecimal("0");
	//负债
	private BigDecimal liabilities = new BigDecimal("0");
	private Integer ver;
	private Date createtime;
	private Date updatetime;
	private String remark;
	
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid( String accountid) {
		this.accountid = accountid;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname( String accountname) {
		this.accountname = accountname;
	}
	public BigDecimal getAssets() {
		return assets;
	}
	public void setAssets( BigDecimal assets) {
		this.assets = assets;
	}
	public BigDecimal getLiabilities() {
		return liabilities;
	}
	public void setLiabilities( BigDecimal liabilities) {
		this.liabilities = liabilities;
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
	public Long getId() {
		return id;
	}
	public void setId( Long id) {
		this.id = id;
	}
	
	public String toString() {
		String tmp = null;
		try {
			tmp = MyClassUtils.objectToString(this, this.getClass());
		}catch(Exception e) {
			tmp = e.getMessage();
		}
		return tmp;
	}
	public BigDecimal getHope() {
		return hope;
	}
	public void setHope( BigDecimal hope) {
		this.hope = hope;
	}
}
