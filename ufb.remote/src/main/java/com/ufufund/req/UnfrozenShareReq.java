package com.ufufund.req;

public class UnfrozenShareReq {

	/*
	 *
	 * 
		5011	Version	C	10	1.0.0	版本号	Y
		5000	MerchantId	C	10	机构标识	机构标识	Y
		121		DistributorCode	C	9	销售人代码	销售人代码	Y
		5024	BusinType	C	20	业务类型	见6.1	Y
		5002	ApplicationNo	A	24	直销平台申请单编号		Y
		
		8		AppSheetSerialNo	A	24	基金公司申请单编号		N
		92		TransactionDate	A	8	交易发生日期		N
		93		Transactiontime	A	6	交易发生时间		N
		66		TotalFundVol	N	16	总份额	两位小数	N
		13		AvailableVol	N	16	用户当前可用份额	两位小数	N
		
		59		TotalFrozenVol	N	16	冻结份额	两位小数	N
		5006	ReturnCode	C	4	返回代码		Y
		5007	ReturnMsg	C	255	返回描述		Y
		5008	Extension	C	500	消息扩展		N

	 */
	
	private String version;
	private String merchantId;
	private String distributorCode;
	private String businType;
	private String applicationNo;
	
	private String appSheetSerialNo;
	private String transactionDate;
	private String transactiontime;
	private String totalFundVol;
	private String availableVol;
	
	private String totalFrozenVol;
	private String returnCode;
	private String returnMsg;
	private String extension;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getDistributorCode() {
		return distributorCode;
	}
	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}
	public String getBusinType() {
		return businType;
	}
	public void setBusinType(String businType) {
		this.businType = businType;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getAppSheetSerialNo() {
		return appSheetSerialNo;
	}
	public void setAppSheetSerialNo(String appSheetSerialNo) {
		this.appSheetSerialNo = appSheetSerialNo;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactiontime() {
		return transactiontime;
	}
	public void setTransactiontime(String transactiontime) {
		this.transactiontime = transactiontime;
	}
	public String getTotalFundVol() {
		return totalFundVol;
	}
	public void setTotalFundVol(String totalFundVol) {
		this.totalFundVol = totalFundVol;
	}
	public String getAvailableVol() {
		return availableVol;
	}
	public void setAvailableVol(String availableVol) {
		this.availableVol = availableVol;
	}
	public String getTotalFrozenVol() {
		return totalFrozenVol;
	}
	public void setTotalFrozenVol(String totalFrozenVol) {
		this.totalFrozenVol = totalFrozenVol;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
}
