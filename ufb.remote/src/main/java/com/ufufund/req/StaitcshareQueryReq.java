package com.ufufund.req;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class StaitcshareQueryReq {

	/*
	 * 
		5011	Version	C	10	1.0.0	版本号	Y
		5000	MerchantId	C	10	机构标识	机构标识	Y
		121		DistributorCode	C	9	销售人代码	销售人代码	Y
		5024	BusinType	C	20	业务类型	见6.1	Y
		5002	ApplicationNo	A	24	直销平台申请单编号	直销平台申请单编号	Y
		5018	TotalRecord	A	3	总记录数		Y
						以下字段可能多次出现
						120	TransactionAccountID	C	17	交易账号		Y
						67	FundCode	C	6	基金代码		Y
						260	ShareClass	A	1	收费方式		Y
						66	TotalFundVol	N	16	总份额	两位小数	Y
						13	AvailableVol	N	16	用户当前可用份额	两位小数	Y
						59	TotalFrozenVol	N	16	冻结份额	两位小数	Y
						507	UndistributeMonetaryIncome	N	16	未付收益金额	两位小数	N
						536	FundDayIncome	N	16	每日收益	两位小数	N
						5005	TotalIncome	N	16	累计收益	两位小数	N
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
	private List<StaitcshareQueryDelList>   delList = new ArrayList<StaitcshareQueryDelList>();
	
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
