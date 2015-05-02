package com.ufufund.ufb.model.db;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ufufund.ufb.model.action.PrintableModel;

public class Fdacfinalresult extends PrintableModel  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  serialno;//varchar(24) not null comment '流水号',
	private String  custno;//char(10) not null comment '客户编号',
	private String  apkind;//char(3) not null comment '业务代码',
	private String  workdate;// char(8) not null comment '工作日',
	private String  apdt;//char(8) not null comment '申请日期',
	private String  aptm;// char(6) not null comment '申请时间',
	private String  status="Y";// char(1) not null comment '状态',
	//private String  updatetimestamp;//timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
	private String  toTradeaccoid;// varchar(24) not null comment '交易账号',
	private String  toBankserialid;//varchar(24) not null comment '银行账号',
	private String  tofundCode;//varchar(24) not null comment '银行账号',
	private String  fromTradeaccoid;// varchar(24) not null comment '交易账号',
	private String  fromBankserialid;//varchar(24) not null comment '银行账号',
	private String  fromFundCode;//varchar(24) not null comment '银行账号',
	
	private BigDecimal appamt;//` decimal(16,2) default null comment '申请金额',
	private BigDecimal appvol;//` decimal(16,2) default null comment '申请份额',
	private BigDecimal ackamt;//` decimal(16,2) default null comment '确认金额',
	private BigDecimal ackvol;//` decimal(16,2) default null comment '确认份额',
	private String  fundcorpno;
	
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	
	public String getApkind() {
		return apkind;
	}
	public void setApkind(String apkind) {
		this.apkind = apkind;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	public String getApdt() {
		return apdt;
	}
	public void setApdt(String apdt) {
		this.apdt = apdt;
	}
	public String getAptm() {
		return aptm;
	}
	public void setAptm(String aptm) {
		this.aptm = aptm;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToTradeaccoid() {
		return toTradeaccoid;
	}
	public void setToTradeaccoid(String toTradeaccoid) {
		this.toTradeaccoid = toTradeaccoid;
	}
	public String getToBankserialid() {
		return toBankserialid;
	}
	public void setToBankserialid(String toBankserialid) {
		this.toBankserialid = toBankserialid;
	}
	public String getTofundCode() {
		return tofundCode;
	}
	public void setTofundCode(String tofundCode) {
		this.tofundCode = tofundCode;
	}
	public String getFromTradeaccoid() {
		return fromTradeaccoid;
	}
	public void setFromTradeaccoid(String fromTradeaccoid) {
		this.fromTradeaccoid = fromTradeaccoid;
	}
	public String getFromBankserialid() {
		return fromBankserialid;
	}
	public void setFromBankserialid(String fromBankserialid) {
		this.fromBankserialid = fromBankserialid;
	}
	public String getFromFundCode() {
		return fromFundCode;
	}
	public void setFromFundCode(String fromFundCode) {
		this.fromFundCode = fromFundCode;
	}
	public BigDecimal getAppamt() {
		return appamt;
	}
	public void setAppamt(BigDecimal appamt) {
		this.appamt = appamt;
	}
	public BigDecimal getAppvol() {
		return appvol;
	}
	public void setAppvol(BigDecimal appvol) {
		this.appvol = appvol;
	}
	public BigDecimal getAckamt() {
		return ackamt;
	}
	public void setAckamt(BigDecimal ackamt) {
		this.ackamt = ackamt;
	}
	public BigDecimal getAckvol() {
		return ackvol;
	}
	public void setAckvol(BigDecimal ackvol) {
		this.ackvol = ackvol;
	}
	public String getFundcorpno() {
		return fundcorpno;
	}
	public void setFundcorpno(String fundcorpno) {
		this.fundcorpno = fundcorpno;
	}
	



}
