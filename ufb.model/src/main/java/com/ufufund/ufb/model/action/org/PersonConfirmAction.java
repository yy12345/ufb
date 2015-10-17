package com.ufufund.ufb.model.action.org;

import java.util.ArrayList;
import java.util.List;

import com.ufufund.ufb.model.action.CommonAction;

public class PersonConfirmAction extends CommonAction {
	
	//private String detailid;// char(24) DEFAULT NULL,
	//private String paycustno;// char(24) DEFAULT NULL COMMENT '缴费客户号',
	//private String paydate;// timestamp NULL DEFAULT NULL COMMENT '缴费日期',
	//private String plandate;// timestamp NULL DEFAULT NULL,
	private String acktype;// char(1) CHARACTER SET utf8mb4 DEFAULT 'U' COMMENT '扣款方式 U-幼富宝  B-银行卡',
	private String ackcustno;// char(24) DEFAULT NULL COMMENT '缴费客户号',
	private String ackbankcardid;// char(24) DEFAULT NULL COMMENT '缴费银行卡id',
	private String acktradeaccoid;// char(24) DEFAULT NULL COMMENT '缴费交易帐号id',
	private String acktradeacco;// char(17) DEFAULT NULL COMMENT '缴费交易帐号',
	//private String ackdate;// timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '确认日',
	private List<PersonConfirmList> personConfirmList =new ArrayList<PersonConfirmList>();
	
	

	public List<PersonConfirmList> getPersonConfirmList() {
		return personConfirmList;
	}





	public String getAcktype() {
		return acktype;
	}

	public void setAcktype(String acktype) {
		this.acktype = acktype;
	}

	public String getAckcustno() {
		return ackcustno;
	}

	public void setAckcustno(String ackcustno) {
		this.ackcustno = ackcustno;
	}

	public String getAckbankcardid() {
		return ackbankcardid;
	}

	public void setAckbankcardid(String ackbankcardid) {
		this.ackbankcardid = ackbankcardid;
	}

	public String getAcktradeaccoid() {
		return acktradeaccoid;
	}

	public void setAcktradeaccoid(String acktradeaccoid) {
		this.acktradeaccoid = acktradeaccoid;
	}

	public String getAcktradeacco() {
		return acktradeacco;
	}

	public void setAcktradeacco(String acktradeacco) {
		this.acktradeacco = acktradeacco;
	}

	
	
	
}
