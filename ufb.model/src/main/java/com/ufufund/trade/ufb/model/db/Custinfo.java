package com.ufufund.trade.ufb.model.db;

import java.io.Serializable;

public class Custinfo implements Serializable{
	private static final long serialVersionUID = 2823000821868950247L;

	private String custNo;
	
	private String custType;
	
	private String areaId;

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Override
	public String toString() {
		return "Custinfo [custNo=" + custNo + ", custType=" + custType
				+ ", areaId=" + areaId + "]";
	}
	
}
