package com.myliabilities.web.vo;

import java.math.BigDecimal;

public class LiabilitiesVo {

	private Long id;
	//用户id
	private String accountid;

	//负债
	private BigDecimal liabilities;
	//类型0-默认
	private String type;
	//结算日,债务到期日
	private String settledate;
	//每月支付金额
	private BigDecimal monthpay;
	//支付月
	private Integer paymonth;;
	//状态0-待付,1-结清
	private String statu;
	private int statuv;
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
	public BigDecimal getLiabilities() {
		return liabilities;
	}
	public void setLiabilities( BigDecimal liabilities) {
		this.liabilities = liabilities;
	}
	public String getType() {
		return type;
	}
	public void setType( String type) {
		this.type = type;
	}
	public String getSettledate() {
		return settledate;
	}
	public void setSettledate( String settledate) {
		this.settledate = settledate;
	}
	public BigDecimal getMonthpay() {
		return monthpay;
	}
	public void setMonthpay( BigDecimal monthpay) {
		this.monthpay = monthpay;
	}
	public Integer getPaymonth() {
		return paymonth;
	}
	public void setPaymonth( Integer paymonth) {
		this.paymonth = paymonth;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu( String statu) {
		this.statu = statu;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo( String memo) {
		this.memo = memo;
	}
    /**
     * @return the statuv
     */
    public int getStatuv() {

        return statuv;
    }
    /**
     * @param statuv the statuv to set
     */
    public void setStatuv(int statuv) {

        this.statuv = statuv;
    }

}
