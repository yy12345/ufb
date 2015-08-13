package com.ufufund.ufb.model.db;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class Orggrade extends PrintableModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orgid;//	char	24	机构ID
	private String gradeid;//	char	23	主键
	private String gradename;//	varchar	30	学期名
	private String startdate;//	char	8	开始日期
	private String enddate;//	char	8	结束日期
	
	private String isopen;// 	char	1	N关闭，Y进行中  只显示为Y的计划
	private String term1id;//	char	24	第一学期ID  gradeid+A
	private String t1startdate;//	char	8	开始日期
	private String t1enddate;//	char	8	结束日期
	private String t1isopen;//	char	1	N关闭，Y进行中 
	
	private String term2id;//	char	24	第二学期ID gradeid+B
	private String t2startdate;//	char	8	开始日期
	private String t2enddate;//	char	8	结束日期
	private String t2isopen;//	char	1	N关闭，Y进行中 
	private String createno;//	char	24	创建人 客户号
	//private String createtime	timestamp		创建时间
	private String updateno;//	char	24	最近修改 客户号
	//private String updatetime	timestamp		最近时间
	
	
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getGradename() {
		return gradename;
	}
	public void setGradename(String gradename) {
		this.gradename = gradename;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	public String getTerm1id() {
		return term1id;
	}
	public void setTerm1id(String term1id) {
		this.term1id = term1id;
	}
	public String getT1startdate() {
		return t1startdate;
	}
	public void setT1startdate(String t1startdate) {
		this.t1startdate = t1startdate;
	}
	public String getT1enddate() {
		return t1enddate;
	}
	public void setT1enddate(String t1enddate) {
		this.t1enddate = t1enddate;
	}
	public String getT1isopen() {
		return t1isopen;
	}
	public void setT1isopen(String t1isopen) {
		this.t1isopen = t1isopen;
	}
	public String getTerm2id() {
		return term2id;
	}
	public void setTerm2id(String term2id) {
		this.term2id = term2id;
	}
	public String getT2startdate() {
		return t2startdate;
	}
	public void setT2startdate(String t2startdate) {
		this.t2startdate = t2startdate;
	}
	public String getT2enddate() {
		return t2enddate;
	}
	public void setT2enddate(String t2enddate) {
		this.t2enddate = t2enddate;
	}
	public String getT2isopen() {
		return t2isopen;
	}
	public void setT2isopen(String t2isopen) {
		this.t2isopen = t2isopen;
	}
	public String getCreateno() {
		return createno;
	}
	public void setCreateno(String createno) {
		this.createno = createno;
	}
	public String getUpdateno() {
		return updateno;
	}
	public void setUpdateno(String updateno) {
		this.updateno = updateno;
	}

	

}
