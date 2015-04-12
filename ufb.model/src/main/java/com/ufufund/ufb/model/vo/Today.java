package com.ufufund.ufb.model.vo;

public class Today {

	private String date;
	private String time;
	private String workday;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWorkday() {
		return workday;
	}
	public void setWorkday(String workday) {
		this.workday = workday;
	}
	@Override
	public String toString() {
		return "Today [date=" + date + ", time=" + time + ", workday="
				+ workday + "]";
	}
	
}
