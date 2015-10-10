package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class Bankcardinfo extends PrintableModel  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serialid;// ;//int(11) not null comment '银行卡id',
	private String custno;// char(10) not null default '0' comment '直销平台用户id',
	//private String fundcorpno;// char(2) default '' comment '基金公司标识',
	private String bankno;// char(3) not null comment '银行编码',
	private String bankacco;// varchar(28) not null comment '银行卡号',
	private String bankaccodisplay;// varchar(28) not null comment '银行提示账号',
	private String bankidtp;// char(1) default null comment '银行证件类型',
	private String bankidno;// varchar(30) default null comment '银行证件号码',
	private String bankidvaliddt;// char(8) default null comment '证件有效期',
	private String bankacnm;// varchar(60) default null comment '银行开户户名',
	private String subbankno;// char(3) default null comment '银行编号',
	private String banklongname;// varchar(60) default null comment '银行开户分行名称',
	private String issign;// char(1) default null comment '是否已签约：y是；n否',
	private String signno;// varchar(40) default null comment '银行签约协议号',
	private String bankproexists;// char(2) default null comment '定期定额银行协议是否签订
									// 00:未签订 01:已签订',
	private String protocol_no;// varchar(100) default null comment '定期定额银行协议号码
								// ',
	private String state="N";// char(1) default null comment '银行卡（基金交易账户）状态：y 正常；n
							// 已解绑',
	private String opendt;// char(8) default null comment '绑卡日期',
	private String closedt;// char(8) default null comment '解绑日期',
	private String disorder;// int(11) default null comment '展示顺序：用户多张卡时的展示顺序',
	private String updatetimestamp;// timestamp not null default
									// current_timestamp on u
	
	private String bankmobile;
	private String bankprovincenm;
	private String bankcitynm;
	private String bankadd; // 支行网点
	
	public String getBankprovincenm() {
		return bankprovincenm;
	}

	public void setBankprovincenm(String bankprovincenm) {
		this.bankprovincenm = bankprovincenm;
	}

	public String getBankcitynm() {
		return bankcitynm;
	}

	public void setBankcitynm(String bankcitynm) {
		this.bankcitynm = bankcitynm;
	}

	public String getBankadd() {
		return bankadd;
	}

	public void setBankadd(String bankadd) {
		this.bankadd = bankadd;
	}
	public String getBankmobile() {
		return bankmobile;
	}
	public void setBankmobile(String bankmobile) {
		this.bankmobile = bankmobile;
	}
	public String getSerialid() {
		return serialid;
	}
	public void setSerialid(String serialid) {
		this.serialid = serialid;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
//	public String getFundcorpno() {
//		return fundcorpno;
//	}
//	public void setFundcorpno(String fundcorpno) {
//		this.fundcorpno = fundcorpno;
//	}
	public String getBankno() {
		return bankno;
	}
	public void setBankno(String bankno) {
		this.bankno = bankno;
	}
	public String getBankacco() {
		return bankacco;
	}
	public void setBankacco(String bankacco) {
		this.bankacco = bankacco;
	}
	public String getBankaccodisplay() {
		return bankaccodisplay;
	}
	public void setBankaccodisplay(String bankaccodisplay) {
		this.bankaccodisplay = bankaccodisplay;
	}
	public String getBankidtp() {
		return bankidtp;
	}
	public void setBankidtp(String bankidtp) {
		this.bankidtp = bankidtp;
	}
	public String getBankidno() {
		return bankidno;
	}
	public void setBankidno(String bankidno) {
		this.bankidno = bankidno;
	}
	public String getBankidvaliddt() {
		return bankidvaliddt;
	}
	public void setBankidvaliddt(String bankidvaliddt) {
		this.bankidvaliddt = bankidvaliddt;
	}
	public String getBankacnm() {
		return bankacnm;
	}
	public void setBankacnm(String bankacnm) {
		this.bankacnm = bankacnm;
	}
	public String getSubbankno() {
		return subbankno;
	}
	public void setSubbankno(String subbankno) {
		this.subbankno = subbankno;
	}
	public String getBanklongname() {
		return banklongname;
	}
	public void setBanklongname(String banklongname) {
		this.banklongname = banklongname;
	}
	public String getIssign() {
		return issign;
	}
	public void setIssign(String issign) {
		this.issign = issign;
	}
	public String getSignno() {
		return signno;
	}
	public void setSignno(String signno) {
		this.signno = signno;
	}
	public String getBankproexists() {
		return bankproexists;
	}
	public void setBankproexists(String bankproexists) {
		this.bankproexists = bankproexists;
	}
	public String getProtocol_no() {
		return protocol_no;
	}
	public void setProtocol_no(String protocol_no) {
		this.protocol_no = protocol_no;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOpendt() {
		return opendt;
	}
	public void setOpendt(String opendt) {
		this.opendt = opendt;
	}
	public String getClosedt() {
		return closedt;
	}
	public void setClosedt(String closedt) {
		this.closedt = closedt;
	}
	public String getDisorder() {
		return disorder;
	}
	public void setDisorder(String disorder) {
		this.disorder = disorder;
	}
	public String getUpdatetimestamp() {
		return updatetimestamp;
	}
	public void setUpdatetimestamp(String updatetimestamp) {
		this.updatetimestamp = updatetimestamp;
	}
	
	
}
