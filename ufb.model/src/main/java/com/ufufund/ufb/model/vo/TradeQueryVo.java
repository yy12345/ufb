package com.ufufund.ufb.model.vo;

import java.io.Serializable;

public class TradeQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String custno;
	private String appdateindex;
	private String appcateindex;
	private String apptypeindex;
	private String startappdate;
	private String endappdate;

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getAppdateindex() {
		return appdateindex;
	}

	public void setAppdateindex(String appdateindex) {
		this.appdateindex = appdateindex;
	}

	public String getAppcateindex() {
		return appcateindex;
	}

	public void setAppcateindex(String appcateindex) {
		this.appcateindex = appcateindex;
	}

	public String getApptypeindex() {
		return apptypeindex;
	}

	public void setApptypeindex(String apptypeindex) {
		this.apptypeindex = apptypeindex;
	}

	public String getStartappdate() {
		return startappdate;
	}

	public void setStartappdate(String startappdate) {
		this.startappdate = startappdate;
	}

	public String getEndappdate() {
		return endappdate;
	}

	public void setEndappdate(String endappdate) {
		this.endappdate = endappdate;
	}

}
