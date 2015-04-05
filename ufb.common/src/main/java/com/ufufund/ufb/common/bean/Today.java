package com.ufufund.ufb.common.bean;

public class Today {

	private String date;
	private String time;
	
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
	@Override
	public String toString() {
		return "Today [date=" + date + ", time=" + time + "]";
	}

}
