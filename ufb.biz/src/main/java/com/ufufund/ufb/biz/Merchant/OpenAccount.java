package com.ufufund.ufb.biz.Merchant;

public class OpenAccount extends BaseResponse {

	private String otherserial;//对方序列号
	private String protocolno;//银行协议编号
	private String transactionAccountID;
	
	public String getOtherserial() {
		return otherserial;
	}
	public void setOtherserial(String otherserial) {
		this.otherserial = otherserial;
	}
	public String getProtocolno() {
		return protocolno;
	}
	public void setProtocolno(String protocolno) {
		this.protocolno = protocolno;
	}
	public String getTransactionAccountID() {
		return transactionAccountID;
	}
	public void setTransactionAccountID(String transactionAccountID) {
		this.transactionAccountID = transactionAccountID;
	}
	
	
}
