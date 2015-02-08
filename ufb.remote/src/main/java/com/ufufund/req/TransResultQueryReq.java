package com.ufufund.req;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class TransResultQueryReq {

	/*
	 * 
		5011	Version	C	10	1.0.0	版本号	Y
		5000	MerchantId	C	10	机构标识	机构标识	Y
		121		DistributorCode	C	9	销售人代码	销售人代码	Y
		5024	BusinType	C	20	业务类型	见6.1	Y
		5002	ApplicationNo	A	24	直销平台申请单编号	直销平台申请单编号	Y
		
		5018	TotalRecord	A	3	总记录数		Y
				以下字段可能多次出现
				8	AppSheetSerialNo	A	24	基金公司申请单编号		N
				92	TransactionDate	A	8	交易发生日期		N
				93	Transactiontime	A	6	交易发生时间		N
				5003	ConfirmFlag	A	1	确认状态		N
				5004	PayFlag	A	1	扣款状态		N
				5012	TradeDate	A	8	当前交易日		N
				以上字段可能多次出现
		5006	ReturnCode	C	4	返回代码		Y
		5007	ReturnMsg	C	255	返回描述		Y
		5008	Extension	C	500	消息扩展		N


	 * 
	 */
	
	private String version;
	private String merchantId;
	private String distributorCode;
	private String businType;
	private String applicationNo;
	
	private String totalRecord;
	private String returnCode;
	private String returnMsg;
	private String extension;
	
	@XmlElementWrapper(name = "assetLit")
    @XmlElement(name = "asset")
	private List<TransResultQueryDelList>   delList = new ArrayList<TransResultQueryDelList>();
	
	
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
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
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
