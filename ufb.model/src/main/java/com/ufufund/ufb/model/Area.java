package com.ufufund.ufb.model;

import java.io.Serializable;

public class Area implements Serializable{
	private static final long serialVersionUID = 598292256424376950L;
	
	private String id;
	
	private String province;
	
	private String location;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", province=" + province + ", location="
				+ location + "]";
	}

}
