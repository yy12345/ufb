package com.ufufund.ufb.model.db;

public class Accorequest {
	private String  serialno;// varchar(24) not null comment '流水号',
	private String  custno;//char(10) not null comment '客户编号',
	private String  fundcorpno;// char(2) not null comment '交易账号类型：归属基金公司',
	private String  invtp;// char(1) not null comment '投资人类型 0：机构 1：个人',
	private String  invnm;// varchar(120) not null comment '投资人姓名',
	private String  idtp;// char(1) not null comment '证件类型',
	private String  idno;// varchar(30) not null comment '证件号码',
	private String  telno;// varchar(24) default null comment '固定电话',
	private String  mobileno;// varchar(24) not null comment '手机号',
	private String  email;//varchar(40) default null comment '电子信箱',
	private String  faxno;// varchar(24) default null comment '传真号',
	private String  postcode;// varchar(24) default null comment '邮政编码',
	private String  addr;// varchar(120) default null comment '地址',
	private String  melonmd;// varchar(1) default null comment '分红方式',
	private String  bankno;//char(3) not null comment '银行代码',
	private String  banklongname;// varchar(60) not null comment '银行全称',
	private String  bankacnm;// varchar(60) not null comment '银行户名',
	private String  bankacco;// varchar(28) not null comment '银行账号',
	private String  apdt;// char(8) not null comment '申请日期',
	private String  aptm;//char(6) not null comment '申请时间',
	private String  birthdary;// varchar(8) default null comment '生日',
	private String  provincecode;// varchar(2) default null comment '省份代码',
	private String  cityname;// varchar(50) default null comment '城市名称',
	private String  workdate;// char(8) not null comment '工作日',
	//private String  updatetimestamp` timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
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
	public String getFundcorpno() {
		return fundcorpno;
	}
	public void setFundcorpno(String fundcorpno) {
		this.fundcorpno = fundcorpno;
	}
	public String getInvtp() {
		return invtp;
	}
	public void setInvtp(String invtp) {
		this.invtp = invtp;
	}
	public String getInvnm() {
		return invnm;
	}
	public void setInvnm(String invnm) {
		this.invnm = invnm;
	}
	public String getIdtp() {
		return idtp;
	}
	public void setIdtp(String idtp) {
		this.idtp = idtp;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFaxno() {
		return faxno;
	}
	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getMelonmd() {
		return melonmd;
	}
	public void setMelonmd(String melonmd) {
		this.melonmd = melonmd;
	}
	public String getBankno() {
		return bankno;
	}
	public void setBankno(String bankno) {
		this.bankno = bankno;
	}
	public String getBanklongname() {
		return banklongname;
	}
	public void setBanklongname(String banklongname) {
		this.banklongname = banklongname;
	}
	public String getBankacnm() {
		return bankacnm;
	}
	public void setBankacnm(String bankacnm) {
		this.bankacnm = bankacnm;
	}
	public String getBankacco() {
		return bankacco;
	}
	public void setBankacco(String bankacco) {
		this.bankacco = bankacco;
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
	public String getBirthdary() {
		return birthdary;
	}
	public void setBirthdary(String birthdary) {
		this.birthdary = birthdary;
	}
	public String getProvincecode() {
		return provincecode;
	}
	public void setProvincecode(String provincecode) {
		this.provincecode = provincecode;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}



}
