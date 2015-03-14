package com.ufufund.ufb.model.model;

import java.io.Serializable;

/**
 * 用户信息表dto
 * @author ayis
 *
 */
public class Custinfo implements Serializable{
	
	private static final long serialVersionUID = -6338092203128112153L;

	
	
	private String custno;//用户id',
	private String invtp;//用户类型：0个人用户；1机构用户',
	private String invnm;//用户姓名',
	private String sex;//性别:0 男；1 女',
	private String idtp;//0-身份证 1-护照 2-军官证 3-士兵证 4-港澳 5-户口本 6-外国护照 7-其它 8-文职证 9-警官证 a-台胞证',
	private String idno;//证件号码',
	private String mobileno;//手机号',
	private String email;//邮箱',
	private String custst;//账户状态：y正常；n 已注销',
	private String passwd;//登陆密码，md5密文',
	private String lastlogintime;//上次登录时间',
	private int passwderr;//密码错误次数',
	private String opendt;//开户日期',
	private String updatetimestamp;//更新时间',
	private String tradepwd;//交易密码，md5密文',
	
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getCustst() {
		return custst;
	}
	public void setCustst(String custst) {
		this.custst = custst;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	
	public String getOpendt() {
		return opendt;
	}
	public void setOpendt(String opendt) {
		this.opendt = opendt;
	}
	public String getUpdatetimestamp() {
		return updatetimestamp;
	}
	public void setUpdatetimestamp(String updatetimestamp) {
		this.updatetimestamp = updatetimestamp;
	}
	public String getTradepwd() {
		return tradepwd;
	}
	public void setTradepwd(String tradepwd) {
		this.tradepwd = tradepwd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getPasswderr() {
		return passwderr;
	}
	public void setPasswderr(int passwderr) {
		this.passwderr = passwderr;
	}
	
	@Override
	public String toString() {
		return "Custinfo [custno=" + custno + ", invtp=" + invtp + ", invnm=" + invnm + ", sex=" + sex + ", idtp=" + idtp + ", idno=" + idno + ", mobileno=" + mobileno + ", email=" + email
				+ ", custst=" + custst + ", passwd=" + passwd + ", lastlogintime=" + lastlogintime + ", passwderr=" + passwderr + ", opendt=" + opendt + ", updatetimestamp=" + updatetimestamp
				+ ", tradepwd=" + tradepwd + "]";
	}
	
}
