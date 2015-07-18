package com.ufufund.ufb.model.action.cust;

public class OpenAccountOrgAction extends OpenAccountAction {

	/** 银行信息 **/
	private String bankprovinceno; // 省份
	private String bankprovincenm;
	private String bankcityno; // 城市
	private String bankcitynm;
	private String bankadd; // 支行网点

	/** 经办人信息 **/
	private String operatornm; // 经办人
	private String operatoridtp;
	private String operatoridno;
	private String operatormobile;
	private String operatortelno;
	private String operatoremailadd;

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

	public String getBankprovinceno() {
		return bankprovinceno;
	}

	public void setBankprovinceno(String bankprovinceno) {
		this.bankprovinceno = bankprovinceno;
	}

	public String getBankprovincenm() {
		return bankprovincenm;
	}

	public void setBankprovincenm(String bankprovincenm) {
		this.bankprovincenm = bankprovincenm;
	}

	public String getBankcityno() {
		return bankcityno;
	}

	public void setBankcityno(String bankcityno) {
		this.bankcityno = bankcityno;
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

	public String getOperatornm() {
		return operatornm;
	}

	public void setOperatornm(String operatornm) {
		this.operatornm = operatornm;
	}

	public String getOperatoridtp() {
		return operatoridtp;
	}

	public void setOperatoridtp(String operatoridtp) {
		this.operatoridtp = operatoridtp;
	}

	public String getOperatoridno() {
		return operatoridno;
	}

	public void setOperatoridno(String operatoridno) {
		this.operatoridno = operatoridno;
	}

	public String getOperatormobile() {
		return operatormobile;
	}

	public void setOperatormobile(String operatormobile) {
		this.operatormobile = operatormobile;
	}

	public String getOperatortelno() {
		return operatortelno;
	}

	public void setOperatortelno(String operatortelno) {
		this.operatortelno = operatortelno;
	}

	public String getOperatoremailadd() {
		return operatoremailadd;
	}

	public void setOperatoremailadd(String operatoremailadd) {
		this.operatoremailadd = operatoremailadd;
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
