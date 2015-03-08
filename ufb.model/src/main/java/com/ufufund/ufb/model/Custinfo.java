package com.ufufund.ufb.model;

import java.io.Serializable;

/**
 * 用户信息表dto
 * @author ayis
 *
 */
public class Custinfo implements Serializable{
	private static final long serialVersionUID = -6338092203128112153L;

	private String custno;
	
	private String invtp;
	
	private String intnm;
	
	private String sex;
	
	private String idtype;
	
	private String idno;
	
	private String mobileno;
	
	private String email;
	
	private String custst;
	
	private String passwd;
	
	private String lastlogintime;
	
	private String passwderr;
	
	private String opendt;
	
	private String tradepwd;
	
	private String updatetimestamp;

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

	public String getIntnm() {
		return intnm;
	}

	public void setIntnm(String intnm) {
		this.intnm = intnm;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
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

	public String getPasswderr() {
		return passwderr;
	}

	public void setPasswderr(String passwderr) {
		this.passwderr = passwderr;
	}

	public String getOpendt() {
		return opendt;
	}

	public void setOpendt(String opendt) {
		this.opendt = opendt;
	}

	public String getTradepwd() {
		return tradepwd;
	}

	public void setTradepwd(String tradepwd) {
		this.tradepwd = tradepwd;
	}

	public String getUpdatetimestamp() {
		return updatetimestamp;
	}

	public void setUpdatetimestamp(String updatetimestamp) {
		this.updatetimestamp = updatetimestamp;
	}

	@Override
	public String toString() {
		return "Custinfo [custno=" + custno + ", invtp=" + invtp + ", intnm="
				+ intnm + ", sex=" + sex + ", idtype=" + idtype + ", idno="
				+ idno + ", mobileno=" + mobileno + ", email=" + email
				+ ", custst=" + custst + ", passwd=" + passwd
				+ ", lastlogintime=" + lastlogintime + ", passwderr="
				+ passwderr + ", opendt=" + opendt + ", tradepwd=" + tradepwd
				+ ", updatetimestamp=" + updatetimestamp + "]";
	}
	
	
}
