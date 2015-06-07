package com.ufufund.ufb.model.hft;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class BalanceQueryResponse extends AbstractResponse {

	private int TotalRecord;

	@XmlElementWrapper(name="assetList")
	@XmlElement(name="asset")
	private List<BalanceQueryAsset> assets;
	
	public int getTotalRecord() {
		return TotalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		TotalRecord = totalRecord;
	}

	public List<BalanceQueryAsset> getAssets() {
		return assets;
	}

	public void setAssets(List<BalanceQueryAsset> assets) {
		this.assets = assets;
	}

	@Override
	public String toString() {
		return "BalanceQueryResponse [TotalRecord=" + TotalRecord + ", assets="
				+ assets + ", toString()=" + super.toString() + "]";
	}

}
