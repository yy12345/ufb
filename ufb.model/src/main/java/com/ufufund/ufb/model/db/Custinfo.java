package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

/**
 * 用户信息表dto
 * 
 * @author ayis
 * 
 */
public class Custinfo extends PrintableModel implements Serializable {

	private static final long serialVersionUID = -6338092203128112153L;

	private String custno;// 用户id
	private String invtp;// 用户类型：0个人 1机构
	private String level;// 级别: 0家庭 1机构 2经办人
	private String invnm;// 用户姓名
	private String sex;// 性别:0男 1女
	private String idtp;// 0-身份证 1-护照 2-军官证 3-士兵证 4-港澳 5-户口本 6-外国护照 7-其它 8-文职证 9-警官证 a-台胞证'
	private String idno;// 证件号码
	private String mobileno;// 手机号
	private String telno;
	private String emailadd;
	private String custst;// 账户状态：Y开户；N 注册；C注销;P冻结
	private String loginpwd;
	private String lastlogintime;// 上次登录时间
	private int passwderr;// 密码错误次数
	private String tradepwd;// 交易密码，md5密文
	private String orgnm;// 机构名称
	private String orgbusiness;// 营业执照
	
	
	/** 机构信息 **/
	private String orgprovinceno; // 省份
	private String orgprovincenm;
	private String orgcityno; // 城市
	private String orgcitynm;
	private String orgadd; // 实际办学经营地址
	
	
	/** 法人信息 **/
	private String rerpidtp; // 法人证件类型
	private String rerpidno; // 法人证件号码
	private String rerpvalidt; // 法人证件有效日期
	private String rerpnm; // 法人姓名
	
	
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getEmailadd() {
		return emailadd;
	}
	public void setEmailadd(String emailadd) {
		this.emailadd = emailadd;
	}
	public String getCustst() {
		return custst;
	}
	public void setCustst(String custst) {
		this.custst = custst;
	}
	public String getLoginpwd() {
		return loginpwd;
	}
	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}
	public String getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public int getPasswderr() {
		return passwderr;
	}
	public void setPasswderr(int passwderr) {
		this.passwderr = passwderr;
	}
	public String getTradepwd() {
		return tradepwd;
	}
	public void setTradepwd(String tradepwd) {
		this.tradepwd = tradepwd;
	}
	public String getOrgnm() {
		return orgnm;
	}
	public void setOrgnm(String orgnm) {
		this.orgnm = orgnm;
	}
	public String getOrgbusiness() {
		return orgbusiness;
	}
	public void setOrgbusiness(String orgbusiness) {
		this.orgbusiness = orgbusiness;
	}
	public String getOrgprovinceno() {
		return orgprovinceno;
	}
	public void setOrgprovinceno(String orgprovinceno) {
		this.orgprovinceno = orgprovinceno;
	}
	public String getOrgprovincenm() {
		return orgprovincenm;
	}
	public void setOrgprovincenm(String orgprovincenm) {
		this.orgprovincenm = orgprovincenm;
	}
	public String getOrgcityno() {
		return orgcityno;
	}
	public void setOrgcityno(String orgcityno) {
		this.orgcityno = orgcityno;
	}
	public String getOrgcitynm() {
		return orgcitynm;
	}
	public void setOrgcitynm(String orgcitynm) {
		this.orgcitynm = orgcitynm;
	}
	public String getOrgadd() {
		return orgadd;
	}
	public void setOrgadd(String orgadd) {
		this.orgadd = orgadd;
	}
	public String getRerpidtp() {
		return rerpidtp;
	}
	public void setRerpidtp(String rerpidtp) {
		this.rerpidtp = rerpidtp;
	}
	public String getRerpidno() {
		return rerpidno;
	}
	public void setRerpidno(String rerpidno) {
		this.rerpidno = rerpidno;
	}
	public String getRerpvalidt() {
		return rerpvalidt;
	}
	public void setRerpvalidt(String rerpvalidt) {
		this.rerpvalidt = rerpvalidt;
	}
	public String getRerpnm() {
		return rerpnm;
	}
	public void setRerpnm(String rerpnm) {
		this.rerpnm = rerpnm;
	}

}
