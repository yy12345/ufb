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

	private String custno;
	private BigDecimal autoamt;
	private String summary;
	private String apkind;
	private String cycle;
	private String dat;
	private String fromfundcode;// varchar(6) default null comment '源基金代码',
	private String frombankserialid;
	private String fromfundcorpno;// char(24) default null comment '源归属基金公司',
	private String fromtradeacco;
	private String lastdate;// char(8) default null comment '最近扣款日期',
	private String nextdate;// char(8) default null comment '下一扣款日期',

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



	public String getFrombankserialid() {
		return frombankserialid;
	}



	public void setFrombankserialid(String frombankserialid) {
		this.frombankserialid = frombankserialid;
	}



	public String getFromfundcorpno() {
		return fromfundcorpno;
	}



	public void setFromfundcorpno(String fromfundcorpno) {
		this.fromfundcorpno = fromfundcorpno;
	}



	public String getFromtradeacco() {
		return fromtradeacco;
	}



	public void setFromtradeacco(String fromtradeacco) {
		this.fromtradeacco = fromtradeacco;
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



	@Override
	public String toString() {
		return "AutotradeVo [custno=" + custno
				+ ", autoamt=" + autoamt
				+ ", summary=" + summary
				+ ", apkind=" + apkind
				+ ", cycle=" + cycle
				+ ", dat=" + dat
				+ ", frombankserialid=" + frombankserialid
				+ ", fromfundcode=" + fromfundcode
				+ ", fromfundcorpno=" + fromfundcorpno
				+ ", fromtradeacco=" + fromtradeacco
				+ ", nextdate=" + nextdate
				+ ", lastdate=" + lastdate
				+ "]";
	}
}
