package com.ufufund.ufb.model.hft;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

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
