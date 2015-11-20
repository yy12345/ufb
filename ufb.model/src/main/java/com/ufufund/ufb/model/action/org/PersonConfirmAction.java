package com.ufufund.ufb.model.action.org;

import java.util.ArrayList;
import java.util.List;

import com.ufufund.ufb.model.action.CommonAction;

import lombok.Data;
@Data
public class PersonConfirmAction extends CommonAction {
	
	private String acktype;// 扣款方式 U-幼富宝  B-银行卡
	private String ackcustno;// 缴费客户号
	private String ackbankcardid;// 缴费银行卡id
	private String acktradeacco;// '缴费交易帐号
	private List<PersonConfirmList> personConfirmList =new ArrayList<PersonConfirmList>();
	
}
