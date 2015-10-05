package com.ufufund.ufb.model.vo;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class QueryOrgStudent extends PrintableModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orgid;//
	private String orgnm;//
	private String classid;//
	private String classßnm;//
	private String studentid;//
	private String studentnm;//
	private String custno;//
	private String custnm;//

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getClassßnm() {
		return classßnm;
	}

	public void setClassßnm(String classßnm) {
		this.classßnm = classßnm;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgnm() {
		return orgnm;
	}

	public void setOrgnm(String orgnm) {
		this.orgnm = orgnm;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getStudentnm() {
		return studentnm;
	}

	public void setStudentnm(String studentnm) {
		this.studentnm = studentnm;
	}

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getCustnm() {
		return custnm;
	}

	public void setCustnm(String custnm) {
		this.custnm = custnm;
	}

}
