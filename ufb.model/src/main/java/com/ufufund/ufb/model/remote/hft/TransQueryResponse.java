package com.ufufund.ufb.model.remote.hft;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransQueryResponse extends AbstractResponse {

	private int TotalRecord;
	
	@XmlElementWrapper(name="assetList")
	@XmlElement(name="asset")
	private List<TransQueryAsset> assets;
	
	public int getTotalRecord() {
		return TotalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		TotalRecord = totalRecord;
	}

	public List<TransQueryAsset> getAssets() {
		return assets;
	}

	public void setAssets(List<TransQueryAsset> assets) {
		this.assets = assets;
	}
	
	@Override
	public String toString() {
		return "TransQueryResponse [TotalRecord=" + TotalRecord + ", assets="
				+ assets + ", toString()=" + super.toString() + "]";
	}

}

