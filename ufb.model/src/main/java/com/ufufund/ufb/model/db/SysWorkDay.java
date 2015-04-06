package com.ufufund.ufb.model.db;

import java.io.Serializable;

public class SysWorkDay implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日期
	 */
	private String workdate;
	
	/**
	 * 是否工作日：Y 是； N 否
	 */
	private String workflag;

	public String getWorkdate() {
		return workdate;
	}

	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}

	public String getWorkflag() {
		return workflag;
	}

	public void setWorkflag(String workflag) {
		this.workflag = workflag;
	}

	@Override
	public String toString() {
		return "SysWorkDay [workdate=" + workdate + ", workflag=" + workflag
				+ "]";
	}
	
}
