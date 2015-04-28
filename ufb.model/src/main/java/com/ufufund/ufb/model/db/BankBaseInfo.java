package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class BankBaseInfo extends PrintableModel implements Serializable {

	private static final long serialVersionUID = -6765966376534371940L;
	
	private String bankno;
	private String banknm;
	private String banksite;
	private String ebankurl;
	private String telbank;
	private String linkman;
	private String linkmantel;
	private String linkmanfax;
	private String linkmanemail;
	private String disorder;
	private String disflag;
	private String status;
	private String cman;
	private String ctime;
	private String eman;
	private String etime;

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getBanknm() {
		return banknm;
	}

	public void setBanknm(String banknm) {
		this.banknm = banknm;
	}

	public String getBanksite() {
		return banksite;
	}

	public void setBanksite(String banksite) {
		this.banksite = banksite;
	}

	public String getEbankurl() {
		return ebankurl;
	}

	public void setEbankurl(String ebankurl) {
		this.ebankurl = ebankurl;
	}

	public String getTelbank() {
		return telbank;
	}

	public void setTelbank(String telbank) {
		this.telbank = telbank;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkmantel() {
		return linkmantel;
	}

	public void setLinkmantel(String linkmantel) {
		this.linkmantel = linkmantel;
	}

	public String getLinkmanfax() {
		return linkmanfax;
	}

	public void setLinkmanfax(String linkmanfax) {
		this.linkmanfax = linkmanfax;
	}

	public String getLinkmanemail() {
		return linkmanemail;
	}

	public void setLinkmanemail(String linkmanemail) {
		this.linkmanemail = linkmanemail;
	}

	public String getDisorder() {
		return disorder;
	}

	public void setDisorder(String disorder) {
		this.disorder = disorder;
	}

	public String getDisflag() {
		return disflag;
	}

	public void setDisflag(String disflag) {
		this.disflag = disflag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCman() {
		return cman;
	}

	public void setCman(String cman) {
		this.cman = cman;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getEman() {
		return eman;
	}

	public void setEman(String eman) {
		this.eman = eman;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

}
