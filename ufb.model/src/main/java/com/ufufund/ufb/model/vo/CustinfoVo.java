package com.ufufund.ufb.model.vo;

/**
 * 用户VO <br>
 * 创建日期：2015年3月
 * 
 * @author goodrich
 * @version 1.0
 */
public class CustinfoVo {

	private String custno;
	private String custst;
	private String invtp; // 用户类型
	private String level;
	private String mobileno;

	private String invnm; // 用户姓名
	private String idtp; // 证件类型
	private String idno; // 证件号码
	private String pswpwd;
	private String pswpwd2;
	private String tradepwd;
	private String tradepwd2;// 交易密码

	private String orgnm;// 幼教机构名称
	private String orgbusiness; // 营业执照注册号

	private String msgcode;
	private String verifycode;

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getCustst() {
		return custst;
	}

	public void setCustst(String custst) {
		this.custst = custst;
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

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
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

	public String getPswpwd() {
		return pswpwd;
	}

	public void setPswpwd(String pswpwd) {
		this.pswpwd = pswpwd;
	}

	public String getPswpwd2() {
		return pswpwd2;
	}

	public void setPswpwd2(String pswpwd2) {
		this.pswpwd2 = pswpwd2;
	}

	public String getTradepwd() {
		return tradepwd;
	}

	public void setTradepwd(String tradepwd) {
		this.tradepwd = tradepwd;
	}

	public String getTradepwd2() {
		return tradepwd2;
	}

	public void setTradepwd2(String tradepwd2) {
		this.tradepwd2 = tradepwd2;
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

	public String getMsgcode() {
		return msgcode;
	}

	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

}
