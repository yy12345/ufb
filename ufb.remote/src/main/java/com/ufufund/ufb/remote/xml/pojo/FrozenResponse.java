package com.ufufund.ufb.remote.xml.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.ufufund.ufb.remote.xml.base.AbstractResponse;

@XmlAccessorType(XmlAccessType.FIELD)
public class FrozenResponse extends AbstractResponse{

	private String AppSheetSerialNo;

	public String getAppSheetSerialNo() {
		return AppSheetSerialNo;
	}
	public void setAppSheetSerialNo(String appSheetSerialNo) {
		AppSheetSerialNo = appSheetSerialNo;
	}
	
	@Override
	public String toString() {
		return "FrozenResponse [AppSheetSerialNo=" + AppSheetSerialNo
				+ ", toString()=" + super.toString() + "]";
	}
	
}
