package com.myliabilities.dao.po;

import java.math.BigDecimal;
import java.util.Date;

import com.myliabilities.utils.MyClassUtils;

public class LiabilitiesPo {
	
	private Long id;
	//用户id
	private String accountid;
	//负债
	private BigDecimal liabilities = new BigDecimal("0");
	//类型0-默认
	private Integer type;
	//结算日,债务到期日
	private Date settledate;
	//每月支付金额
	private BigDecimal monthpay = new BigDecimal("0");
	//支付月
	private Integer paymonth;;
	//状态0-待付,1-结清
	private Integer statu;
	private String memo;
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
	public BigDecimal getLiabilities() {
		return liabilities;
	}
	public void setLiabilities( BigDecimal liabilities) {
		this.liabilities = liabilities;
	}
	public Integer getType() {
		return type;
	}
	public void setType( Integer type) {
		this.type = type;
	}
	public Date getSettledate() {
		return settledate;
	}
	public void setSettledate( Date settledate) {
		this.settledate = settledate;
	}
	public BigDecimal getMonthpay() {
		return monthpay;
	}
	public void setMonthpay( BigDecimal monthpay) {
		this.monthpay = monthpay;
	}
	public Integer getStatu() {
		return statu;
	}
	public void setStatu( Integer statu) {
		this.statu = statu;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo( String memo) {
		this.memo = memo;
	}
	public Integer getPaymonth() {
		return paymonth;
	}
	public void setPaymonth( Integer paymonth) {
		this.paymonth = paymonth;
	}
}
