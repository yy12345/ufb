package com.ufufund.ufb.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 
 * @author GH
 * 
 */
public class AutotradeVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String autoid;
	
	private String custno;
	private BigDecimal autoamt;
	private String summary;
	private String apkind;
	private String cycle;
	private String dat;
	private String fromfundcode;// varchar(6) default null comment '源基金代码',
	private String fromfundcorpno;// char(24) default null comment '源归属基金公司',
	private String frombankserialid;
	private String frombankacco;
	private String fromaccoid;
	private String fromtradeacco;
	private String fromchargetype;
	

	private String tofundcode;// varchar(6) default null comment '目标基金代码',
	private String tofundcorpno;// char(24) default null comment '目标归属基金公司',
	private String tobankserialid;
	private String tobankacco;
	private String toaccoid;
	private String totradeacco;
	private String tochargetype;
	
	private String lastdate;// char(8) default null comment '最近扣款日期',
	private String nextdate;// char(8) default null comment '下一扣款日期',
	
	public String getAutoid() {
		return autoid;
	}

	public void setAutoid(String autoid) {
		this.autoid = autoid;
	}

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public BigDecimal getAutoamt() {
		return autoamt;
	}

	public void setAutoamt(BigDecimal autoamt) {
		this.autoamt = autoamt;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getApkind() {
		return apkind;
	}

	public void setApkind(String apkind) {
		this.apkind = apkind;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getDat() {
		return dat;
	}

	public void setDat(String dat) {
		this.dat = dat;
	}

	public String getFromfundcode() {
		return fromfundcode;
	}

	public void setFromfundcode(String fromfundcode) {
		this.fromfundcode = fromfundcode;
	}

	public String getFromfundcorpno() {
		return fromfundcorpno;
	}

	public void setFromfundcorpno(String fromfundcorpno) {
		this.fromfundcorpno = fromfundcorpno;
	}

	public String getFrombankserialid() {
		return frombankserialid;
	}

	public void setFrombankserialid(String frombankserialid) {
		this.frombankserialid = frombankserialid;
	}

	public String getFrombankacco() {
		return frombankacco;
	}

	public void setFrombankacco(String frombankacco) {
		this.frombankacco = frombankacco;
	}

	public String getFromaccoid() {
		return fromaccoid;
	}

	public void setFromaccoid(String fromaccoid) {
		this.fromaccoid = fromaccoid;
	}

	public String getFromtradeacco() {
		return fromtradeacco;
	}

	public void setFromtradeacco(String fromtradeacco) {
		this.fromtradeacco = fromtradeacco;
	}

	public String getTofundcode() {
		return tofundcode;
	}

	public void setTofundcode(String tofundcode) {
		this.tofundcode = tofundcode;
	}

	public String getTofundcorpno() {
		return tofundcorpno;
	}

	public void setTofundcorpno(String tofundcorpno) {
		this.tofundcorpno = tofundcorpno;
	}

	public String getTobankserialid() {
		return tobankserialid;
	}

	public void setTobankserialid(String tobankserialid) {
		this.tobankserialid = tobankserialid;
	}

	public String getTobankacco() {
		return tobankacco;
	}

	public void setTobankacco(String tobankacco) {
		this.tobankacco = tobankacco;
	}

	public String getToaccoid() {
		return toaccoid;
	}

	public void setToaccoid(String toaccoid) {
		this.toaccoid = toaccoid;
	}

	public String getTotradeacco() {
		return totradeacco;
	}

	public void setTotradeacco(String totradeacco) {
		this.totradeacco = totradeacco;
	}

	public String getLastdate() {
		return lastdate;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	public String getNextdate() {
		return nextdate;
	}

	public void setNextdate(String nextdate) {
		this.nextdate = nextdate;
	}

	public String getFromchargetype() {
		return fromchargetype;
	}

	public void setFromchargetype(String fromchargetype) {
		this.fromchargetype = fromchargetype;
	}

	public String getTochargetype() {
		return tochargetype;
	}

	public void setTochargetype(String tochargetype) {
		this.tochargetype = tochargetype;
	}

	@Override
	public String toString() {
		return "AutotradeVo [custno=" + custno
				+ ", autoamt=" + autoamt
				+ ", summary=" + summary
				+ ", apkind=" + apkind
				+ ", cycle=" + cycle
				+ ", dat=" + dat
				+ ", fromfundcorpno=" + fromfundcorpno
				+ ", fromfundcode=" + fromfundcode
				+ ", frombankserialid=" + frombankserialid
				+ ", frombankacco=" + frombankacco
				+ ", fromaccoid=" + fromaccoid
				+ ", fromtradeacco=" + fromtradeacco
				+ ", fromchargetype=" + fromchargetype
				+ ", tofundcorpno=" + tofundcorpno
				+ ", tofundcode=" + tofundcode
				+ ", tobankserialid=" + tobankserialid
				+ ", tobankacco=" + tobankacco
				+ ", toaccoid=" + toaccoid
				+ ", totradeacco=" + totradeacco
				+ ", tochargetype=" + tochargetype
				+ ", nextdate=" + nextdate
				+ ", lastdate=" + lastdate
				+ "]";
	}
}
