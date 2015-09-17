package com.ufufund.ufb.model.vo;

import java.io.Serializable;

import com.ufufund.ufb.model.action.PrintableModel;

public class QueryCustPayInfo extends PrintableModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	//private String studentid;// 学生ID
	//private String orgid;// 学生ID

	
	
	/*
	 收费 
	 */
	private String gtotalappamount;// 总应缴金额
	private String gtotaldiscount;//总折扣金额
	private String gtotalackamount;// 总应缴实际金额
    private String gallnum;//  总应缴笔数
	private String gyespaynum;// 已缴笔数
	private String gnopaynum;// 未缴笔数
	private String gyespayamount;// 实际已缴总金额
	private String gnopayamount;//  实际待缴总金额
	
	/*
	 *退费
	 */
	private String rtotalappamount;// 总应退费金额
	private String rtotaldiscount;//总折扣退费金额
	private String rtotalackamount;// 总实际退费金额
    private String rallnum;//  总退费笔数
	private String ryespaynum;// 已退费笔数
	private String rnopaynum;// 未退费笔数
	private String ryespayamount;// 实际退费总金额
	private String rnopayamount;//  实际待退费总金额
	
	
	public String getGtotalappamount() {
		return gtotalappamount;
	}
	public void setGtotalappamount(String gtotalappamount) {
		this.gtotalappamount = gtotalappamount;
	}
	public String getGtotaldiscount() {
		return gtotaldiscount;
	}
	public void setGtotaldiscount(String gtotaldiscount) {
		this.gtotaldiscount = gtotaldiscount;
	}
	public String getGtotalackamount() {
		return gtotalackamount;
	}
	public void setGtotalackamount(String gtotalackamount) {
		this.gtotalackamount = gtotalackamount;
	}
	public String getGallnum() {
		return gallnum;
	}
	public void setGallnum(String gallnum) {
		this.gallnum = gallnum;
	}
	public String getGyespaynum() {
		return gyespaynum;
	}
	public void setGyespaynum(String gyespaynum) {
		this.gyespaynum = gyespaynum;
	}
	public String getGnopaynum() {
		return gnopaynum;
	}
	public void setGnopaynum(String gnopaynum) {
		this.gnopaynum = gnopaynum;
	}
	public String getGyespayamount() {
		return gyespayamount;
	}
	public void setGyespayamount(String gyespayamount) {
		this.gyespayamount = gyespayamount;
	}
	public String getGnopayamount() {
		return gnopayamount;
	}
	public void setGnopayamount(String gnopayamount) {
		this.gnopayamount = gnopayamount;
	}
	public String getRtotalappamount() {
		return rtotalappamount;
	}
	public void setRtotalappamount(String rtotalappamount) {
		this.rtotalappamount = rtotalappamount;
	}
	public String getRtotaldiscount() {
		return rtotaldiscount;
	}
	public void setRtotaldiscount(String rtotaldiscount) {
		this.rtotaldiscount = rtotaldiscount;
	}
	public String getRtotalackamount() {
		return rtotalackamount;
	}
	public void setRtotalackamount(String rtotalackamount) {
		this.rtotalackamount = rtotalackamount;
	}
	public String getRallnum() {
		return rallnum;
	}
	public void setRallnum(String rallnum) {
		this.rallnum = rallnum;
	}
	public String getRyespaynum() {
		return ryespaynum;
	}
	public void setRyespaynum(String ryespaynum) {
		this.ryespaynum = ryespaynum;
	}
	public String getRnopaynum() {
		return rnopaynum;
	}
	public void setRnopaynum(String rnopaynum) {
		this.rnopaynum = rnopaynum;
	}
	public String getRyespayamount() {
		return ryespayamount;
	}
	public void setRyespayamount(String ryespayamount) {
		this.ryespayamount = ryespayamount;
	}
	public String getRnopayamount() {
		return rnopayamount;
	}
	public void setRnopayamount(String rnopayamount) {
		this.rnopayamount = rnopayamount;
	}
	
	
	
	
}


